package tj.anor.myapplicatio;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ConnectionService extends AppCompatActivity implements ConnectionAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    ServiceAdapter adapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_service);


        RelativeLayout myTariff = findViewById(R.id.myTariff);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ArrayList<ConnectionServiceItem> connectionServiceItems = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
//        adapter = new ConnectionService(connectionServiceItems, ConnectionService.this);
        layoutManager = new LinearLayoutManager(ConnectionService.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick() {
    }


}