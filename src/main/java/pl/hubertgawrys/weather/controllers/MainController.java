package pl.hubertgawrys.weather.controllers;

import pl.hubertgawrys.weather.models.FileSaver;
import pl.hubertgawrys.weather.models.WeatherModel;
import pl.hubertgawrys.weather.models.WeatherObserver;
import pl.hubertgawrys.weather.models.services.WeatherService;
import pl.hubertgawrys.weather.views.MainMenu;

import java.util.List;
import java.util.regex.Pattern;

public class MainController implements WeatherObserver {
    private WeatherService weatherService = WeatherService.getInstance();
    private MainMenu mainMenu;

    public MainController() {
        mainMenu = new MainMenu();
        weatherService.registerObserver(this);
    }

    FileSaver fileSaver = new FileSaver();

    public int run(List<String> cityAndTime) {
        int i = 0;
        if (Pattern.matches("[A-ZĄĆÓĘŃŁŹŻĆŚ]*[a-zóńśęąćłźż]*", cityAndTime.get(0))) {
            if (cityAndTime.get(0).equals("Exit")) {
                i = 1;
            } else {
                if (cityAndTime.get(1).equals("W"))
                    weatherService.getWeatherQuality(cityAndTime.get(0));
                if (cityAndTime.get(1).equals("P"))
                    weatherService.getWeatherAfterFiveDays(cityAndTime.get(0));
                if (cityAndTime.get(1).equals("A"))
                    weatherService.getWeather(cityAndTime.get(0));
            }
        } else {
            mainMenu.printWrongData();
        }
        return i;
    }

    @Override
    public void informAboutWeatherIncome(WeatherModel weatherModel) {
        mainMenu.sendMessageToConsole(weatherModel.toString());
    }
}
