package com.example.crossfitappv1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText date;
    TextView breakfastFoodMeel,breakfastWeight,breakfastCalo,breakfastCarbs,breakfastFat,breakfastProtein;
    TextView lunchFoodMela,lunchWeight,lunchCalo,lunchCarbs,lunchFat,lunchProtein;
    TextView dinnerFoodMela,dinnerWeight,dinnerCalo,dinnerCarbs,dinnerFat,dinnerProtein;
    Button btnDate;
    public FragmentList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentList.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentList newInstance(String param1, String param2) {
        FragmentList fragment = new FragmentList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view2 =  inflater.inflate(R.layout.fragment_list, container, false);


        //date = (EditText) view2.findViewById(R.id.editTextDate2);
        //String dateString = date.getText().toString();

        breakfastFoodMeel = (TextView) view2.findViewById(R.id.foodListMealName);
        breakfastWeight = (TextView) view2.findViewById(R.id.foodListWeight);
        breakfastCalo = (TextView) view2.findViewById(R.id.foodListCalo);
        breakfastCarbs = (TextView) view2.findViewById(R.id.foodListCarbs);
        breakfastFat = (TextView) view2.findViewById(R.id.foodListFat);
        breakfastProtein = (TextView) view2.findViewById(R.id.foodListProtein);

        lunchFoodMela = (TextView) view2.findViewById(R.id.foodList2MealName);
        lunchWeight = (TextView) view2.findViewById(R.id.foodList2Weight);
        lunchCalo = (TextView) view2.findViewById(R.id.foodList2Calo);
        lunchCarbs = (TextView) view2.findViewById(R.id.foodList2Carbs);
        lunchFat = (TextView) view2.findViewById(R.id.foodList2Fat);
        lunchProtein = (TextView) view2.findViewById(R.id.foodList2Protein);

        dinnerFoodMela = (TextView) view2.findViewById(R.id.foodList3MealName);
        dinnerWeight = (TextView) view2.findViewById(R.id.foodList3Weight);
        dinnerCalo = (TextView) view2.findViewById(R.id.foodList3Calo);
        dinnerCarbs = (TextView) view2.findViewById(R.id.foodList3Carbs);
        dinnerFat = (TextView) view2.findViewById(R.id.foodList3Fat);
        dinnerProtein = (TextView) view2.findViewById(R.id.foodList3Protein);
        btnDate = (Button) view2.findViewById(R.id.btnAddMeals);
        DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference().child("FoodsConsumption")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        btnDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                date = (EditText) view2.findViewById(R.id.editTextDate2);
                String dateString = date.getText().toString();

                dref.addValueEventListener(new ValueEventListener() {
                   @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Set Breakfast
                        String meal_breakfast = snapshot.child("breakfast").child(dateString).child("mealName").getValue().toString();
                        String weight_breakfast = snapshot.child("breakfast").child(dateString).child("weight").getValue().toString();
                        String calo_breakfast = snapshot.child("breakfast").child(dateString).child("calories").getValue().toString();
                        String carbs_breakfast = snapshot.child("breakfast").child(dateString).child("carbs").getValue().toString();
                        String fat_breakfast = snapshot.child("breakfast").child(dateString).child("fat").getValue().toString();
                        String protein_breakfast = snapshot.child("breakfast").child(dateString).child("protien").getValue().toString();
                        breakfastFoodMeel.setText(meal_breakfast);
                        breakfastWeight.setText(weight_breakfast);
                        breakfastCalo.setText(calo_breakfast);
                        breakfastCarbs.setText(carbs_breakfast);
                        breakfastFat.setText(fat_breakfast);
                        breakfastProtein.setText(protein_breakfast);

                        //Set Lunch
                       String meal_lunch = snapshot.child("lunch").child(dateString).child("mealName").getValue().toString();
                       String weight_lunch = snapshot.child("lunch").child(dateString).child("weight").getValue().toString();
                       String calo_lunch = snapshot.child("lunch").child(dateString).child("calories").getValue().toString();
                       String carbs_lunch = snapshot.child("lunch").child(dateString).child("carbs").getValue().toString();
                       String fat_lunch = snapshot.child("lunch").child(dateString).child("fat").getValue().toString();
                       String protein_lunch = snapshot.child("lunch").child(dateString).child("protien").getValue().toString();
                       lunchFoodMela.setText(meal_lunch);
                       lunchWeight.setText(weight_lunch);
                       lunchCalo.setText(calo_lunch);
                       lunchCarbs.setText(carbs_lunch);
                       lunchFat.setText(fat_lunch);
                       lunchProtein.setText(protein_lunch);

                       //Set Dinner
                       String meal_dinner = snapshot.child("dinner").child(dateString).child("mealName").getValue().toString();
                       String weight_dinner = snapshot.child("dinner").child(dateString).child("weight").getValue().toString();
                       String calo_dinner = snapshot.child("dinner").child(dateString).child("calories").getValue().toString();
                       String carbs_dinner = snapshot.child("dinner").child(dateString).child("carbs").getValue().toString();
                       String fat_dinner = snapshot.child("dinner").child(dateString).child("fat").getValue().toString();
                       String protein_dinner = snapshot.child("dinner").child(dateString).child("protien").getValue().toString();
                       dinnerFoodMela.setText(meal_dinner);
                       dinnerWeight.setText(weight_dinner);
                       dinnerCalo.setText(calo_dinner);
                       dinnerCarbs.setText(carbs_dinner);
                       dinnerFat.setText(fat_dinner);
                       dinnerProtein.setText(protein_dinner);

                   }

                    @Override
                   public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("The read failed: ");
                  }
                });
            }
        });

        return view2;
    }
}