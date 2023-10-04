package com.example.smartbin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbin.adapter.RecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AccountHistory extends AppCompatActivity {

    String[] Titles = new String[100];
    String[] Description = new String[100];
    String[] Image = new String[100];
    String[] Author = new String[100];
    int count;
    RecyclerView recyclerView;
    ImageView i1;;
    RecyclerAdapter adapter;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_history);
        recyclerView = findViewById(R.id.recyclerView);
        Objects.requireNonNull(getSupportActionBar()).hide();

        t1= findViewById(R.id.text1);
        i1 = findViewById(R.id.image);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccountHistory.this,HomePage.class);
                startActivity(i);
                finish();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(AccountHistory.this));
        count=12;
        Titles[0] = "College Fees";
        Description[0] = "Unpaid";
        Image[0] = "Trophy";
        Author[0] = "Rs 40000";

        Titles[1] = "Exam Fees";
        Description[1] = "Paid";
        Image[1] = "Trophy";
        Author[1] = "Rs 1000";

        Titles[2] = "Textbook Cost";
        Description[2] = "Unpaid";
        Image[2] = "Trophy";
        Author[2] = "Rs 5000";

        Titles[3] = "Library Fine";
        Description[3] = "Unpaid";
        Image[3] = "Trophy";
        Author[3] = "Rs 200";

        Titles[4] = "Sports Equipment";
        Description[4] = "Paid";
        Image[4] = "Trophy";
        Author[4] = "Rs 1500";

        Titles[5] = "Lab Fees";
        Description[5] = "Unpaid";
        Image[5] = "Trophy";
        Author[5] = "Rs 2500";

        Titles[6] = "Dormitory Rent";
        Description[6] = "Paid";
        Image[6] = "Trophy";
        Author[6] = "Rs 8000";

        Titles[7] = "Cafeteria Charges";
        Description[7] = "Unpaid";
        Image[7] = "Trophy";
        Author[7] = "Rs 350";

        Titles[8] = "Transportation Fee";
        Description[8] = "Unpaid";
        Image[8] = "Trophy";
        Author[8] = "Rs 3000";

        Titles[9] = "Uniform Cost";
        Description[9] = "Paid";
        Image[9] = "Trophy";
        Author[9] = "Rs 1200";

        Titles[10] = "Graduation Fee";
        Description[10] = "Paid";
        Image[10] = "Trophy";
        Author[10] = "Rs 6000";

        Titles[11] = "Student Association Dues";
        Description[11] = "Unpaid";
        Image[11] = "Trophy";
        Author[11] = "Rs 50";
        ImageView i2= findViewById(R.id.image1);
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccountHistory.this,Stats.class);
                startActivity(i);
            }
        });
        adapter = new RecyclerAdapter(AccountHistory.this, Titles, count, Image, Description,Author);
        recyclerView.setAdapter(adapter);
        FirebaseUser fuser;
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("MyUsers").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String b = String.valueOf(snapshot.child("points").child("redeemed").getValue());
                String c = String.valueOf(snapshot.child("points").child("received").getValue());
                int d= Integer.parseInt(c)-Integer.parseInt(b);
                t1.setText("Total Points: "+d);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}