package tj.anor.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tj.anor.myapplicatio.model.Post;
import tj.anor.myapplicatio.remote.APIUtils;
import tj.anor.myapplicatio.remote.PostService;

public class ContentServiceActivity extends AppCompatActivity {

    TextView postIdValue;
    TextView userIdValue;
    TextView titleValue;
    TextView descriptionValue;
    Button button2;
    Button btnDelete;
    PostService postService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_service);
        btnDelete = findViewById(R.id.btnDelete);
        postService = APIUtils.getUserService();
        postIdValue = findViewById(R.id.postIdValue);
        userIdValue = findViewById(R.id.userIdValue);
        titleValue = findViewById(R.id.titleValue);
        descriptionValue = findViewById(R.id.descriptionValue);
        int postId = getIntent().getIntExtra("postId",1);
        Call<Post> call = postService.getById(postId);
        call.enqueue(new Callback<Post>() {

            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post = response.body();
                postIdValue.setText(post.getId().toString());
                userIdValue.setText(post.getUserId().toString());
                titleValue.setText(post.getTitle());
                descriptionValue.setText(post.getDescription());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContentServiceActivity.this, ContentActivity.class));
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePost(postId);

                Intent intent = new Intent(ContentServiceActivity.this, ServiceActivity.class);
                startActivity(intent);
            }
        });
    }

    public void deletePost(int id){
        Call<Post> call = postService.deletePost(id);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ContentServiceActivity.this, "Post deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}