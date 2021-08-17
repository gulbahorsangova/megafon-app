package tj.anor.myapplicatio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class CostsActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costs);

        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RelativeLayout relativeLayout2 = findViewById(R.id.writeOffs);
        RelativeLayout relativeLayout3 = findViewById(R.id.refill);

        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(CostsActivity.this, CostsContentActivity.class);
                intent2.putExtra("operTranzaction", "outcome");
                startActivity(intent2);
            }
        });

        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(CostsActivity.this, CostsContentActivity.class);
                intent3.putExtra("operTranzaction", "income");
                startActivity(intent3);
            }
        });

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.home){
                    NavUtils.navigateUpFromSameTask(CostsActivity.this);
                }
            }
        });
    }

}