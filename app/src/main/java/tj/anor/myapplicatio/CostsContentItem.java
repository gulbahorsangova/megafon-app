package tj.anor.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CostsContentItem {
    private String type;
    private String create_date;
    private int price;


    public CostsContentItem() {
    }

    public CostsContentItem(String type, String create_date, int price) {
        this.type = type;
        this.create_date = create_date;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getCreate_date() {
        return create_date;
    }

    public int getPrice() {
        return price;
    }
}