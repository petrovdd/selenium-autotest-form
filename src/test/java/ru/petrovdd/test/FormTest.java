package ru.petrovdd.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.petrovdd.page.FormPage;

import java.util.concurrent.TimeUnit;

/**
 * Класс для проведения теста формы
 */
class FormTest {

    private final static String BASE_URL = "https://demoqa.com/automation-practice-form";

    static FormPage formPage;
    static WebDriver driver;

    /**
     * Инициализируем объект драйвера и объект класса FormPage
     */
    @BeforeAll
    static void beforeAll() {
        driver = new ChromeDriver();
        formPage = new FormPage(driver);
    }

    /**
     * Позитивный тест на проверку заполнения полей формы
     */
    @Test
    void fullRegistration() {
        driver.get(BASE_URL);
        formPage.setFirstNameField("Alex")
                .setLastNameField("Smith")
                .setUserEmailField("user@mail.ru")
                .selectGenderWrapperField("Male")
                .setUserNumberField("79268130933")
                .setDateOfBirthInput("8", "November", "1992")
                .selectHobbies("Sports")
                .setUploadFile("C:\\selenium-autotest\\src\\test\\resources\\file.txt")
                .setCurrentAddress("USA")
                .setState("NCR")
                .setCity("Delhi")
                .submitClick()
                .checkResult("Student Name", "Alex Smith")
                .checkResult("Student Email", "user@mail.ru")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "7926813093")
                .checkResult("Date of Birth", "08 November,1992")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "file.txt")
                .checkResult("Address", "USA")
                .checkResult("State and City", "NCR Delhi");
        driver.quit();
    }

}
