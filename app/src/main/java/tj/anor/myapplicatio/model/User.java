package tj.anor.myapplicatio.model;

import java.io.Serializable;

public class User implements Serializable {
    private String mid;
    private String name;
    private int age;
    private double balance;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User() {
    }

    public User(String id, String name, int age, double balance) {
        this.mid = id;
        this.name = name;
        this.age = age;
        this.balance = balance;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + mid + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
