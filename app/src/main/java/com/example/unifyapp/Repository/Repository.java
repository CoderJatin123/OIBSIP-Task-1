package com.example.unifyapp.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Repository {

    public  double getLength(double value, String unit_of_value, String target_unit) {
        // Conversion factors for different units
        final double CENTIMETER_TO_METER = 0.01;
        final double METER_TO_CENTIMETER = 100.0;
        final double METER_TO_KILOMETER = 0.001;
        final double METER_TO_MILES = 0.000621371;

        // Convert value to meters as the common unit for conversion
        double valueInMeters;
        switch (unit_of_value) {
            case "Centimeter":
                valueInMeters = value * CENTIMETER_TO_METER;
                break;
            case "Inch":
                valueInMeters = value * 0.0254;
                break;
            case "Meter":
                valueInMeters = value;
                break;
            case "Kilometer":
                valueInMeters = value * 1000.0;
                break;
            case "Miles":
                valueInMeters = value * 1609.344;
                break;
            default:
                throw new IllegalArgumentException("Invalid unit_of_value");
        }

        // Convert value from meters to the target unit
        double result;
        switch (target_unit) {
            case "Centimeter":
                result = valueInMeters * METER_TO_CENTIMETER;
                break;
            case "Inch":
                result = valueInMeters * 39.370;
                break;
            case "Meter":
                result = valueInMeters;
                break;
            case "Kilometer":
                result = valueInMeters * METER_TO_KILOMETER;
                break;
            case "Miles":
                result = valueInMeters * METER_TO_MILES;
                break;
            default:
                throw new IllegalArgumentException("Invalid target_unit");
        }

        return result;
    }

    public double getWeight(double value, String unit_of_value, String target_unit) {
        // Conversion factors for different units
        final double MILLIGRAM_TO_GRAM = 0.001;
        final double GRAM_TO_KILOGRAM = 0.001;
        final double KILOGRAM_TO_POUND = 2.20462;
        final double KILOGRAM_TO_TONNE = 0.001;

        // Convert value to grams as the common unit for conversion
        double valueInGrams;
        switch (unit_of_value) {
            case "Milligram":
                valueInGrams = value * MILLIGRAM_TO_GRAM;
                break;
            case "Gram":
                valueInGrams = value;
                break;
            case "Kilogram":
                valueInGrams = value * 1000.0;
                break;
            case "Pound":
                valueInGrams = value / KILOGRAM_TO_POUND; // Convert Pound to Kilograms
                break;
            case "Tonne":
                valueInGrams = value * 1000000.0;
                break;
            default:
                throw new IllegalArgumentException("Invalid unit_of_value");
        }

        // Convert value from grams to the target unit
        double result;
        switch (target_unit) {
            case "Milligram":
                result = valueInGrams * (1.0 / MILLIGRAM_TO_GRAM);
                break;
            case "Gram":
                result = valueInGrams;
                break;
            case "Kilogram":
                result = valueInGrams * GRAM_TO_KILOGRAM;
                break;
            case "Pound":
                result = valueInGrams * KILOGRAM_TO_POUND; // Convert Kilograms to Pound
                break;
            case "Tonne":
                result = valueInGrams * KILOGRAM_TO_TONNE;
                break;
            default:
                throw new IllegalArgumentException("Invalid target_unit");
        }

        // Round the result to 3 decimal places
        BigDecimal roundedResult = new BigDecimal(result).setScale(3, RoundingMode.HALF_UP);
        return result;
    }
    public static String formatAnswer(String unit, double value) {
        // Round the value to 2 decimal places
        String formattedValue = String.format("%.2f", value);

        // Determine the unit abbreviation based on the unit
        String unitAbbreviation;
        switch (unit) {
            case "Centimeter":
                unitAbbreviation = "cm";
                break;
            case "Inch":
                unitAbbreviation = "in";
                break;
            case "Meter":
                unitAbbreviation = "m";
                break;
            case "Kilometer":
                unitAbbreviation = "Km";
                break;
            case "Miles":
                unitAbbreviation = "mi";
                break;
            case "Milligram":
                unitAbbreviation = "mg";
                break;
            case "Gram":
                unitAbbreviation = "g";
                break;
            case "Kilogram":
                unitAbbreviation = "Kg";
                break;
            case "Pound":
                unitAbbreviation = "lb";
                break;
            case "Tonne":
                unitAbbreviation = "t";
                break;
            default:
                throw new IllegalArgumentException("Invalid unit");
        }

        // Combine the formatted value and unit abbreviation
        return formattedValue + " " + unitAbbreviation;
    }

}
