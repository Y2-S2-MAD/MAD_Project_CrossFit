package com.example.crossfitappv1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAnalize#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAnalize extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView calo,carbs,fat,protein,date;

    public FragmentAnalize() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAnalize.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAnalize newInstance(String param1, String param2) {
        FragmentAnalize fragment = new FragmentAnalize();
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

        View view1 =  inflater.inflate(R.layout.fragment_analize, container, false);
        calo = (TextView) view1.findViewById(R.id.textCalories);
        carbs = (TextView) view1.findViewById(R.id.textCarbs);
        fat = (TextView) view1.findViewById(R.id.textFat);
        protein = (TextView) view1.findViewById(R.id.textProtein);
        date = (TextView) view1.findViewById(R.id.textdate);
        DatabaseReference dref;

        Calendar calendar = Calendar.getInstance();
        String currDate = DateFormat.getDateInstance().format(calendar.getTime());
        date.setText(currDate);
        dref = FirebaseDatabase.getInstance().getReference().child("FoodsConsumption")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Set Calories
                String calo1 = snapshot.child("breakfast").child(currDate).child("calories").getValue().toString();
                String calo2 = snapshot.child("lunch").child(currDate).child("calories").getValue().toString();
                String calo3 = snapshot.child("dinner").child(currDate).child("calories").getValue().toString();
                float cal1Float = Float.parseFloat(calo1);
                float cal2Float = Float.parseFloat(calo2);
                float cal3Float = Float.parseFloat(calo3);
                float calTotal = cal1Float+cal2Float+cal3Float;
                calo.setText(Float.toString(calTotal));
                calo.setText(calo1);
                //Set Carbs
                String carbs1 = snapshot.child("breakfast").child(currDate).child("carbs").getValue().toString();
                String carbs2 = snapshot.child("lunch").child(currDate).child("carbs").getValue().toString();
                String carbs3 = snapshot.child("dinner").child(currDate).child("carbs").getValue().toString();
                float carbs1Float = Float.parseFloat(carbs1);
                float carbs2Float = Float.parseFloat(carbs2);
                float carbs3Float = Float.parseFloat(carbs3);
                float carbsTot = carbs1Float+carbs2Float+carbs3Float;
                carbs.setText(Float.toString(carbsTot));
                //Set Fats
                String fat1 = snapshot.child("breakfast").child(currDate).child("fat").getValue().toString();
                String fat2 = snapshot.child("lunch").child(currDate).child("fat").getValue().toString();
                String fat3 = snapshot.child("dinner").child(currDate).child("fat").getValue().toString();
                float fat1Float = Float.parseFloat(fat1);
                float fat2Float = Float.parseFloat(fat2);
                float fat3Float = Float.parseFloat(fat3);
                float fatTot = fat1Float+fat2Float+fat3Float;
                fat.setText(Float.toString(fatTot));
                //Set Protein
                String protein1 = snapshot.child("breakfast").child(currDate).child("protien").getValue().toString();
                String protein2 = snapshot.child("lunch").child(currDate).child("protien").getValue().toString();
                String protein3 = snapshot.child("dinner").child(currDate).child("protien").getValue().toString();
                float protein1Float = Float.parseFloat(protein1);
                float protein2Float = Float.parseFloat(protein2);
                float protein3Float = Float.parseFloat(protein3);
                float proteinTot = protein1Float+protein2Float+protein3Float;
                protein.setText(Float.toString(proteinTot));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Inflate the layout for this fragment
        return view1;
    }
}