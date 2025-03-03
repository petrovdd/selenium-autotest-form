package ru.petrovdd.util;

import com.github.javafaker.Faker;

import java.util.*;

/**
 * Класс для генерации данных
 */
public class RandomData {

    private final Random random = new Random();
    private final String randomGender;
    private final String randomHobbies;
    private final String randomState;
    private final String randomCity;
    private final String randomFirstName;
    private final String randomLastName;
    private final String randomEmail;
    private final String randomPhoneNumber;
    private final String randomFullAddress;
    private final String randomMonthName;
    private final String randomYear;
    private final String randomDay;
    //TODO реализовать поле для subject

    /**
     * Конструктор класса, генерируем данные и инициализируем поля
     */
    public RandomData() {
        Faker faker = new Faker(new Locale("en-GB"));
        randomGender = generateRandomGender();
        randomHobbies = generateRandomHobbies();
        randomState = generateRandomState();
        randomCity = generateRandomCity();
        randomMonthName = generateMonthName();
        randomYear = String.valueOf(getRandomInt(1970, 2025));
        //TODO для дня генерация костыльная, подумать над разной верхней границей в зависимости от месяца
        randomDay = String.valueOf(getRandomInt(1, 28));
        randomFirstName = faker.name().firstName();
        randomLastName = faker.name().lastName();
        randomEmail = faker.internet().emailAddress();
        randomPhoneNumber = faker.phoneNumber().phoneNumber().replace(" ", "");
        randomFullAddress = faker.address().fullAddress();

    }

    /**
     * Получить пол
     */
    public String getRandomGender() {
        return randomGender;
    }

    /**
     * Получить хобби
     */
    public String getRandomHobbies() {
        return randomHobbies;
    }

    /**
     * Получить email
     */
    public String getRandomEmail() {
        return randomEmail;
    }

    /**
     * Получить страну
     */
    public String getRandomState() {
        return randomState;
    }

    /**
     * Получить город
     */
    public String getRandomCity() {
        return randomCity;
    }

    /**
     * Получить имя
     */
    public String getFirstName() {
        return randomFirstName;
    }

    /**
     * Получить фамилию
     */
    public String getLastName() {
        return randomLastName;
    }

    /**
     * Получить номер телефона
     */
    public String getPhoneNumber() {
        return randomPhoneNumber;
    }

    /**
     * Получить полный адрес проживания
     */
    public String getFullAddress() {
        return randomFullAddress;
    }

    /**
     * Получить наименование месяца
     */
    public String getMonthName() {
        return randomMonthName;
    }

    /**
     * Получить год
     */
    public String getYear() {
        return randomYear;
    }

    /**
     * Получить день
     */
    public String getDay() {
        return randomDay;
    }

    /**
     * Сгенерировать пол
     *
     * @return пол
     */
    private String generateRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return getRandomItemFromArray(genders);
    }

    /**
     * Сгенерировать хобби
     *
     * @return наименование хобби
     */
    private String generateRandomHobbies() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        //TODO хобби может быть несколько, подумать над выбором нескольких
        return getRandomItemFromArray(hobbies);
    }

    /**
     * Сгенерировать страну
     *
     * @return наименование страны
     */
    private String generateRandomState() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return getRandomItemFromArray(states);
    }

    /**
     * Сгенерировать город
     *
     * @return наименование города
     */
    private String generateRandomCity() {
        List<String> city = new ArrayList<>();
        if (randomState.equals("NCR")) {
            city.addAll(Arrays.asList("Delhi", "Gurgaon", "Noida"));
        }
        if (randomState.equals("Uttar Pradesh")) {
            city.addAll(Arrays.asList("Agra", "Lucknow", "Merrut"));
        }
        if (randomState.equals("Haryana")) {
            city.addAll(Arrays.asList("Karnal", "Panipat"));
        }
        if (randomState.equals("Rajasthan")) {
            city.addAll(Arrays.asList("Jaipur", "Jaiselmer"));
        }
        String[] array = new String[city.size()];
        return getRandomItemFromArray(city.toArray(array));
    }

    /**
     * Сгенерировать наименование месяца
     *
     * @return наименование месяца
     */
    private String generateMonthName() {
        //TODO попробовать вынести все это в список/списки
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return getRandomItemFromArray(months);
    }

    /**
     * Получить рандомный элемент массива
     *
     * @param array массив данных
     * @return рандомный элемент массива
     */
    private String getRandomItemFromArray(String[] array) {
        return array[random.nextInt(array.length)];
    }

    /**
     * Получить случайное сгенерированное число
     *
     * @param min минимальный порог числа
     * @param max максимальный порог числа
     * @return случайное сгенерированное число
     */
    private int getRandomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

}
