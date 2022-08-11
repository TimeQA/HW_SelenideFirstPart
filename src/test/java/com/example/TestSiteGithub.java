package com.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.nio.channels.Selector.open;

public class TestSiteGithub {

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void shouldFindCodeJUnit5() {
        // Запускаю браузер и перехожу на страницу Github
        Selenide.open("https://github.com");
        // Ввожу в поле поиска "Selenide" и нажимаю Enter
        $("[name=q]").setValue("Selenide").pressEnter();
        // Ищу необходимый репозиторий и кликаю для перехода
        $$("ul.repo-list li a").first().click();
        // Перехожу в Wiki
        $("#wiki-tab").click();
        // Раскрываю весь список меню справа
        $("div.js-wiki-sidebar-toggle-display ul li button").click();
        // Проверяю, что SoftAssertions есть в меню справа
        $(".Layout-sidebar").shouldHave(text("SoftAssertions"));
        // Кликаю для перехода на SoftAssertions
        $(".wiki-rightbar").$(byText("SoftAssertions")).click();
        // Проверка на предмет выявления примера кода JUinit5
        $("#wiki-body").shouldHave(text("3. Using JUnit5 extend test class:"));
    }
}