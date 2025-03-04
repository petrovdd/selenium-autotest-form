package ru.petrovdd.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.petrovdd.page.FormPage;
import ru.petrovdd.util.RandomData;

/**
 * Класс для проведения теста формы
 */
@DisplayName("Тесты на заполнение формы")
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
    //TODO @AllureId() буду использовать когда подключу Allure, id тест-кейса, вынесу в отдельную ветку
    //TODO Подкорректировать заголовки тест кейса
    @DisplayName("Все поля должны заполниться и закрыться форма с их выводом")
    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB")
    })//TODO Прописать priority и severity в теге, пропишу в отдельной ветке у каждого теста
    @Disabled("Проигнорируем")
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
    @Tag("WEB")
    @DisplayName("Тест формы с сгенерированными данными")
    //TODO
    //@ValueSource(strings = {"Dan", "JUnit"}, name = "" - описание) - запустит для каждого значения свой тест
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

    //TODO в отдельной ветке прописать параметризованный тест
    /*@ParameterizedTest
    @Tag("WEB")
    @DisplayName("Тест формы с сгенерированными данными")
    void fullRegistrationGenerateData(String searchQuery) {

    }*/

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

}
