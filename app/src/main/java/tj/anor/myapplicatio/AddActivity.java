package tj.anor.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tj.anor.myapplicatio.model.Post;
import tj.anor.myapplicatio.remote.APIUtils;
import tj.anor.myapplicatio.remote.PostService;

public class AddActivity extends AppCompatActivity {

    private PostService postService;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button savePost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        postService = APIUtils.getUserService();
        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        savePost = findViewById(R.id.savePost);

        savePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = new Post();
                post.setTitle(titleEditText.getText().toString());
                post.setDescription(descriptionEditText.getText().toString());
                post.setUserId(2L);
                Call<Post> call = postService.addPost(post);
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(response.isSuccessful()){
                            Log.i("post", response.body().toString());
                            Toast.makeText(AddActivity.this, "Post created successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddActivity.this, ServiceActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.e("ERROR: ", t.getMessage());
                    }
                });
            }
        });
    }
}