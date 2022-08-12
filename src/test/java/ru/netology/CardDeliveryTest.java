package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardDeliveryTest {


    @BeforeEach
    public void openPage() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldSendForm() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Волгоград");
        String deliveryDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(deliveryDate);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79267777777");
        $("[data-test-id=agreement] span").click();
        $(withText("Забронировать")).click();
        $("[data-test-id=notification]").shouldHave(Condition.text
                ("Успешно! Встреча успешно забронирована на " + deliveryDate), Duration.ofSeconds(15));

    }

    @Test
    public void shouldSendDoubleCity() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Нижний Новгород");
        String deliveryDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(deliveryDate);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79267777777");
        $("[data-test-id=agreement] span").click();
        $(withText("Забронировать")).click();
        $("[data-test-id=notification]").shouldHave(Condition.text
                ("Успешно! Встреча успешно забронирована на " + deliveryDate), Duration.ofSeconds(15));

    }

    @Test
    public void shouldSendCityWithDash() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Улан-Удэ");
        String deliveryDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(deliveryDate);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79267777777");
        $("[data-test-id=agreement] span").click();
        $(withText("Забронировать")).click();
        $("[data-test-id=notification]").shouldHave(Condition.text
                ("Успешно! Встреча успешно забронирована на " + deliveryDate), Duration.ofSeconds(15));

    }

    @Test
    public void shouldDeliverInMonth() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Волгоград");
        String deliveryDate = LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(deliveryDate);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79267777777");
        $("[data-test-id=agreement] span").click();
        $(withText("Забронировать")).click();
        $("[data-test-id=notification]").shouldHave(Condition.text
                ("Успешно! Встреча успешно забронирована на " + deliveryDate), Duration.ofSeconds(15));

    }

    @Test
    public void shouldNotSendWithLatinCity() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Volgograd");
        String deliveryDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(deliveryDate);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79267777777");
        $("[data-test-id=agreement] span").click();
        $(withText("Забронировать")).click();
        String expectedText = "Доставка в выбранный город недоступна";
        String actualText = $("[data-test-id=city] .input__sub").getText().trim();
        assertEquals(expectedText, actualText);

    }

    @Test
    public void shouldNotSendEmptyCity() {
        Configuration.holdBrowserOpen = true;

        String deliveryDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(deliveryDate);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79267777777");
        $("[data-test-id=agreement] span").click();
        $(withText("Забронировать")).click();
        String expectedText = "Поле обязательно для заполнения";
        String actualText = $("[data-test-id=city] .input__sub").getText().trim();
        assertEquals(expectedText, actualText);

    }

    @Test
    public void shouldNotSendEmptyName() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Волгоград");
        String deliveryDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(deliveryDate);
        $("[data-test-id=phone] input").setValue("+79267777777");
        $("[data-test-id=agreement] span").click();
        $(withText("Забронировать")).click();
        String expectedText = "Поле обязательно для заполнения";
        String actualText = $("[data-test-id=name] .input__sub").getText().trim();
        assertEquals(expectedText, actualText);

    }

    @Test
    public void shouldNotSendLatinName() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Волгоград");
        String deliveryDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(deliveryDate);
        $("[data-test-id=name] input").setValue("Petrov Ivan");
        $("[data-test-id=phone] input").setValue("79267777777");
        $("[data-test-id=agreement] span").click();
        $(withText("Забронировать")).click();
        String expectedText = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actualText = $("[data-test-id=name] .input__sub").getText().trim();
        assertEquals(expectedText, actualText);
    }

    @Test
    public void shouldNotSendWrongPhone() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Волгоград");
        String deliveryDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(deliveryDate);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+7926777");
        $("[data-test-id=agreement] span").click();
        $(withText("Забронировать")).click();
        String expectedText = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actualText = $("[data-test-id=phone] .input__sub").getText().trim();
        assertEquals(expectedText, actualText);
    }

    @Test
    public void shouldNotSendEmptyPhone() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Волгоград");
        String deliveryDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(deliveryDate);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=agreement] span").click();
        $(withText("Забронировать")).click();
        String expectedText = "Поле обязательно для заполнения";
        String actualText = $("[data-test-id=phone] .input__sub").getText().trim();
        assertEquals(expectedText, actualText);
    }

    @Test
    public void shouldNotSendWithoutAgreement() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Волгоград");
        String deliveryDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(deliveryDate);
        $("[data-test-id=name] input").setValue("Иван Петров");
        $("[data-test-id=phone] input").setValue("+79267777777");

        $(withText("Забронировать")).click();
        String checkboxInvalid = $("[data-test-id=agreement].checkbox").getAttribute("className");
        assertTrue(checkboxInvalid.contains("input_invalid"));
    }


}