package ru.petrovdd.properties.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс на использованием параметризированного теста
 */
class SystemPropertiesTest {

    @Test
    @DisplayName("Параметризированный тест")
    public void systemPropertiesTest() {
        String browser = System.getProperty("BROWSER");
        System.out.println("Browser: " + browser);
    }

}
