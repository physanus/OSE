package de.danielprinz.technikum.temperature;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Created by el17x002 on 26.09.2018.
 */
public class NumberListener implements ChangeListener<String> {

    private final TextField parent;
    private final Temperature.TemperatureType temperatureType;
    public NumberListener(TextField parent, Temperature.TemperatureType temperatureType) {
        this.parent = parent;
        this.temperatureType = temperatureType;
    }


    @Override
    public void changed(ObservableValue observable, String oldValue, String newValue) {

        if (!newValue.matches("-?\\d*\\.?\\d*")) {

            int index;
            newValue = newValue.replaceAll("[^\\d|-|.]", ""); // remove everything except numbers, - and .

            // replace multiple .
            while((index = newValue.lastIndexOf(".")) != newValue.indexOf("."))
                newValue = newValue.substring(0, index) + newValue.substring(index + 1);

            // replace multiple -
            while(newValue.lastIndexOf("-") != -1 && (index = newValue.lastIndexOf("-")) != 0)
                newValue = newValue.substring(0, index) + newValue.substring(index + 1);

            parent.setText(newValue);
        }

        Main.temperature = Temperature.from(temperatureType, Double.parseDouble(newValue));

    }
}
