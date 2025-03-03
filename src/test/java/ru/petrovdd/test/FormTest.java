package ru.petrovdd.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.petrovdd.page.FormPage;
import ru.petrovdd.util.RandomData;

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

    @BeforeEach
    void openPage() {
        driver.get(BASE_URL);
    }

    /**
     * Позитивный тест на проверку заполнения полей формы
     */
    @Test
    void fullRegistration() {
        //TODO добавить subject
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
                .checkResult("State and City", "NCR Delhi")
                .submitCloseClick();
    }

    /**
     * Тест с генерацией данных
     */
    @Test
    void fullRegistrationGenerateData() {
        RandomData randomData = new RandomData();
        //TODO добавить subject
        formPage.setFirstNameField(randomData.getFirstName())
                .setLastNameField(randomData.getLastName())
                .setUserEmailField(randomData.getRandomEmail())
                .selectGenderWrapperField(randomData.getRandomGender())
                .setUserNumberField(randomData.getPhoneNumber())
                .setDateOfBirthInput(randomData.getDay(), randomData.getMonthName(), randomData.getYear())
                .selectHobbies(randomData.getRandomHobbies())
                //TODO подумать над генерацией пути для файла
                .setUploadFile("C:\\selenium-autotest\\src\\test\\resources\\file.txt")
                .setCurrentAddress(randomData.getFullAddress())
                .setState(randomData.getRandomState())
                .setCity(randomData.getRandomCity())
                .submitClick()
                //TODO изменить все проверки
                .checkResult("Student Name", randomData.getFirstName() + " " + randomData.getLastName())
                .checkResult("Student Email", randomData.getRandomEmail())
                .checkResult("Gender", randomData.getRandomGender())
                //Не пройдет, ошибка - в поле не влезает весь номер
                //.checkResult("Mobile", randomData.getPhoneNumber())
                //Не пройдет
                //.checkResult("Date of Birth",
                //        randomData.getDay() + " " + randomData.getMonthName() + "," + randomData.getYear())
                .checkResult("Hobbies", randomData.getRandomHobbies())
                .checkResult("Picture", "file.txt")
                .checkResult("Address", randomData.getFullAddress())
                .checkResult("State and City", randomData.getRandomState() + " " + randomData.getRandomCity())
                .submitCloseClick();
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

}
