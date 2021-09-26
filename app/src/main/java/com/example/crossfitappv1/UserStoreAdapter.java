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

public class UserStoreAdapter extends RecyclerView.Adapter<UserStoreAdapter.StoreViewAdapter>{
    private List<StoreCategoryData> Ulist;
    private Context Ucontext;
    private String Ucategory;

    public UserStoreAdapter(List<StoreCategoryData> list, Context context, String category) {
        this.Ulist = list;
        this.Ucontext = context;
        this.Ucategory = category;
    }

    @NonNull
    @Override
    public StoreViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(Ucontext).inflate(R.layout.user_store_item_layout, parent, false);
        return new StoreViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewAdapter holder, int position) {
        StoreCategoryData item = Ulist.get(position);
        holder.name.setText(item.getName());
        holder.price.setText(item.getPrice());
        holder.details.setText(item.getDetails());
        try {
            Picasso.get().load(item.getImage()).into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.BuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ucontext, UpdateStoreActivity.class);
                intent.putExtra("price",item.getPrice());

                intent.putExtra("StoreCategory",Ucategory);
                Ucontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Ulist.size();
    }

    public class StoreViewAdapter extends RecyclerView.ViewHolder {

        private TextView name,price,details;
        private Button BuyNow;
        private ImageView imageView;

        public StoreViewAdapter(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.userProductName);
            price = itemView.findViewById(R.id.userProductPrice);
            details = itemView.findViewById(R.id.userProductDetails);
            BuyNow = itemView.findViewById(R.id.userProductBuynow);
            imageView = itemView.findViewById(R.id.userProductImage);

        }
    }
}
