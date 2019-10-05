package model;

public class SpecialCandle{
    private String color;
    private String size;
    private String essence;
    private double brightness_deg;

    // Constructor
    public SpecialCandle(String color, String size, String essence, double brightness_deg) {
        this.color = color;
        this.size = size;
        this.essence = essence;
        this.brightness_deg = brightness_deg;
    }

    // Getters and Setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getEssence() {
        return essence;
    }

    public void setEssence(String essence) {
        this.essence = essence;
    }

    public double getBrightness_deg() {
        return brightness_deg;
    }

    public void setBrightness_deg(double brightness_deg) {
        this.brightness_deg = brightness_deg;
    }

    @Override
    public String toString(){
        return  "\n" + 
                "        color: " + color + "\n" +
                "        size: " + size + "\n" +
                "        essence: " + essence + "\n" +
                "        brightness degree: " + brightness_deg; 
    }
}