package pl.hubertgawrys.weather.models.services;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.hubertgawrys.weather.controllers.MainController;
import pl.hubertgawrys.weather.models.PrintAndSave;
import pl.hubertgawrys.weather.models.Utils;
import pl.hubertgawrys.weather.models.WeatherModel;
import pl.hubertgawrys.weather.models.WeatherObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getInstance() {
        return ourInstance;
    }

    private ExecutorService executorService;
    private MainController mainController;
    private static List<WeatherObserver> listOfObservers;
    private PrintAndSave printAndSave;
    public void registerObserver(WeatherObserver weatherObserver) {
        listOfObservers.add(weatherObserver);
    }

    private WeatherService() {
        listOfObservers = new ArrayList<>();
        executorService = Executors.newSingleThreadExecutor();
        printAndSave = new PrintAndSave();
    }

    public void getWeather(String city) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String websiteResponse = Utils.readWebsiteContent("http://api.openweathermap.org/data/2.5/weather?q=" + city + ",PL&APPID=b874d92729c25067bab28bf23ee81698");
                String description = null;
                int temperature;
                int pressure;
                int humidity;
                int clouds;

                JSONObject root = new JSONObject(websiteResponse);
                JSONArray weatherObcject = root.getJSONArray("weather");
                for (int i = 0; i < weatherObcject.length(); i++) {
                    JSONObject elementInArray = weatherObcject.getJSONObject(i);
                    description = elementInArray.getString("main");
                }
                JSONObject jsonMain = root.getJSONObject("main");
                temperature = (int) jsonMain.getFloat("temp");
                pressure = (int) jsonMain.getFloat("pressure");
                humidity = (int) jsonMain.getFloat("humidity");
                JSONObject jsonClouds = root.getJSONObject("clouds");
                clouds = (int) jsonClouds.getFloat("all");

                WeatherModel weatherModel = new WeatherModel.Builder(city)
                        .setClouds(clouds)
                        .setHumidity(humidity)
                        .setPressure(pressure)
                        .setTemperature(temperature - 273)
                        .setWeatherComment(description)
                        .build();

                notifyObservers(weatherModel);
            }
        });
    }

    public void getWeatherAfterFiveDays(String city) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String websiteResponse = Utils.readWebsiteContent("http://api.openweathermap.org/data/2.5/forecast?q=" + city + "");
                String description = null;
                int temperature;
                int pressure;
                int humidity;
                int clouds;

                JSONObject root = new JSONObject(websiteResponse);
                JSONArray listOfWeathers = root.getJSONArray("list");
                JSONObject lastDayInForecast = listOfWeathers.getJSONObject(listOfWeathers.length() - 1);
                JSONArray weatherObcject = lastDayInForecast.getJSONArray("weather");
                for (int i = 0; i < weatherObcject.length(); i++) {
                    JSONObject elementInArray = weatherObcject.getJSONObject(i);
                    description = elementInArray.getString("main");
                }
                JSONObject jsonMain = lastDayInForecast.getJSONObject("main");
                temperature = (int) jsonMain.getFloat("temp");
                pressure = (int) jsonMain.getFloat("pressure");
                humidity = (int) jsonMain.getFloat("humidity");
                JSONObject jsonClouds = lastDayInForecast.getJSONObject("clouds");
                clouds = (int) jsonClouds.getFloat("all");

                WeatherModel weatherModel = new WeatherModel.Builder(city)
                        .setClouds(clouds)
                        .setHumidity(humidity)
                        .setPressure(pressure)
                        .setTemperature(temperature - 273)
                        .setWeatherComment(description)
                        .build();

                notifyObservers(weatherModel);
            }
        });
    }

    public void getWeatherQuality(String city) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int lat;
                int lng;
                double value = 0;
                double precision = 0;
                int pressure = 0;
                String placeResponse = Utils.readWebsiteContent("https://maps.googleapis.com/maps/api/geocode/json?address=" + city + "");
                // GETTING LOCATION FROM GOOGLE API
                JSONObject root = new JSONObject(placeResponse);
                JSONArray resultsArray = root.getJSONArray("results");
                JSONObject resultObject = resultsArray.getJSONObject(0);
                JSONObject geometry = resultObject.getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");
                lat = location.getInt("lat");
                lng = location.getInt("lng");

                // GETTING AIR QUALITY FROM OPENWEATHER API
                String websiteResponse = Utils.readWebsiteContent("http://api.openweathermap.org/pollution/v1/co/" + String.valueOf(lat) + "," + String.valueOf(lng) + "/2018Z.json?appid=b874d92729c25067bab28bf23ee81698");
                JSONObject rootWeather = new JSONObject(websiteResponse);
                JSONArray data = rootWeather.getJSONArray("data");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(city + ", Lat: " + lat + " Lng: " + lng + " ");
                for (int i = 0; i < data.length(); i++) {
                    if (data.getJSONObject(i).getDouble("pressure") < 250) {
                        pressure = data.getJSONObject(i).getInt("pressure");
                        value = data.getJSONObject(i).getDouble("value");
                        precision = data.getJSONObject(i).getInt("precision");
                        break;
                    }
                }
                WeatherModel weatherModel = new WeatherModel.Builder(city)
                        .setValue(value)
                        .setPressure(pressure)
                        .setPrecision(precision)
                        .build();

                notifyObservers(weatherModel);
            }
        });
    }

    private void notifyObservers(WeatherModel weatherModel) {
        for (WeatherObserver listOfObserver : listOfObservers) {
            listOfObserver.informAboutWeatherIncome(weatherModel);
            System.out.println(listOfObserver.getClass().toString());
            System.out.println(listOfObservers.size());
            try {
                printAndSave.saveDataToFile(weatherModel.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
