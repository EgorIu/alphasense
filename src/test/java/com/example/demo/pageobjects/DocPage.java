package com.example.demo.pageobjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;

public class DocPage extends Page<DocPage> {

    private static final SelenideElement searchBarAddKeywords = $(By.id("searchInDocument"));
    private static final String additionalKeywordItems = ".ReactVirtualized__Grid__innerScrollContainer > div .snippetItem__content";
    private static final String loadingBar = "dv-loading-page";
    private static final String highlightedText = "//div[@id='mainWrapper']//p[./span[contains(@class,'x-grid3-row-blue')]]";
    private static final String docFrame = "content-1";

    @Step("Search for {query}")
    public DocPage searchAdditionalKeyword(String query) {
        searchBarAddKeywords.shouldBe(Condition.visible).click();
        searchBarAddKeywords.$(By.xpath(".//textarea")).sendKeys(Keys.chord(query, Keys.ENTER));
        return this;
    }

    @Step("Scroll to the last item in the search list and select it")
    public String selectLastItem() {
        Set<SelenideElement> items = new LinkedHashSet<>();
        Optional<SelenideElement> lastToAdd = Optional.empty();
        boolean isDone = true;
        while (isDone) {
            int nums = 0;
            ElementsCollection els = $$(additionalKeywordItems).shouldHave(CollectionCondition.sizeGreaterThan(1));
            for (SelenideElement e : els) {
                if (items.add(e)) {
                    e.scrollIntoView(true);
                    lastToAdd = Optional.of(e);
                } else {
                    nums++;
                }
            }
            if (nums == els.size()) isDone = false;
        }
        lastToAdd.ifPresent(SelenideElement::click);
        return lastToAdd.map(SelenideElement::getText).orElse("");
    }

    @Override
    @Step("Verify that document page is loaded")
    public DocPage isLoaded() {
        $(loadingBar).shouldNot(Condition.exist, Duration.ofSeconds(30L));
        return this;
    }

    private WebDriver goToDocFrame() {
        return switchTo().frame(docFrame);
    }

    public String getHighlightedParagraph() {
        return goToDocFrame().findElement(By.xpath(highlightedText)).getText();
    }
}
