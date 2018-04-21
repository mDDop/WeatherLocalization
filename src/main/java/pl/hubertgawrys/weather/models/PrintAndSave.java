package pl.hubertgawrys.weather.models;

import pl.hubertgawrys.weather.models.services.WeatherService;
import pl.hubertgawrys.weather.views.MainMenu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class PrintAndSave {

    public void saveDataToFile(String stringToSave) throws IOException {
        File file = new File("C:\\Users\\mddop\\Desktop\\AK\\Pogodynka.txt");
        byte[] byteArray = (stringToSave + "\r\n").getBytes();
        Files.write(file.toPath(), byteArray, StandardOpenOption.APPEND);
    }


}
