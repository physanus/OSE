package de.danielprinz.technikum.temperature;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;

public class Main extends Application {

    private static final String WINDOW_TITLE = "TempConverter";
    private static final String ICON_PATH = "thermometer_1f321.png";

    private static Main instance;
    private static Stage window;

    private static Label celsiusLabel;
    private static Label fahrenheitLabel;
    private static Label kelvinLabel;
    static TextField celsiusTextField;
    static TextField fahrenheitTextField;
    static TextField kelvinTextField;

    static Temperature temperature;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        instance = this;

        window = primaryStage;
        window.setTitle(WINDOW_TITLE);
        window.getIcons().add(new Image(Main.class.getResourceAsStream("../../../../" + ICON_PATH)));


        GridPane mainPane = new GridPane();
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        mainPane.setVgap(8);
        mainPane.setHgap(10);


        celsiusLabel = new Label("Celsius:");
        GridPane.setConstraints(celsiusLabel, 0, 0);

        celsiusTextField = new TextField();
        GridPane.setConstraints(celsiusTextField, 1, 0);
        celsiusTextField.setPrefWidth(165);
        celsiusTextField.textProperty().addListener(new NumberListener(celsiusTextField, Temperature.TemperatureType.CELSIUS));
        celsiusTextField.setOnAction(new TemperatureSetter());


        fahrenheitLabel = new Label("Fahrenheit:");
        GridPane.setConstraints(fahrenheitLabel, 0, 1);

        fahrenheitTextField = new TextField();
        GridPane.setConstraints(fahrenheitTextField, 1, 1);
        fahrenheitTextField.setPrefWidth(165);
        fahrenheitTextField.textProperty().addListener(new NumberListener(fahrenheitTextField, Temperature.TemperatureType.FAHRENHEIT));
        fahrenheitTextField.setOnAction(new TemperatureSetter());
            /*// enter
            celsiusTextField.setText(String.valueOf(temperature.getCelsius()));
            fahrenheitTextField.setText(String.valueOf(temperature.getFahrenheit()));
            kelvinTextField.setText(String.valueOf(temperature.getKelvin()));*/


        kelvinLabel = new Label("Kelvin:");
        GridPane.setConstraints(kelvinLabel, 0, 2);

        kelvinTextField = new TextField();
        GridPane.setConstraints(kelvinTextField, 1, 2);
        kelvinTextField.setPrefWidth(165);
        kelvinTextField.textProperty().addListener(new NumberListener(kelvinTextField, Temperature.TemperatureType.KELVIN));
        kelvinTextField.setOnAction(new TemperatureSetter());
            /*// enter
            celsiusTextField.setText(String.valueOf(temperature.getCelsius()));
            fahrenheitTextField.setText(String.valueOf(temperature.getFahrenheit()));
            kelvinTextField.setText(String.valueOf(temperature.getKelvin()));*/


        mainPane.getChildren().addAll(celsiusLabel, celsiusTextField, fahrenheitLabel, fahrenheitTextField, kelvinLabel, kelvinTextField);

        Scene scene = new Scene(mainPane);
        window.setScene(scene);
        window.show();

    }
}
