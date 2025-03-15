package ru.petrovdd.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.petrovdd.enums.State;
import ru.petrovdd.page.FormPage;
import ru.petrovdd.util.RandomData;

import java.time.Duration;

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
     * PageLoadStrategy - параметр в селениум, определяет стратегию загрузки страницы
     * Normal - ждем, пока страница полностью загрузится, по умолчанию
     * Eager - ждем пока не будет подгружен HTML-документ, на все остальное забиваем
     * None - Нет проверки загрузки ресурсов
     */
    @BeforeAll
    static void beforeAll() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        formPage = new FormPage(driver);
    }

    //используется для сигнализации о том, что аннотированный метод должен быть выполнен перед каждым методом @Test
    @BeforeEach
    void openPage() {
        //driver.manage().window().maximize();
        //TODO Не идеальное решение, но методом тыка выделил время загрузки страницы 5 сек(?)
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
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
        //TODO добавить subject, в отдельную ветку т.к. много доработок
        formPage.setFirstNameField("Alex")
                .setLastNameField("Smith")
                .setUserEmailField("user@mail.ru")
                .selectGenderWrapperField("Male")
                .setUserNumberField("79268130933")
                .setDateOfBirthInput("8", "November", "1992")
                .selectHobbies("Sports")
                .setUploadFile("C:\\selenium-autotest\\src\\test\\resources\\csv\\test_data.csv")
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
                .checkResult("Picture", "csv/test_data.csv")
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
                .setUploadFile("C:\\selenium-autotest\\src\\test\\resources\\csv\\test_data.csv")
                .setCurrentAddress(randomData.getFullAddress())
                .setState(randomData.getRandomState())
                .setCity(randomData.getRandomCity())
                .submitClick()
                .checkResult("Student Name", randomData.getFirstName() + " " + randomData.getLastName())
                .checkResult("Student Email", randomData.getRandomEmail())
                .checkResult("Gender", randomData.getRandomGender())
                //Не пройдет, ошибка - в поле не влезает весь номер
                //.checkResult("Mobile", randomData.getPhoneNumber())
                //Не пройдет
                //.checkResult("Date of Birth",
                //        randomData.getDay() + " " + randomData.getMonthName() + "," + randomData.getYear())
                .checkResult("Hobbies", randomData.getRandomHobbies())
                .checkResult("Picture", "test_data.csv")
                .checkResult("Address", randomData.getFullAddress())
                .checkResult("State and City", randomData.getRandomState() + " " + randomData.getRandomCity())
                .submitCloseClick();
    }

    @ValueSource(strings = {//dataprovader
            "8996926091"
    })
    @ParameterizedTest(name = "Проверка заполнения на форме номера телефона {0}")
    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB")
    })
    void checkFullPersonNumber(String personNumber) {
        formPage.setFirstNameField("Alex")
                .setLastNameField("Smith")
                .selectGenderWrapperField("Male")
                .setUserNumberField(personNumber)
                .setState("NCR")
                .setCity("Delhi")
                .submitClick()
                .checkResult("Student Name", "Alex Smith")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", personNumber)
                .checkResult("State and City", "NCR Delhi")
                .submitCloseClick();
    }

    /*@CsvSource(value = {})*/
    @CsvFileSource(resources = "/csv/test_data.csv")
    @ParameterizedTest(name = "Проверка заполнения на форме страны {0} и города {1}")
    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB")
    })
    void checkSetStateAndCity(String state, String city) {
        formPage.setFirstNameField("Alex")
                .setLastNameField("Smith")
                .selectGenderWrapperField("Male")
                .setUserNumberField("7926813093")
                .setState(state)
                .setCity(city)
                .submitClick()
                .checkResult("Student Name", "Alex Smith")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "7926813093")
                .checkResult("State and City", state + " " + city)
                .submitCloseClick();
    }

    @EnumSource(State.class)
    @ParameterizedTest(name = "Проверка заполнения на форме страны {0}")
    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB")
    })
    void checkSetAllState(State state) {
        String city = RandomData.generateRandomCity(state.getNameState());
        formPage.setFirstNameField("Alex")
                .setLastNameField("Smith")
                .selectGenderWrapperField("Male")
                .setUserNumberField("7926813093")
                .setState(state.getNameState())
                .setCity(city)
                .submitClick()
                .checkResult("Student Name", "Alex Smith")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "7926813093")
                .checkResult("State and City", state.getNameState() + " " + city)
                .submitCloseClick();
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

}
