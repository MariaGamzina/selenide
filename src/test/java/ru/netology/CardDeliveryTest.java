package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.*;

public class CardDeliveryTest {

    private String setDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    void shouldTest() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Майкоп");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(BACK_SPACE);
        String date = setDate(3, "dd.MM.yyyy");
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Иван Петров");
        $("[data-test-id=phone] input").setValue("+79002345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча  забронирована на " + date));
    }
}
