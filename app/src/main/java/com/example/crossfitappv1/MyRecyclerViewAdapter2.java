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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyRecyclerViewAdapter2 extends FirebaseRecyclerAdapter<ModelFoods,MyRecyclerViewAdapter2.myViewHolder2>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyRecyclerViewAdapter2(@NonNull FirebaseRecyclerOptions<ModelFoods> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder2 holder, @SuppressLint("RecyclerView") final int position, @NonNull ModelFoods model) {
        holder.melaName.setText(model.getMealName());
        holder.wei.setText(model.getWeight());
        holder.calo.setText(model.getCalories());
        holder.carbs.setText(model.getCarbs());
        holder.fat.setText(model.getFat());
        holder.pro.setText(model.getProtien());

        //Adding for Breakfast
        holder.btnAddFood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.melaName.getContext())
                        .setContentHolder(new ViewHolder(R.layout.add_food_popup))
                        .setExpanded(true,1628)
                        .create();
                View view = dialogPlus.getHolderView();
                TextView name = view.findViewById(R.id.addfoodname);
                TextView weight = view.findViewById(R.id.addweight);
                TextView calories = view.findViewById(R.id.addcalo);
                TextView carbs = view.findViewById(R.id.addCarbs);
                TextView fat = view.findViewById(R.id.addfat);
                TextView protein = view.findViewById(R.id.addpro);
                //EditText count = view.findViewById(R.id.txtCount);

                Button btnadd = view.findViewById(R.id.btnaddconsumption);
                name.setText(model.getMealName());
                weight.setText(model.getWeight());
                calories.setText(model.getCalories());
                carbs.setText(model.getCarbs());
                fat.setText(model.getFat());
                protein.setText(model.getProtien());
                //count.setText("");
                dialogPlus.show();

                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String ,Object> map = new HashMap<>();
                        map.put("mealName",name.getText().toString());
                        map.put("weight",weight.getText().toString());
                        map.put("calories",calories.getText().toString());
                        map.put("carbs",carbs.getText().toString());
                        map.put("fat",fat.getText().toString());
                        map.put("protien",protein.getText().toString());
                        Calendar calendar = Calendar.getInstance();
                        String currDate = DateFormat.getDateInstance().format(calendar.getTime());
                        FirebaseDatabase.getInstance().getReference().child("FoodsConsumption")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("breakfast").child(currDate).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.melaName.getContext(), "Breakfast Added",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.melaName.getContext(), "Error While Adding",Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });
                    }
                });
            }
        });

        //Adding for Lunch
        holder.btnAddFood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus2 = DialogPlus.newDialog(holder.melaName.getContext())
                        .setContentHolder(new ViewHolder(R.layout.add_food_lunch_popup))
                        .setExpanded(true,1628)
                        .create();

                View view2 = dialogPlus2.getHolderView();
                TextView name2 = view2.findViewById(R.id.addfoodnameLunch);
                TextView weight2 = view2.findViewById(R.id.addweightlunch);
                TextView calories2 = view2.findViewById(R.id.addcalolunch);
                TextView carbs2 = view2.findViewById(R.id.addCarbslunch);
                TextView fat2 = view2.findViewById(R.id.addfatlunch);
                TextView protein2 = view2.findViewById(R.id.addprolunch);
                //EditText count2 = view2.findViewById(R.id.txtCountlunch);

                Button btnaddLunch = view2.findViewById(R.id.btnaddconsumptionlunch);
                name2.setText(model.getMealName());
                weight2.setText(model.getWeight());
                calories2.setText(model.getCalories());
                carbs2.setText(model.getCarbs());
                fat2.setText(model.getFat());
                protein2.setText(model.getProtien());
                //count2.setText("");

                dialogPlus2.show();
                btnaddLunch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String ,Object> map2 = new HashMap<>();
                        map2.put("mealName",name2.getText().toString());
                        map2.put("weight",weight2.getText().toString());
                        map2.put("calories",calories2.getText().toString());
                        map2.put("carbs",carbs2.getText().toString());
                        map2.put("fat",fat2.getText().toString());
                        map2.put("protien",protein2.getText().toString());
                        //map2.put("count",count2.getText().toString());

                        Calendar calendar2 = Calendar.getInstance();
                        String currDate2 = DateFormat.getDateInstance().format(calendar2.getTime());

                        FirebaseDatabase.getInstance().getReference().child("FoodsConsumption")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("lunch").child(currDate2).updateChildren(map2)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.melaName.getContext(), "Lunch Added",Toast.LENGTH_SHORT).show();
                                        dialogPlus2.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.melaName.getContext(), "Error While Adding",Toast.LENGTH_SHORT).show();
                                dialogPlus2.dismiss();
                            }
                        });
                    }
                });
            }
        });

        //Adding for Dinner
        holder.btnAddFood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus3 = DialogPlus.newDialog(holder.melaName.getContext())
                        .setContentHolder(new ViewHolder(R.layout.add_food_dinner_popup))
                        .setExpanded(true,1628)
                        .create();

                View view3 = dialogPlus3.getHolderView();
                TextView name3 = view3.findViewById(R.id.addfoodnameDinner);
                TextView weight3 = view3.findViewById(R.id.addweightDinner);
                TextView calories3 = view3.findViewById(R.id.addcaloDinner);
                TextView carbs3 = view3.findViewById(R.id.addCarbsDinner);
                TextView fat3 = view3.findViewById(R.id.addfatDinner);
                TextView protein3 = view3.findViewById(R.id.addproDinner);
                //EditText count3 = view3.findViewById(R.id.txtCountDinner);

                Button btnaddDinner = view3.findViewById(R.id.btnaddconsumptionDinner);
                name3.setText(model.getMealName());
                weight3.setText(model.getWeight());
                calories3.setText(model.getCalories());
                carbs3.setText(model.getCarbs());
                fat3.setText(model.getFat());
                protein3.setText(model.getProtien());
                //count3.setText("");

                dialogPlus3.show();
                btnaddDinner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String ,Object> map3 = new HashMap<>();
                        map3.put("mealName",name3.getText().toString());
                        map3.put("weight",weight3.getText().toString());
                        map3.put("calories",calories3.getText().toString());
                        map3.put("carbs",carbs3.getText().toString());
                        map3.put("fat",fat3.getText().toString());
                        map3.put("protien",protein3.getText().toString());
                        //map3.put("count",count3.getText().toString());

                        Calendar calendar3 = Calendar.getInstance();
                        String currDate3 = DateFormat.getDateInstance().format(calendar3.getTime());

                        FirebaseDatabase.getInstance().getReference().child("FoodsConsumption")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("dinner").child(currDate3).updateChildren(map3)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.melaName.getContext(), "Dinner Added",Toast.LENGTH_SHORT).show();
                                        dialogPlus3.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.melaName.getContext(), "Error While Adding",Toast.LENGTH_SHORT).show();
                                dialogPlus3.dismiss();
                            }
                        });
                    }
                });
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_count_row,parent,false);
        return new myViewHolder2(view2);
    }

    class myViewHolder2 extends RecyclerView.ViewHolder{
        TextView melaName;
        TextView wei,calo,carbs,fat,pro;
        Button btnAddFood1,btnAddFood2,btnAddFood3;

        //Button btnedit,btnDelete;

        public myViewHolder2(@NonNull View itemView) {
            super(itemView);

            melaName = (TextView) itemView.findViewById(R.id.addfoodCountMealName);
            wei = (TextView) itemView.findViewById(R.id.addfoodCountWeight);
            calo = (TextView) itemView.findViewById(R.id.addfoodCountCalo);
            carbs = (TextView) itemView.findViewById(R.id.addfoodCountCarbs);
            fat = (TextView) itemView.findViewById(R.id.addfoodCountFat);
            pro = (TextView) itemView.findViewById(R.id.addfoodCountProtein);

            btnAddFood1 = (Button) itemView.findViewById(R.id.btnAddFood1);
            btnAddFood2 = (Button) itemView.findViewById(R.id.btnAddFood2);
            btnAddFood3 = (Button) itemView.findViewById(R.id.btnAddFood3);
        }
    }
}
