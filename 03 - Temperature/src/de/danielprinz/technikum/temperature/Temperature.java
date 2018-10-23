package de.danielprinz.technikum.temperature;

public class Temperature {

    private double celsius, fahrenheit, kelvin;
    private Temperature(TemperatureType temperatureType, double temperature) {
        if(temperatureType.equals(TemperatureType.CELSIUS)) {
            this.celsius    = temperature;
            this.fahrenheit = this.celsius * 1.8 + 32;
            this.kelvin     = this.celsius + 273.15;
        } else if(temperatureType.equals(TemperatureType.FAHRENHEIT)) {
            this.fahrenheit = temperature;
            this.celsius    = (this.fahrenheit - 32) / 1.8;
            this.kelvin     = this.celsius + 273.15;
        } else {
            this.kelvin     = temperature;
            this.celsius    = this.kelvin - 273.15;
            this.fahrenheit = this.celsius * 1.8 + 32;
        }
    }


    public double getCelsius() {
        return celsius;
    }

    public double getFahrenheit() {
        return fahrenheit;
    }

    public double getKelvin() {
        return kelvin;
    }

    public static Temperature fromCelsius(double celsius) {
        return new Temperature(TemperatureType.CELSIUS, celsius);
    }

    public static Temperature fromFahrenheit(double fahrenheit) {
        return new Temperature(TemperatureType.FAHRENHEIT, fahrenheit);
    }

    public static Temperature fromKelvin(double kelvin) {
        return new Temperature(TemperatureType.KELVIN, kelvin);
    }

    public static Temperature from(TemperatureType temperatureType, double temperature) {
        return new Temperature(temperatureType, temperature);
    }





    /*private static double celsiusToFahrenheit(double celsius) {
        return celsius * 1.8 + 32;
    }
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) / 1.8;
    }*/



    enum TemperatureType {
        CELSIUS, FAHRENHEIT, KELVIN
    }


    @Override
    public String toString() {
        return "Temperature{" +
                "celsius=" + celsius +
                ", fahrenheit=" + fahrenheit +
                ", kelvin=" + kelvin +
                '}';
    }
}
