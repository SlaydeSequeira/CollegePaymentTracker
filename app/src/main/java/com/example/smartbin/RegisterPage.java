package com.example.smartbin;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbin.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class RegisterPage extends AppCompatActivity {

    // Widgets
    EditText userET, passET, emailET,phone,city,aadhar;
    Button registerBtn;

    // Firebase
    FirebaseAuth auth;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Initializing Widgets:
        userET = findViewById(R.id.edittext1);
        passET = findViewById(R.id.edittext3);
        emailET = findViewById(R.id.edittext2);
        registerBtn = findViewById(R.id.button);
        phone= findViewById(R.id.edit1);
        city= findViewById(R.id.edit2);
        aadhar= findViewById(R.id.edit3);
        TextView t= findViewById(R.id.login);


        // Firebase Auth
        auth = FirebaseAuth.getInstance();
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(RegisterPage.this,LoginActivity.class);
                startActivity(i);
            }
        });
        // Adding Event Listener to Button Register
        registerBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String username_text = userET.getText().toString();
                String email_text    = emailET.getText().toString();
                String pass_text     = passET.getText().toString();
                String phone_text = phone.getText().toString();
                String city_text    = city.getText().toString();
                String aadhar_text     = aadhar.getText().toString();
                if (TextUtils.isEmpty(username_text) || TextUtils.isEmpty(email_text) || TextUtils.isEmpty(pass_text)|| TextUtils.isEmpty(phone_text)|| TextUtils.isEmpty(aadhar_text)|| TextUtils.isEmpty(city_text)){
                    Toast.makeText(RegisterPage.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }else{
                    RegisterNow(username_text, email_text , pass_text,phone_text,city_text,aadhar_text );
                }
            }
        });

    }

    private void Check() {
        int t= RandomGenerator();
        final int[] flag = {0};
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("tokens");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder childrenKeys = new StringBuilder();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String childKey = childSnapshot.getKey();
                    childrenKeys.append(childKey);
                    int a = Integer.parseInt(childKey);
                    if(a==t)
                    {
                        Check();
                        flag[0] =1;
                        break;
                    }
                }
                if(flag[0] !=1)
                {
                    String token = String.valueOf(t);
                    FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = fuser.getUid();
                    HashMap<String, Object> map = new HashMap<>();
                    map.put(token, uid);
                    myRef.setValue(map);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled event if needed
            }
        });

    }

    private int RandomGenerator() {
        Random r = new Random();
        int token=r.nextInt(1000000);
        return token;
    }

    private void RegisterNow(final String username, String email, String password,String phone_text,String city_text, String aadhar_text){

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            myRef = FirebaseDatabase.getInstance().getReference("MyUsers")
                                    .child(userid);
                            //Check();
                            // HashMaps
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username", username);
                            hashMap.put("imageURL", "default");
                            hashMap.put("status" , "offline");
                            hashMap.put("admin","0");
                            hashMap.put("phone", phone_text);
                            hashMap.put("city" , city_text);
                            hashMap.put("aadhar",aadhar_text);
                            myRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Intent i = new Intent(RegisterPage.this, HomePage.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }

                                }
                            });



                            // Opening the Main Activity after Success Registration


                        }else{
                            Toast.makeText(RegisterPage.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                        }

                    }
                });




    }

}