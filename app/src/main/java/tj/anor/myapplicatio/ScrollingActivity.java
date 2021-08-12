package tj.anor.myapplicatio;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import tj.anor.myapplicatio.databinding.ActivityScrollingBinding;
import tj.anor.myapplicatio.model.User;

public class ScrollingActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private ActivityScrollingBinding binding;
    private ProgressBar progressBar;
    private ProgressBar progressBar1;
    private TextView textCurrentTime;
    private TextView textUserBalance;
    private TextView number;
    private TextView number2;
    private TextView numberInternet;
    private TextView numberInternet2;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        progressBar1 = findViewById(R.id.internetProgressBar);
        progressBar = findViewById(R.id.minutesProgressBar);
        number = findViewById(R.id.number);
        number2 = findViewById(R.id.number2);
        numberInternet = findViewById(R.id.numberInternet);
        numberInternet2 = findViewById(R.id.numberInternet2);

        Double result = (Double.parseDouble(number.getText().toString())*100)/Double.parseDouble(number2.getText().toString());
        Double result1 = (Double.parseDouble(numberInternet.getText().toString())*100)/Double.parseDouble(numberInternet2.getText().toString());

        progressBar.setProgress(result.intValue());
        progressBar1.setProgress(result1.intValue());
        relativeLayout = (RelativeLayout) findViewById(R.id.rv_costs);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCosts = new Intent(ScrollingActivity.this, CostsActivity.class);
                startActivity(openCosts);
            }
        });

        DocumentReference docRef = db.collection("users").document("LvzmDafH5poxVnl55hSS");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        User currentUser = document.toObject(User.class);

                        renderUserBalance(currentUser.getBalance());


                    } else {
                        Log.d("UserInfo", "No such document");
                    }
                } else {
                    Log.d("UserInfo", "get failed with ", task.getException());
                }
            }
        });

        setCurrentTime();


        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void renderUserBalance(double balance) {
        textUserBalance = findViewById(R.id.textUserBalance);
        textUserBalance.setText(String.valueOf(balance));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setCurrentTime() {
        textCurrentTime = findViewById(R.id.textCurrentTime);
        textCurrentTime.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent openSettings =
                    new Intent(this, ServiceActivity.class);
            startActivity(openSettings);
            return true;
        } else if (id == R.id.action_finance) {
            Intent openFinance =
                    new Intent(this, FinanceActivity.class);
            startActivity(openFinance);
            return true;
        } else if (id == R.id.action_services) {
            Intent openService =
                    new Intent(this, ConnectionActivity.class);
            startActivity(openService);
            return true;
        } else if (id == R.id.action_for_me) {
            Intent openForMe =
                    new Intent(this, ForMeActivity.class);
            startActivity(openForMe);
            return true;
        } else if (id == R.id.action_more) {
            Intent openMore =
                    new Intent(this, MoreActivity.class);
            startActivity(openMore);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.costsLinear){
            Intent openCosts = new Intent(ScrollingActivity.this, CostsActivity.class);
            startActivity(openCosts);
        }
    }
}