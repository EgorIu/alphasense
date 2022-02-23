package com.example.demo.tests;


import com.example.demo.config.AppConfig;
import com.example.demo.config.ConfigProperties;
import com.example.demo.dto.ErrorResponse;
import com.example.demo.dto.Statement;
import com.example.demo.pageservices.DocPageService;
import com.example.demo.services.SearchService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import retrofit2.Retrofit;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.utils.ApiUtils.getErrorConverter;
import static com.example.demo.utils.SelenideConfigUtils.setUpSelenide;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = AppConfig.class)
@EnableConfigurationProperties
@Log4j2
class DemoApplicationTests {

    @Autowired
    SearchService searchService;

    @Autowired
    ConfigProperties configProperties;

    @Autowired
    DocPageService docPageService;

    @Autowired
    Retrofit retrofit;

    @BeforeEach
    void setUp() {
        setUpSelenide();
    }


    @Test
    @DisplayName("API. Verify successful search request")
    void searchResultsTest() {
        String expectedText = "Logo - <span class='hl'>https://mma.prnewswire.com/media/947841/AlphaSense_Logo.jpg</span>";
        var response = searchService
                .getSearchResults(configProperties.getDocName(), "AlphaSense", "15", "1633003200000");

        assertThat(response.code())
                .as("Expected error code is 200 but found " + response.code())
                .isEqualTo(200);

        assertThat(response.body())
                .as("Response body should have a content but it's null")
                .isNotNull();

        List<Statement> results = response.body()
                .getSearchResults()
                .getStatements()
                .stream()
                .filter(s -> s.getContent().equals(expectedText))
                .collect(Collectors.toList());

        assertThat(results)
                .as("Search results should contain the following: " + expectedText + " but it is not found")
                .hasSize(1);
    }


    @Test
    @SneakyThrows
    @DisplayName("API. Verify unsuccessful (bad) search request")
    void searchResultsNoSlopErrorTest() {
        String errorMsg = "slop must be a number conforming to the specified constraints";
        var response = searchService
                .getSearchResults(configProperties.getDocName(), "AlphaSense", "", "1633003200000");

        assertThat(response.errorBody())
                .as("Response body should have a content but it's null")
                .isNotNull();

        ErrorResponse body = getErrorConverter(ErrorResponse.class, retrofit).convert(response.errorBody());
        assertThat(body.getMessage().get(0)).isEqualTo(errorMsg);
    }

    @Test
    @DisplayName("UI. Search for keyword. Click on search result and verify it's highlighted in the document")
    void keywordsSearchTest() {
        String expectedText = docPageService
                .getDocPage()
                .load(configProperties.getDocName())
                .isLoaded()
                .searchAdditionalKeyword("AlphaSense")
                .selectLastItem();

        String actualText = docPageService
                .getDocPage()
                .getHighlightedParagraph();
        assertThat(expectedText.trim())
                .as("Expected text is " + expectedText + " but found " + actualText)
                .isEqualTo(actualText.trim());
    }
}
