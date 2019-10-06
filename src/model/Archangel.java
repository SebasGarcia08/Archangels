package model;

import java.util.Arrays;

/**
 * This class defines the attributes and methods for the Archangel object.
 */

public class Archangel {
    public static final String NAME_SUFFIX = "el";

    private String name;
    private String[] powers;
    private String prayer;
    private int celebration_day;
    private int celebration_month;
    private SpecialCandle candle;

    public Archangel(String name, String[] powers, String prayer, int celebration_day, int celebration_month,
            SpecialCandle candle) {
        this.name = name;
        this.powers = powers;
        this.prayer = prayer;
        this.celebration_day = celebration_day;
        this.celebration_month = celebration_month;
        this.candle = candle;
    }

    /**
     * Searches a requested power within the ones of the arhcangel.
     * <b>post:</b> there will be a method for consulting any power requested.
     * @param power String, can be null or empty.
     * @return null if power doesn't exist in array, otherwise returns the power found.
     */
    public String searchPower(String power) {
        boolean found = false;
        String power_found = null;
        for (int i = 0; i < powers.length && !found; i++) {
            if (powers[i] != null && powers[i].equals(power)) {
                found = true;
                power_found = powers[i];
            }
        }
        return power_found;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPowers() {
        return powers;
    }

    public void setPowers(String[] powers) {
        this.powers = powers;
    }

    public String getPrayer() {
        return prayer;
    }

    public void setPrayer(String prayer) {
        this.prayer = prayer;
    }

    public int getCelebrationMonth() {
        return celebration_month;
    }

    public void setCelebrationMonth(int celebration_month) {
        this.celebration_month = celebration_month;
    }

    public int getCelebrationDay() {
        return celebration_day;
    }

    public void setCelebrationDay(int celebration_day) {
        this.celebration_day = celebration_day;
    }

    public SpecialCandle getCandle() {
        return candle;
    }

    public void setCandle(SpecialCandle candle) {
        this.candle = candle;
    }

    // To String for deploying infomation of the angel.
    @Override
    public String toString() {
        return     "\n" +name + " \n" +
                   "    celebration day:" + celebration_day + "\n" +
                   "    celebration month: " + celebration_month + "\n" + 
                   "    powers: " + Arrays.toString(powers) + "\n" + 
                   "    prayer: " + prayer + "\n" +
                   "    Candle: " + candle.toString();
    }

}