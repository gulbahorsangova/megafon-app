package tj.anor.myapplicatio;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tj.anor.myapplicatio.model.ServiceItem;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    ArrayList<ServiceItem> serviceItems;
    OnItemClickListener onItemClickListener;

    public ServiceAdapter(ArrayList<ServiceItem> serviceItems) {
        this.serviceItems = serviceItems;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ArrayList<ServiceItem> getServiceItems() {
        return serviceItems;
    }

    @Override
    public ServiceAdapter.ServiceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_item, viewGroup, false);
        ServiceViewHolder serviceViewHolder = new ServiceViewHolder(view);
        return serviceViewHolder;
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder viewHolder, int i) {
        ServiceItem serviceItem = serviceItems.get(i);
        viewHolder.title.setText(serviceItem.getTitle());
        viewHolder.description.setText(serviceItem.getDescription());
        viewHolder.connection.setText(serviceItem.getConnection());
        viewHolder.subscription.setText(serviceItem.getSubscription());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceItems.size();
    }

    interface OnItemClickListener {
        void onItemClick();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView connection;
        public TextView subscription;
        public TextView shutdown;
        public EditText connectionNumber;
        public EditText subscriptionNumber;
        public EditText shutdownNumber;

        public ServiceViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
            connection = itemView.findViewById(R.id.connectionTextView);
            subscription = itemView.findViewById(R.id.subscriptionFeeTextView);
            connectionNumber = itemView.findViewById(R.id.connectionTextNumber);
            subscriptionNumber = itemView.findViewById(R.id.subscriptionFeeTextNumber);
        }
    }
}
