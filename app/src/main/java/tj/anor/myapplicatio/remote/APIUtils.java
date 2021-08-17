package tj.anor.myapplicatio.remote;

public class APIUtils {
    public static final String API_URL = "https://jsonplaceholder.typicode.com/";

    private APIUtils() {
    }

    public static PostService getUserService() {
        return RetrofitClient.getClient(API_URL).create(PostService.class);
    }
}
