package tj.anor.myapplicatio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tj.anor.myapplicatio.model.Transaction;

class CostsContentAdapter extends RecyclerView.Adapter<CostsContentAdapter.CostsViewHolder> {
    ArrayList<Transaction> costsItems;
    Context context;
    OnItemClickListener onItemClickListener;

    public CostsContentAdapter(ArrayList<Transaction> costsItems, Context context) {
        this.costsItems = costsItems;
        this.context = context;

    }

    public void setOnItemClickListener(CostsContentActivity onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public ArrayList<Transaction> getCostsItems() {
        return costsItems;
    }

    @Override
    public CostsContentAdapter.CostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_costs_content_item, parent, false);
        CostsViewHolder costsViewHolder = new CostsViewHolder(view);
        return costsViewHolder;
    }

    @Override
    public void onBindViewHolder(CostsViewHolder holder, int position) {
        Transaction costsContentItem = costsItems.get(position);
        holder.type.setText(costsContentItem.getType());
        holder.price.setText(String.valueOf(costsContentItem.getPrice()));
        holder.create_date.setText(costsContentItem.getCreate_date());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(costsContentItem.getType());
            }
        });

    }

    @Override
    public int getItemCount() {
        return costsItems.size();
    }

    interface OnItemClickListener {
        void onItemClick(String type);
    }

    class CostsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView type;
        public TextView price;
        public TextView create_date;

        public CostsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(CostsViewHolder.this);
            type = itemView.findViewById(R.id.type);
            price = itemView.findViewById(R.id.price);
            create_date = itemView.findViewById(R.id.create_date);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, CostsContentActivity.class);

        }
    }
}
