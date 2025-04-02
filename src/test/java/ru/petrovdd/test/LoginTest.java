package ru.petrovdd.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.petrovdd.page.FormPage;
import ru.petrovdd.page.LoginFormPage;

import java.time.Duration;

import static io.qameta.allure.Allure.*;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;

public class LoginTest {

    private final static String BASE_URL = "https://demoqa.com/login";

    static FormPage formPage;
    static WebDriver driver;

    private static LoginFormPage loginFormPage;

    private static final String USER_NAME = "petrovdd";
    private static final String PASSWORD = "2E7*gwV9$5YxhnN";
    private static final String AUTH_DATA_JSON = "{\"userName\":\"petrovdd\",\"password\":\"2E7*gwV9$5YxhnN\"}";

    /**
     * Инициализируем объект драйвера и объект класса FormPage
     * PageLoadStrategy - параметр в селениум, определяет стратегию загрузки страницы
     * Normal - ждем, пока страница полностью загрузится, по умолчанию
     * Eager - ждем пока не будет подгружен HTML-документ, на все остальное забиваем
     * None - Нет проверки загрузки ресурсов
     */
    @BeforeAll
    static void setub() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        //options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
        RestAssured.baseURI = "https://demoqa.com";

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        formPage = new FormPage(driver);

        loginFormPage = new LoginFormPage(driver);
    }

    @Test
    void successfulLoginWithUiTest() {
        step("Open page", () -> driver.get(BASE_URL));
        step("Get authorization", () -> {
            loginFormPage
                    .setUserName(USER_NAME)
                    .setPassword(PASSWORD)
                    .clickLoginButton()
                    .checkLoginUser(USER_NAME);
        });
    }

    @Test
    void loginWithApiTest() {
        step("Get authorization cookie by api and set it to browser", () -> {
            Response authResponce = given()
                    .log().uri()
                    .log().method()
                    .log().body()
                    .contentType(JSON)
                    .body(AUTH_DATA_JSON)
                    .when()
                    .post("/Account/v1/Login")
                    .then()
                    .log().status()
                    .log().body()
                    .extract().response();
        });
    }

    @Test
    public void whenUseCookieLoginTest() {
        Response authResponce = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(AUTH_DATA_JSON)
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().status()
                .log().body()
                .extract().response();

        given()
                .log().uri()
                .log().method()
                .log().body()
                .cookie("userID", authResponce.path("userId"))
                .cookie("expires", authResponce.path("expires"))
                .cookie("token", authResponce.path("token"))
                .when()
                .get("/profile")
                .then().statusCode(200);

        driver.get("https://demoqa.com/login");

        driver.manage().addCookie(new Cookie("userID", authResponce.path("userId")));
        driver.manage().addCookie(new Cookie("expires", authResponce.path("expires")));
        driver.manage().addCookie(new Cookie("token", authResponce.path("token")));

        driver.get("https://demoqa.com/profile");
        loginFormPage.checkLoginUser(USER_NAME);
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

}
