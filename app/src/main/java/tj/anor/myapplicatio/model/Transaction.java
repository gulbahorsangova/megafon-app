package tj.anor.myapplicatio.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

import tj.anor.myapplicatio.utils.Constants;

@Entity(tableName = Constants.TABLE_NAME_TRANSACTION)
public class Transaction implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double price;
    private String opr;
    private String type;
    private String create_date;

    public Transaction() {
    }

    public Transaction(double price, String opr, String type, String create_date) {
        this.price = price;
        this.opr = opr;
        this.type = type;
        this.create_date = create_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOpr() {
        return opr;
    }

    public void setOpr(String opr) {
        this.opr = opr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(opr, that.opr) &&
                Objects.equals(type, that.type) &&
                Objects.equals(create_date, that.create_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, opr, type, create_date);
    }
}
