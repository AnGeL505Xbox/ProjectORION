package ORION.Others;

import java.util.List;

public class Sick {
    private String name;
    private List<String> sick;
    private double isDangerous;

    public Sick(String name, List<String> sick, double isDangerous) {
        this.name = name;
        this.sick = sick;
        this.isDangerous = isDangerous;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSick() {
        return sick;
    }
    public void setSick(List<String> sick) {
        this.sick = sick;
    }

    public double getIsDangerous() {
        return isDangerous;
    }
    public void setIsDangerous(double isDangerous) {
        this.isDangerous = isDangerous;
    }
}
