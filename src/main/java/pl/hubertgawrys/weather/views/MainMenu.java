package pl.hubertgawrys.weather.views;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private Scanner scanner;

    public MainMenu() {
        scanner = new Scanner(System.in);

    }

    public void printMenu(){
        System.out.println("---------------------------------------------------------");
        System.out.println("--------------------------POGODYNKA----------------------");
    }

    public List<String> getCityFromUser() {
        System.out.println("-----Podaj miasto, bądź opuść program wpisując \"Exit\"---");
        String city =  scanner.nextLine();
        System.out.println("-Podaj A- aktualna temperatura, W - współrzędne miasta, P - 5 dni do przodu-");
        String time = scanner.nextLine();
        return Arrays.asList(city, time);
    }


    public void printWrongData(){
        System.out.println("----------------Wprowadziłeś błędne dane----------------");
    }

    public void sendMessageToConsole(String message){
        System.out.println(message);
    }
}
