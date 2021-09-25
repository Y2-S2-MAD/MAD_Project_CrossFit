package com.example.crossfitappv1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class ExerciseCardioAdapter extends FirebaseRecyclerAdapter<ExerciseCardioModel,ExerciseCardioAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options;
     */
    public ExerciseCardioAdapter(@NonNull FirebaseRecyclerOptions<ExerciseCardioModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull ExerciseCardioModel model) {

        holder.Exercise_name.setText(model.getExercise_name());
        holder.Minutes.setText(model.getMinutes());
        holder.Calories_Burned.setText(model.getCalories_Burned());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.Calories_Burned.getContext())
                        .setContentHolder(new ViewHolder(R.layout.exercise_cardio_update_popup))
                        .setExpanded(true,1200)
                        .create();

                //dialogPlus.show();

                View view =dialogPlus.getHolderView();

                EditText Exercise_name = view.findViewById(R.id.txtExCardioName);
                EditText Minutes = view.findViewById(R.id.txtExCardioMinutes);
                EditText Calories_Burned = view.findViewById(R.id.txtExCardioCalories);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                Exercise_name.setText(model.getExercise_name());
                Minutes.setText(model.getMinutes());
                Calories_Burned.setText(model.getCalories_Burned());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("Exercise_name",Exercise_name.getText().toString());
                        map.put("Minutes",Minutes.getText().toString());
                        map.put("Calories_Burned",Calories_Burned.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("CardioExercises")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.Exercise_name.getContext(),"Update Success",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.Exercise_name.getContext(),"Error While Updateing",Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(holder.Exercise_name.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Delete data can't be undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("CardioExercises")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.Exercise_name.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_exercise_cardio_item,parent,false);

        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{

        TextView Exercise_name,Minutes,Calories_Burned;

        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            Exercise_name =  itemView.findViewById(R.id.ExCname);
            Minutes =  itemView.findViewById(R.id.ExCminutes);
            Calories_Burned =  itemView.findViewById(R.id.ExCcaloriesBurned);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);

        }

    }

}
