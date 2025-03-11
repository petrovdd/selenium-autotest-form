package ru.petrovdd.test;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.petrovdd.pojo.Employee;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FileTest {

    ObjectMapper objectMapper = new ObjectMapper();
    private final ClassLoader cs = FileTest.class.getClassLoader();

    @Test
    @DisplayName("Проверка и чтение полей PDF файла в Zip архиве")
    void pdfFileParsingTest() throws IOException {
        try (ZipInputStream zips = new ZipInputStream(Objects.requireNonNull(cs.getResourceAsStream("zip/files.zip")), StandardCharsets.ISO_8859_1)) {
            ZipEntry entry;
            String name;
            while ((entry = zips.getNextEntry()) != null) {
                name = entry.getName();
                if (name.contains("18.pdf")) {
                    PDF pdf = new PDF(zips);
                    assertThat("Домашнее задание").isEqualTo(pdf.text);
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка и чтение полей Xlsx файла в Zip архиве")
    void xlsxFileParsingTest() throws IOException {
        try (ZipInputStream zips = new ZipInputStream(Objects.requireNonNull(cs.getResourceAsStream("zip/files.zip")), StandardCharsets.ISO_8859_1)) {
            ZipEntry entry;
            String name;
            while ((entry = zips.getNextEntry()) != null) {
                name = entry.getName();
                if (name.contains("test-cases.xlsx")) {
                    XLS xsl = new XLS(zips);
                    String xlsFile = xsl.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue();
                    System.out.println(xlsFile);
                    assertThat("Заполнение данных на форме сгенерированными значениями").isEqualTo(xlsFile);
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка и чтение полей CSV файла в Zip архиве")
    void csvFileParsingTest() throws Exception {
        try (ZipInputStream zips = new ZipInputStream(Objects.requireNonNull(cs.getResourceAsStream("zip/files.zip")), StandardCharsets.ISO_8859_1)) {
            ZipEntry entry;
            String name;
            while ((entry = zips.getNextEntry()) != null) {
                name = entry.getName();
                if (name.contains("filename.csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zips));
                    List<String[]> arr = reader.readAll();
                    if (!arr.isEmpty()) {
                        assertThat(new String[]{"Selenide", " Selenium"}).isEqualTo(arr.get(0));
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка и чтение полей файла JSON")
    void jsonFileParsingTest() throws IOException {
        File file = new File("src/test/resources/json/employee.json");//
        Employee employee = objectMapper.readValue(file, Employee.class);

        assertThat(employee.getAge()).isEqualTo(44);
        assertThat(employee.getLastName()).isEqualTo("Simpson");
        assertThat(employee.getFirstName()).isEqualTo("Homer");
    }

}
