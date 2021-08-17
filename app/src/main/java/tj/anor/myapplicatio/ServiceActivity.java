package tj.anor.myapplicatio;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tj.anor.myapplicatio.model.Post;
import tj.anor.myapplicatio.model.ServiceItem;
import tj.anor.myapplicatio.remote.APIUtils;
import tj.anor.myapplicatio.remote.PostService;

public class    ServiceActivity extends AppCompatActivity implements ServiceAdapter.OnItemClickListener{

    RecyclerView recyclerView ;
    ServiceAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private PostService postService;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postService = APIUtils.getUserService();
        setContentView(R.layout.activity_service);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceActivity.this, AddActivity.class));
            }
        });

        RelativeLayout myTariff = findViewById(R.id.myTariff);

        myTariff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceActivity.this, ConnectionService.class));
            }
        });

        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Call<List<Post>> call = postService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    Collections.sort(posts, (o1, o2) -> Long.compare(o1.getId(), o2.getId()));
                    recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setHasFixedSize(true);
                    adapter = new ServiceAdapter(new ArrayList<>(posts), ServiceActivity.this);
                    layoutManager = new LinearLayoutManager(ServiceActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(ServiceActivity.this);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        /*FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("services")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<ServiceItem> serviceItems = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                serviceItems.add(document.toObject(ServiceItem.class));
                            }
                            recyclerView = findViewById(R.id.recyclerView);
                            recyclerView.setHasFixedSize(true);
                            adapter = new ServiceAdapter(serviceItems, ServiceActivity.this);
                            layoutManager = new LinearLayoutManager(ServiceActivity.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(ServiceActivity.this);
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });*/

    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(Long id) {
        Intent intent = new Intent(this, ContentServiceActivity.class);
        intent.putExtra("postId", id.intValue());
        startActivity(intent);
    }
}