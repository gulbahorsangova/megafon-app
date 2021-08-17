package tj.anor.myapplicatio.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import tj.anor.myapplicatio.model.Post;

public interface PostService {

    @GET("posts/")
    Call<List<Post>> getPosts();

    @GET("posts/{id}")
    Call<Post> getById(@Path("id") int id);

    @POST("posts/")
    Call<Post> addPost(@Body Post post);

    @PUT("posts/{id}")
    Call<Post> updatePost(@Path("id") int id, @Body Post post);

    @DELETE("posts/{id}")
    Call<Post> deletePost(@Path("id") int id);
}
