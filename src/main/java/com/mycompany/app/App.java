package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) {
        // Задание 1
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }

        // Задание 2
        System.out.println("\nЗадание 2:");
        Task2.getIP();

        // Задание 3
        System.out.println("\nЗадание 3:");
        Task3.getWeather();
    }
} 