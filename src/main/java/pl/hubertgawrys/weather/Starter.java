package pl.hubertgawrys.weather;

import pl.hubertgawrys.weather.controllers.MainController;
import pl.hubertgawrys.weather.views.MainMenu;

import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {
        MainMenu mainMenu = new MainMenu();
        mainMenu.printMenu();
        int i=0;
        do {
            i = new MainController().run(mainMenu.getCityFromUser());
        } while (i==0);
    }
}