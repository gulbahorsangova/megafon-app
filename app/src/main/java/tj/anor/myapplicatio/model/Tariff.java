package tj.anor.myapplicatio.model;

import java.io.Serializable;
import java.util.Objects;

public class Tariff implements Serializable {
    private String id;
    private double internet_amount;
    private double internet_social;
    private int duration;
    private long minutsInMeg;
    private long minutsOther;
    private double price;
    private String title;

    public Tariff() {
    }

    public Tariff(String id, double internet_amount, double internet_social, int duration,
                  long minutsInMeg, long minutsOther, double price, String title) {
        this.id = id;
        this.internet_amount = internet_amount;
        this.internet_social = internet_social;
        this.duration = duration;
        this.minutsInMeg = minutsInMeg;
        this.minutsOther = minutsOther;
        this.price = price;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getInternet_amount() {
        return internet_amount;
    }

    public void setInternet_amount(double internet_amount) {
        this.internet_amount = internet_amount;
    }

    public double getInternet_social() {
        return internet_social;
    }

    public void setInternet_social(double internet_social) {
        this.internet_social = internet_social;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getMinutsInMeg() {
        return minutsInMeg;
    }

    public void setMinutsInMeg(long minutsInMeg) {
        this.minutsInMeg = minutsInMeg;
    }

    public long getMinutsOther() {
        return minutsOther;
    }

    public void setMinutsOther(long minutsOther) {
        this.minutsOther = minutsOther;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Double.compare(tariff.internet_amount, internet_amount) == 0 &&
                Double.compare(tariff.internet_social, internet_social) == 0 &&
                duration == tariff.duration &&
                minutsInMeg == tariff.minutsInMeg &&
                minutsOther == tariff.minutsOther &&
                Double.compare(tariff.price, price) == 0 &&
                Objects.equals(id, tariff.id) &&
                Objects.equals(title, tariff.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, internet_amount, internet_social, duration, minutsInMeg, minutsOther, price, title);
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id='" + id + '\'' +
                ", internet_amount=" + internet_amount +
                ", internet_social=" + internet_social +
                ", duration=" + duration +
                ", minutsInMeg=" + minutsInMeg +
                ", minutsOther=" + minutsOther +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }
}
