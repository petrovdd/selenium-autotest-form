package ru.petrovdd.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Класс, описавыющий поле выбора страны/города
 */
public class SelectComponent {

    private final WebDriver driver;

    /**
     * Конструктор класса SelectComponent
     *
     * @param driver драйвер
     */
    public SelectComponent(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Выбор страны/города
     *
     * @param value текстовое значение для выбора нужного div'a
     */
    public void setState(String value) {
        driver.findElement(By.xpath("//div[@class=' css-11unzgr']/child::div[text()='" + value + "']")).click();
    }

}
