package com.example.demo.utils;

import com.codeborne.selenide.Configuration;

public class SelenideConfigUtils {

    public static void setUpSelenide(){
//        Configuration.baseUrl = "https://rc.alpha-sense.com";
//        Configuration.holdBrowserOpen = true;
//        Configuration.browser = "chrome";
        Configuration.fastSetValue = true;
        Configuration.browserSize="1366x868";
    }
}
