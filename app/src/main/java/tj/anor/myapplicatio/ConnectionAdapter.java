package tj.anor.myapplicatio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import tj.anor.myapplicatio.ConnectionServiceItem;
import java.util.ArrayList;

public class ConnectionAdapter extends RecyclerView.Adapter<ConnectionAdapter.ConnectionViewHolder>  {
    Context context;
    ArrayList<ConnectionServiceItem> connectionServiceItems;
    OnItemClickListener onItemClickListener;

    public ConnectionAdapter(Context context, ArrayList<ConnectionServiceItem> connectionServiceItems) {
        this.context = context;
        this.connectionServiceItems = connectionServiceItems;
    }

    public ArrayList<ConnectionServiceItem> getConnectionServiceItems() {
        return connectionServiceItems;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ConnectionAdapter.ConnectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_connection_service_item, parent, false);
        ConnectionViewHolder connectionViewHolder = new ConnectionViewHolder(view);
        return connectionViewHolder;
    }

    @Override
    public void onBindViewHolder( ConnectionViewHolder viewHolder, int position) {
        ConnectionServiceItem  connectionServiceItem = connectionServiceItems.get(position);
        viewHolder.title.setText(connectionServiceItem.getTitle());
        viewHolder.description.setText(connectionServiceItem.getDescription());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    interface OnItemClickListener {
        void onItemClick();
    }

    class ConnectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public TextView description;

        public ConnectionViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(ConnectionViewHolder.this);

            title = itemView.findViewById(R.id.titleTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ConnectionService.class);
            context.startActivity(intent);
        }
    }
}
