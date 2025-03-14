package ru.petrovdd.properties.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {

    @Test
    @DisplayName("Параметризированный тест")
    void systemPropertiesTest() {
        String browser = System.getProperty("browser");
        System.out.println("Browser: " + browser);
    }

}
