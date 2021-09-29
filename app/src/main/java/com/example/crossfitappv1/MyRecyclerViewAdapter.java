package com.example.crossfitappv1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyRecyclerViewAdapter extends FirebaseRecyclerAdapter<ModelFoods,MyRecyclerViewAdapter.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyRecyclerViewAdapter(@NonNull FirebaseRecyclerOptions<ModelFoods> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull ModelFoods model) {
        holder.meal.setText(model.getMealName());
        holder.wei.setText(model.getWeight());
        holder.calo.setText(model.getCalories());
        holder.carbs.setText(model.getCarbs());
        holder.fat.setText(model.getFat());
        holder.pro.setText(model.getProtien());

        //Update Food in Admin Panel
        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.meal.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1728)
                        .create();
                View view = dialogPlus.getHolderView();
                EditText name = view.findViewById(R.id.txtUpdateFoodName);
                EditText weight = view.findViewById(R.id.txtUpdateWeight);
                EditText calories = view.findViewById(R.id.txtUpdateCalories);
                EditText carbs = view.findViewById(R.id.txtUpdateCarbs);
                EditText fat = view.findViewById(R.id.txtUpdateFat);
                EditText protein = view.findViewById(R.id.txtUpdateProtien);
                Button btnUp = view.findViewById(R.id.btnUpdate);
                name.setText(model.getMealName());
                weight.setText(model.getWeight());
                calories.setText(model.getCalories());
                carbs.setText(model.getCarbs());
                fat.setText(model.getFat());
                protein.setText(model.getProtien());
                dialogPlus.show();
                btnUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String ,Object> map = new HashMap<>();
                        map.put("mealName",name.getText().toString());
                        map.put("weight",weight.getText().toString());
                        map.put("calories",calories.getText().toString());
                        map.put("carbs",carbs.getText().toString());
                        map.put("fat",fat.getText().toString());
                        map.put("protien",protein.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("Foods")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.meal.getContext(), "Updated Successfully",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.meal.getContext(), "Error While Updating",Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });
                    }
                });

            }
        });

        //Delete Food in Admin Panel
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.meal.getContext());
                builder.setTitle("Need to delete ?");
                builder.setMessage("Deleted data can't be Undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Foods")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.meal.getContext(), "Cancled",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_row,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView meal,wei,calo,carbs,fat,pro;
        Button btnedit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            meal = (TextView) itemView.findViewById(R.id.recyclemealName);
            wei = (TextView) itemView.findViewById(R.id.recycleGram);
            calo = (TextView) itemView.findViewById(R.id.recycleCalories);
            carbs = (TextView) itemView.findViewById(R.id.recycleCrabs);
            fat = (TextView) itemView.findViewById(R.id.recycleFat);
            pro = (TextView) itemView.findViewById(R.id.recycleProtein);

            btnedit = (Button) itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
        }
    }
}
