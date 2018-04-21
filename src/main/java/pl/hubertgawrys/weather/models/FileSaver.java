package pl.hubertgawrys.weather.models;

import pl.hubertgawrys.weather.models.services.WeatherService;
import pl.hubertgawrys.weather.views.MainMenu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class FileSaver {
    MainMenu mainMenu = new MainMenu();
/*
    public void saveAndPrintTheData(WeatherService weatherService, String city, String option) throws IOException {
        if (option.equals("A")) {
            mainMenu.sendMessageToConsole(w);
            saveDataToFile(weatherService.getWeather(city).toString());
        } else if (option.equals("W")){
            mainMenu.sendMessageToConsole(weatherService.getWeatherQuality(city).toStringQuality());
            saveDataToFile(weatherService.getWeatherQuality(city).toStringQuality());
        } else if (option.equals("P")){
            mainMenu.sendMessageToConsole(weatherService.getWeatherAfterFiveDays(city).toString());
            saveDataToFile(weatherService.getWeatherAfterFiveDays(city).toString());
        }
    }
*/

    public void saveDataToFile(String stringToSave) throws IOException {
        File file = new File("C:\\Users\\mddop\\Desktop\\AK\\WeatherLocalization.txt");
        byte[] byteArray = (stringToSave + "\r\n").getBytes();
        Files.write(file.toPath(), byteArray, StandardOpenOption.APPEND);
    }

}
