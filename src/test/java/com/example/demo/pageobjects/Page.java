package com.example.demo.pageobjects;

import static com.codeborne.selenide.Selenide.open;
import static com.example.demo.utils.UrlUtils.getUrl;

abstract class Page<T> {

    abstract T isLoaded();

    public T load() {
        return load("");
    }

    public T load(String url) {
        open(getUrl(getClass()) + "/" + url);
        return (T) this;
    }
}
