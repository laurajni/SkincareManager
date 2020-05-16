package ui;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static ui.SkincareRoutine.*;

public class MainMenuDisplay {
    public JPanel mainMenuPanel;
    private JButton routineButton;
    private JButton shelfButton;
    private JTextPane skincareManagerTextPane;
    private JTextPane weatherPane;
    private String weather = "unknown";

    MainMenuDisplay() {
        shelfButton.addActionListener(new ShelfButtonClicked());
        routineButton.addActionListener(new RoutineButtonClicked());
        weatherPane.setText("Weather: " + weatherString() + "\nPut on sunscreen if necessary!");
    }

    private String weatherString() {
        String vancouverWeatherQuery = "https://api.openweathermap.org/data/2.5/weather?id=6173331&units=metric&APPID=";
        String apiKey = "16c59bed769e4e860d7e0e2743266cce";
        String urlString = vancouverWeatherQuery + apiKey;
        String weatherData;
        try {
            URL url = new URL(urlString);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            weatherData = br.readLine();
            parseWeatherData(weatherData);

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }

    private void parseWeatherData(String weatherData) {
        JsonObject allWeatherObject = new JsonParser().parse(weatherData).getAsJsonObject();
        JsonArray weatherArray = allWeatherObject.getAsJsonArray("weather");
        JsonObject weatherDetails = weatherArray.get(0).getAsJsonObject();
        weather = weatherDetails.getAsJsonPrimitive("main").getAsString();
    }

    private class ShelfButtonClicked implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setVisible(false);
            shelfFrame = new JFrame();
            shelfFrame.setSize(1200,800);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            shelfFrame.setLocationRelativeTo(null);
            shelfFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ShelfDisplay shelfDisplay = new ShelfDisplay();
            shelfFrame.add(shelfDisplay.shelfDisplayPanel);
            shelfFrame.setVisible(true);
        }
    }

    private class RoutineButtonClicked implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setVisible(false);
            routineFrame = new JFrame();
            routineFrame.setSize(1200,800);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            routineFrame.setLocationRelativeTo(null);
            routineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            RoutineDisplay routineDisplay = new RoutineDisplay();
            routineFrame.add(routineDisplay.routineDisplayPanel);
            routineFrame.setVisible(true);
        }
    }

}
