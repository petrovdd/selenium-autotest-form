package ru.petrovdd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginFormPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='userName']")
    WebElement usernameInput;

    @FindBy(xpath = "//*[@id='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//*[@id='login']")
    WebElement loginButton;

    @FindBy(xpath = "//*[@id='newUser']")
    WebElement newUserButton;

    @FindBy(xpath = "//div[@class='profile-wrapper']")
    WebElement profileWrapper;

    public LoginFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginFormPage setUserName(String userName) {
        usernameInput.sendKeys(userName);
        return this;
    }

    public LoginFormPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginFormPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    public LoginFormPage clickNewUserButton() {
        newUserButton.click();
        return this;
    }

    public LoginFormPage checkLoginUser(String username) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        By condition = By.xpath("/descendant::label[text()='petrovdd']");
        try {
            profileWrapper.findElement(condition);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return this;
    }

}
