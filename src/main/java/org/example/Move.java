package org.example;

//import org.example.database.GsonParser;

import java.util.ArrayList;
import java.util.HashSet;

public class Move {

    private static ArrayList<String> cities;  // ідея підказує щоб доставити static
    private static HashSet<String> usedCities;  // ідея підказує щоб доставити static
    private String lastCity;


    public Move() {
        cities = new ArrayList<>();
        usedCities = new HashSet<>();
//        GsonParser gsonParser = new GsonParser();
//        cities.addAll(gsonParser.getCityNames());

    }

    public void playGame(String input) {

        // Хід гравця

//        usedCities.add(input);
//        lastCity = input;


            if (!cities.contains(input)) {
                System.out.println("інше місто");
            }


        if (isUserMoveValid(input) || lastCity != null) {
            usedCities.add(input);
            lastCity = input;
        } // else if (input.equals("I give up")) {
//            // повідомлення в інтерфейс про програш гравця
    }
//        }




    public String getComputerMove() {


        String nextCity = "";

        for (String city : cities) {
            if (isUserMoveValid(city)) {
                nextCity = city;
                break;
            }
        }

        lastCity = nextCity;
        usedCities.add(nextCity);
        return nextCity;
    }

    public String skip() {
        return getComputerMove();
    }


    public boolean isUserMoveValid(String userCity) {
        if (usedCities.isEmpty()) {
            // Перший хід гравця може бути будь-яким містом
            return cities.contains(userCity);
        } else {
            // Перевірка наступних ходів гравця
            if (usedCities.contains(userCity)) {
                return false;
            }

            if (!userCity.toLowerCase().startsWith(lastCity.substring(lastCity.length() - 1).toLowerCase())) {
                return false;
            }

            return cities.contains(userCity);
        }
    }


}
