package ru.petrovdd.page;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.petrovdd.components.CalendarComponent;
import ru.petrovdd.components.SelectComponent;

import java.time.Duration;

/**
 * Класс представления формы решистрации
 */
public class FormPage {

    public WebDriver driver;
    private final CalendarComponent calendar;
    private final SelectComponent stateComponent;

    /**
     * Конструктор класса, нужен для инициализации полей класса
     *
     * @param driver драйвер
     */
    public FormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        calendar = new CalendarComponent(driver);
        stateComponent = new SelectComponent(driver);

    }

    /**
     * Поле first name
     */
    @FindBy(css = "#firstName")
    private WebElement firstNameField;

    /**
     * Поле имени last name
     */
    @FindBy(css = "#lastName")
    private WebElement lastNameField;

    /**
     * Поле email
     */
    @FindBy(css = "#userEmail")
    private WebElement userEmailField;

    /**
     * Поле выбора пола
     */
    @FindBy(css = "#genterWrapper")
    private WebElement genderWrapperField;

    /**
     * Поле номера телефона
     */
    @FindBy(css = "#userNumber")
    private WebElement userNumberField;

    /**
     * Поле выбора даты рождения
     */
    @FindBy(css = "#dateOfBirthInput")
    private WebElement dateOfBirthInputField;

    /**
     * Поле выбора предметов
     */
    @FindBy(css = "#subjectsInput")
    private WebElement subjectsInputField;

    /**
     * Поле выбора хобби спорт
     */
    @FindBy(xpath = "//*[@id='hobbies-checkbox-1']/parent::*/child::label[text()='Sports']")
    private WebElement hobbiesSportsField;

    /**
     * Поле выбора хобби чтение
     */
    @FindBy(xpath = "//*[@id='hobbies-checkbox-2']/parent::*/child::label[text()='Reading']")
    private WebElement hobbiesReadingField;

    /**
     * Поле выбора хобби музыка
     */
    @FindBy(xpath = "//*[@id='hobbies-checkbox-3']/parent::*/child::label[text()='Music']")
    private WebElement hobbiesMusicField;

    /**
     * Поле выбора файла
     */
    @FindBy(css = "#uploadPicture")
    private WebElement uploadPictureField;

    /**
     * Поле выбора адреса
     */
    @FindBy(css = "#currentAddress")
    private WebElement currentAddressField;

    /**
     * Поле выбора страны
     */
    @FindBy(xpath = "//*[@id='state']/descendant::input[@id='react-select-3-input']")
    private WebElement state;

    /**
     * Поле выбора города
     */
    @FindBy(xpath = "//*[@id='city']/descendant::input[@id='react-select-4-input']")
    private WebElement city;

    /**
     * Кнопка введения данных
     */
    @FindBy(css = "#submit")
    private WebElement submitButton;

    /**
     * Конечная таблица данных
     */
    @FindBy(css = ".table-responsive")
    private WebElement tableResponsive;

    /**
     * Ввод имени
     *
     * @param firstName строкое имя
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage setFirstNameField(@NonNull String firstName) {
        firstNameField.sendKeys(firstName);
        return this;
    }

    /**
     * Ввод фамилия
     *
     * @param lastName строкое фамилия
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage setLastNameField(@NonNull String lastName) {
        lastNameField.sendKeys(lastName);
        return this;
    }

    /**
     * Ввод email
     *
     * @param userEmail строкое email
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage setUserEmailField(@NonNull String userEmail) {
        userEmailField.sendKeys(userEmail);
        return this;
    }

    /**
     * Ввод пола
     *
     * @param gender пол
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage selectGenderWrapperField(@NonNull String gender) {
        genderWrapperField.findElement(By.xpath("//label[text()='" + gender + "']")).click();
        return this;
    }

    /**
     * Ввод номера телефона пользователя
     *
     * @param userNumber строкойвый номер телефона
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage setUserNumberField(@NonNull String userNumber) {
        userNumberField.sendKeys(userNumber);
        return this;
    }

    /**
     * Ввод предметов пользователя
     *
     * @param value предмет
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage setSubjectsInputField(@NonNull String value) {
        subjectsInputField.click();
        subjectsInputField.sendKeys(value);
        return this;
    }

    /**
     * Ввод адреса пользователя
     *
     * @param value адрес
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage setCurrentAddress(@NonNull String value) {
        currentAddressField.sendKeys(value);
        return this;
    }

    /**
     * Ввод файла
     *
     * @param path путь к файлу
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage setUploadFile(@NonNull String path) {
        uploadPictureField.sendKeys(path);
        return this;
    }

    /**
     * Выбор страны
     *
     * @param value страна
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage setState(@NonNull String value) {
        state.sendKeys(value);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        stateComponent.setState(value);
        return this;
    }

    /**
     * Выбор города
     *
     * @param value город
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage setCity(@NonNull String value) {
        city.sendKeys(value);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        stateComponent.setState(value);
        return this;
    }

    /**
     * Выбор даты рождения
     *
     * @param day   день рождения
     * @param month месяц рождения
     * @param year  год рождения
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage setDateOfBirthInput(@NonNull String day, @NonNull String month, @NonNull String year) {
        dateOfBirthInputField.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        calendar.setDate(day, month, year);
        return this;
    }

    /**
     * Нажать на кнопку "Submit click"
     *
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage submitClick() {
        submitButton.click();
        return this;
    }

    /**
     * Выбор хобби
     *
     * @param value название хобби
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage selectHobbies(@NonNull String value) {
        switch (value) {
            case "Sports":
                hobbiesSportsField.click();
                break;
            case "Reading":
                hobbiesReadingField.click();
                break;
            case "Music":
                hobbiesMusicField.click();
                break;
        }
        return this;
    }

    /**
     * Проверить результат
     *
     * @param key   название проверяемого атрибута
     * @param value проверяемое значение
     * @return ссылка на текущий объект класса FormPage
     */
    public FormPage checkResult(@NonNull String key, @NonNull String value) {
        By condition = By.xpath("//td[text()='" + key + "']/parent::*/child::td[text()='" + value + "']");
        try {
            tableResponsive.findElement(condition);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return this;
    }

}
