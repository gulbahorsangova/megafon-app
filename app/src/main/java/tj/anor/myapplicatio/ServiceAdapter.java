package tj.anor.myapplicatio;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tj.anor.myapplicatio.model.Post;
import tj.anor.myapplicatio.model.ServiceItem;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    ArrayList<Post> serviceItems;
    OnItemClickListener onItemClickListener;
    Context context;

    public ServiceAdapter(ArrayList<Post> serviceItems, Context context) {
        this.serviceItems = serviceItems;
        this.context = context;
    }

    public void setOnItemClickListener(ServiceActivity onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ArrayList<Post> getServiceItems() {
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
        Post serviceItem = serviceItems.get(i);
        viewHolder.title.setText(serviceItem.getTitle());
        viewHolder.description.setText(serviceItem.getDescription());
//        viewHolder.subscription.setText(serviceItem.getSubscription());
//        viewHolder.connection.setText(serviceItem.getConnection());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(serviceItem.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceItems.size();
    }


    interface OnItemClickListener {
        void onItemClick(Long id);
    }

     class ServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView description;
//        public TextView connection;
//        public TextView subscription;
//        public TextView shutdown;
//        public EditText connectionNumber;
//        public EditText subscriptionNumber;
//        public EditText shutdownNumber;

        public ServiceViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(ServiceViewHolder.this);

            title = itemView.findViewById(R.id.titleTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
//            connection = itemView.findViewById(R.id.connectionTextView);
//            subscription = itemView.findViewById(R.id.subscriptionFeeTextView);
//            connectionNumber = itemView.findViewById(R.id.connectionTextNumber);
//            subscriptionNumber = itemView.findViewById(R.id.subscriptionFeeTextNumber);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ContentActivity.class);
            context.startActivity(intent);
        }
    }
}
