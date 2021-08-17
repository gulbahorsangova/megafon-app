package tj.anor.myapplicatio;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import tj.anor.myapplicatio.dao.TransactionDatabase;
import tj.anor.myapplicatio.model.Transaction;

public class CostsContentActivity extends AppCompatActivity implements CostsContentAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private CostsContentAdapter adapter;
    private TransactionDatabase transactionDatabase;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costs_content);
        /*transactionDatabase = TransactionDatabase.getInstance(CostsContentActivity.this);
        transactionDatabase.getTransactionDao().create(new Transaction( -26, "outcome", "Минут", "02-07-2021"));
        transactionDatabase.getTransactionDao().create(new Transaction( -5, "outcome", "Звонок", "14-07-2021"));
        transactionDatabase.getTransactionDao().create(new Transaction( -30, "outcome", "Минут", "12-07-2021"));
        transactionDatabase.getTransactionDao().create(new Transaction( -2.00, "outcome", "Звонок", "28-07-2021"));
        transactionDatabase.getTransactionDao().create(new Transaction( -30, "outcome", "Интернет", "18-07-2021"));
        transactionDatabase.getTransactionDao().create(new Transaction( +40, "income", "Платеж", "08-07-2021"));
        transactionDatabase.getTransactionDao().create(new Transaction( +96, "income", "Платеж", "09-07-2021"));
        transactionDatabase.getTransactionDao().create(new Transaction( +5.00, "income", "Платеж", "10-07-2021"));
        */
        initializeView();
        displayList();

    }

    private void displayList() {
        transactionDatabase = TransactionDatabase.getInstance(CostsContentActivity.this);
//        transactionDatabase.getTransactionDao().create(new Transaction( -26.00, "outcome", "Минут", "02-07-2021"));
//        transactionDatabase.getTransactionDao().create(new Transaction( -23.15, "outcome", "Звонок", "14-07-2021"));
//        transactionDatabase.getTransactionDao().create(new Transaction( -56.12, "outcome", "Минут", "12-07-2021"));
//        transactionDatabase.getTransactionDao().create(new Transaction( -1.00, "outcome", "Звонок", "28-07-2021"));
//        transactionDatabase.getTransactionDao().create(new Transaction( -30, "outcome", "Интернет", "18-07-2021"));
//        transactionDatabase.getTransactionDao().create(new Transaction( +40, "income", "Платеж", "08-07-2021"));
//        transactionDatabase.getTransactionDao().create(new Transaction( +96, "income", "Платеж", "09-07-2021"));
//        transactionDatabase.getTransactionDao().create(new Transaction( +5.00, "income", "Платеж", "10-07-2021"));
        String oprTransaction = getIntent().getExtras().getString("operTranzaction");

        new RetrieveTask(this,oprTransaction).execute();
    }

    public static class RetrieveTask extends AsyncTask<Void, Void, List<Transaction>> {

        private WeakReference<CostsContentActivity> activityReference;
        private String oprTns;

        public RetrieveTask(CostsContentActivity activityReference, String oprTns) {
            this.activityReference = new WeakReference<>(activityReference);
            this.oprTns = oprTns;
        }

        @Override
        protected List<Transaction> doInBackground(Void... voids) {
            if (activityReference.get()!=null)
                return activityReference.get().transactionDatabase.getTransactionDao().getAll(oprTns);
            else return null;
        }

        @Override
        protected void onPostExecute(List<Transaction> transactions) {
            if (transactions != null && transactions.size()>0) {
                activityReference.get().transactions.clear();
                activityReference.get().transactions.addAll(transactions);
                activityReference.get().adapter.notifyDataSetChanged();
            }
        }
    }

    private void initializeView() {
        recyclerView = findViewById(R.id.recyclerViews);
        titleTextView = findViewById(R.id.titleTextView);
        recyclerView.setHasFixedSize(true);

        transactions = new ArrayList<>();
        adapter = new CostsContentAdapter(transactions, CostsContentActivity.this);
        layoutManager = new LinearLayoutManager(CostsContentActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        String oprTransaction = getIntent().getExtras().getString("operTranzaction");
        if (oprTransaction.equals("income")) {
            titleTextView.setText("Пополнения");
        }else {
            titleTextView.setText("Списания");
        }
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(String type) {

    }

    @Override
    protected void onDestroy() {
//        transactionDatabase.cleanUp();
        super.onDestroy();
    }
}