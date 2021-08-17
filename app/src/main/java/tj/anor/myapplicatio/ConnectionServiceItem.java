package tj.anor.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ConnectionServiceItem {
    private String title;
    private String description;

    public ConnectionServiceItem() {
    }

    public ConnectionServiceItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}