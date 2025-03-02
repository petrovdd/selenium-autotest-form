package ru.petrovdd.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Класс, описывающий компонент календарь
 */
public class CalendarComponent {

    private final WebDriver driver;
    private static final String DATEPICKER = "react-datepicker__";

    /**
     * Конструктор класса CalendarComponent
     *
     * @param driver драйвер
     */
    public CalendarComponent(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Выбор даты: дня, месяца и года
     *
     * @param day   день
     * @param month месяц
     * @param year  год
     */
    public void setDate(String day, String month, String year) {
        Select selectYear = new Select(getSelectElementByClass("select", DATEPICKER + "year-select"));
        selectYear.selectByVisibleText(year);

        Select selectMonth = new Select(getSelectElementByClass("select", DATEPICKER + "month-select"));
        selectMonth.selectByVisibleText(month);

        String dayClassName = String.format(DATEPICKER + "day--0%s", day.length() > 1 ? day : "0" + day);
        WebElement selectDay = getSelectElementByClass("div", dayClassName);
        selectDay.click();
    }

    /**
     * Выбор даты: дня, месяца и года
     *
     * @param atr       наименование атрибута для поиска
     * @param className наименование класа
     * @return елемент
     */
    private WebElement getSelectElementByClass(String atr, String className) {
        return driver.findElement(By.xpath("//" + atr + "[contains(@class,'" + className + "')]"));
    }

}
