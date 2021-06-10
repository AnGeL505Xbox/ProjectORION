package ORION.Others;

import java.util.List;

public class User {
    private String name, CURP, password, username;
    private int age;
    private double height, weigth;
    private List<Sick> sicks;

    public User(String name, String CURP, String password, String username, int age, double height, double weigth, List<Sick> sicks) {
        this.name = name;
        this.CURP = CURP;
        this.password = password;
        this.username = username;
        this.age = age;
        this.height = height;
        this.weigth = weigth;
        this.sicks = sicks;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCURP() {
        return CURP;
    }
    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeigth() {
        return weigth;
    }
    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }

    public List<Sick> getSicks() {
        return sicks;
    }
    public void setSicks(List<Sick> sicks) {
        this.sicks = sicks;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public double getImc(){
        return (getWeigth()/(Math.pow(getHeight(),2)));
    }
}
