package com.example.crossfitappv1;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class UserViewCardioAdapter extends FirebaseRecyclerAdapter<ExerciseCardioModel,UserViewCardioAdapter.myViewHolder> {



    public interface UserSelectedCardioRecycleViewOnClickListener{
        void OnItemClick(int position);
    }


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options;
     */
    public UserViewCardioAdapter(@NonNull FirebaseRecyclerOptions<ExerciseCardioModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull ExerciseCardioModel model) {

        holder.Exercise_name.setText(model.getExercise_name());
        holder.Minutes.setText(model.getMinutes());
        holder.Calories_Burned.setText(model.getCalories_Burned());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_exercise_cardio_item,parent,false);

        return new myViewHolder(view);
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView Exercise_name,Minutes,Calories_Burned;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            Exercise_name =  itemView.findViewById(R.id.ExCname);
            Minutes =  itemView.findViewById(R.id.ExCminutes);
            Calories_Burned =  itemView.findViewById(R.id.ExCcaloriesBurned);

        }

    }

}
