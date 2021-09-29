package com.example.crossfitappv1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class UserPackageAdapter extends RecyclerView.Adapter<UserPackageAdapter.PackageViewAdapter> {
    private List<categoryData> Ulist;
    private Context Ucontext;
    private String Ucategory;


    public UserPackageAdapter(List<categoryData> list, Context context, String category) {
        this.Ulist = list;
        this.Ucontext = context;
        this.Ucategory = category;
    }


    @NonNull
    @Override
    public PackageViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(Ucontext).inflate(R.layout.user_package_item_layout, parent, false);
        return new PackageViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageViewAdapter holder, int position) {
        categoryData item = Ulist.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.availableDate.setText(item.getAvailableDate());
        holder.expireDate.setText(item.getExpireDate());
        try {
            Picasso.get().load(item.getImage()).into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }



        holder.BuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ucontext, PackagePayment.class);
                intent.putExtra("expireDate",item.getExpireDate());
                intent.putExtra("name",item.getName());
                intent.putExtra("category",Ucategory);
                Ucontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Ulist.size();
    }




    public class PackageViewAdapter extends RecyclerView.ViewHolder{

        private TextView name,description,availableDate,expireDate;
        private Button BuyNow;
        private ImageView imageView;



        public PackageViewAdapter(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.packageName);
            description = itemView.findViewById(R.id.packageDescription);
            availableDate = itemView.findViewById(R.id.ADate);
            expireDate = itemView.findViewById(R.id.EDate);
            BuyNow = itemView.findViewById(R.id.packageBuynow);
            imageView = itemView.findViewById(R.id.packageImage);

        }

    }
}
