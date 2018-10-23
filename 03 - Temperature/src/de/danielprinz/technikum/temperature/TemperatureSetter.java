package de.danielprinz.technikum.temperature;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by el17x002 on 26.09.2018.
 */
public class TemperatureSetter implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {

        if(Main.temperature == null) {
            Main.celsiusTextField.setText("0");
            Main.fahrenheitTextField.setText("0");
            Main.kelvinTextField.setText("0");
        } else {
            int caretPositionCelsius    = Main.celsiusTextField.getCaretPosition();
            int caretPositionFahrenheit = Main.fahrenheitTextField.getCaretPosition();
            int caretPositionKelvin     = Main.kelvinTextField.getCaretPosition();

            Main.celsiusTextField.setText(String.valueOf(Main.temperature.getCelsius()));
            Main.fahrenheitTextField.setText(String.valueOf(Main.temperature.getFahrenheit()));
            Main.kelvinTextField.setText(String.valueOf(Main.temperature.getKelvin()));

            Main.celsiusTextField.positionCaret(caretPositionCelsius);
            Main.fahrenheitTextField.positionCaret(caretPositionFahrenheit);
            Main.kelvinTextField.positionCaret(caretPositionKelvin);
        }



    }
}
