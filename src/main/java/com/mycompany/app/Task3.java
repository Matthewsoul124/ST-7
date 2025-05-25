package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task3 {
    public static void getWeather() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
            webDriver.get(url);
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String json_str = elem.getText();
            
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json_str);
            JSONObject hourly = (JSONObject) obj.get("hourly");
            
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");
            
            StringBuilder table = new StringBuilder();
            table.append("№\tДата/время\t\tТемпература\tОсадки (мм)\n");
            
            for (int i = 0; i < times.size(); i++) {
                String time = (String) times.get(i);
                Double temp = (Double) temperatures.get(i);
                Double rain = (Double) rains.get(i);
                
                table.append(String.format("%d\t%s\t%.1f°C\t\t%.2f\n", 
                    i + 1, time, temp, rain));
            }
            
            // Сохраняем в файл
            try (FileWriter writer = new FileWriter("result/forecast.txt")) {
                writer.write(table.toString());
            }
            
            System.out.println(table.toString());
            
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
} 