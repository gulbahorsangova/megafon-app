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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import tj.anor.myapplicatio.model.ServiceItem;

public class ServiceActivity extends AppCompatActivity implements ServiceAdapter.OnItemClickListener {

    RecyclerView recyclerView ;
    ServiceAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ArrayList<ServiceItem> serviceItems = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                            adapter = new ServiceAdapter(serviceItems);
                            layoutManager = new LinearLayoutManager(ServiceActivity.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(ServiceActivity.this);
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
/*

        serviceItems.add(new ServiceItem("Продли интернет 1ГБ", "Дополнительно 1ГБ интернет-трафика на максимальной скорости, предусмотренной условиями тарифа.",
                "Дополнительно 1ГБ интернет-трафика на максимальной скорости, предусмотренной условиями тарифа.",
                "Подключенно", "Абон.плата" , "Отключение" ));
        serviceItems.add(new ServiceItem("Продли интернет 5ГБ", "Дополнительно 5ГБ интернет-трафика на максимальной скорости, предусмотренной условиями тарифа.",
                "Дополнительно 5ГБ интернет-трафика на максимальной скорости, предусмотренной условиями тарифа.",
                "Подключенно", "Абон.плата" , "Отключение" ));
        serviceItems.add(new ServiceItem("Продли интернет 10ГБ", "Дополнительно 10ГБ интернет-трафика на максимальной скорости, предусмотренной условиями тарифа.",
                "Дополнительно 10ГБ интернет-трафика на максимальной скорости, предусмотренной условиями тарифа.",
                "Подключенно", "Абон.плата" , "Отключение" ));
        serviceItems.add(new ServiceItem("Социальные сети", "Опция для общения в Facebook, ВКонтакте и Одноклассники.",
                "Опция для общения в Facebook, ВКонтакте и Одноклассники.",
                "Подключенно", "Абон.плата" , "Отключение"));
        serviceItems.add(new ServiceItem("Соцсети", "Делитесь впечатлениями с друзьями и будьте в курсе новостей.",
                "Делитесь впечатлениями с друзьями и будьте в курсе новостей.",
                "Подключенно", "Абон.плата" , "Отключение"));
        serviceItems.add(new ServiceItem("Видео+", "Опция для просмотра видео в приложениях YouTube, Rutube, Vimeo.",
                "Опция для просмотра видео в приложениях YouTube, Rutube, Vimeo.",
                "Подключенно", "Абон.плата" , "Отключение"));
        serviceItems.add(new ServiceItem("В пути", "Безлимитный интернет для карт, навигаторов, сервисов такси, бронирования авиабилетов и отелей, а также онлайн-радио. Наслаждайтесь дорогой и не думайте о трафике. Подключение в 1-й раз - бесплатно, далее 20Р.",
                "Опция для просмотра видео в приложениях YouTube, Rutube, Vimeo.",
                "Подключенно", "Абон.плата" , "Отключение"));
*/


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
    public void onItemClick() {
        startActivity(new Intent(this, ScrollingActivity.class));
    }
}