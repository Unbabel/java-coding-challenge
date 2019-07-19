package com.unbabel.challenge.selenium;

import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FrontEndTest extends FluentTest
{
    @Value("${local.server.port}")
    private int serverPort;

    private WebDriver webDriver = new PhantomJSDriver();

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

    private String getUrl() {
        return "http://localhost:" + serverPort;
    }

    @Test
    public void hasPageTitle() {
        goTo(getUrl());
        assertThat(find(".page-header").getText()).isEqualTo("A checklist");
    }

}
