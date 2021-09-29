package com.example.crossfitappv1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewAdapter>{
    private List<StoreCategoryData> list;
    private Context context;
    private String storeCategory;

    public StoreAdapter(List<StoreCategoryData> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.storeCategory = category;
    }

    @NonNull
    @Override
    public StoreViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.store_item_layout, parent, false);
        return new StoreViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewAdapter holder, int position) {
        StoreCategoryData item = list.get(position);
        holder.name.setText(item.getName());
        holder.price.setText(item.getPrice());
        holder.details.setText(item.getDetails());
        try {
            Picasso.get().load(item.getImage()).into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateStoreActivity.class);
                intent.putExtra("name",item.getName());
                intent.putExtra("price",item.getPrice());
                intent.putExtra("details",item.getDetails());
                intent.putExtra("image",item.getImage());
                intent.putExtra("key",item.getKey());
                intent.putExtra("StoreCategory",storeCategory);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StoreViewAdapter extends RecyclerView.ViewHolder {

        private TextView name,price,details;
        private Button update;
        private ImageView imageView;

        public StoreViewAdapter(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
            details = itemView.findViewById(R.id.productDetails);
            update = itemView.findViewById(R.id.productUpdate);
            imageView = itemView.findViewById(R.id.productImage);

        }
    }
}
