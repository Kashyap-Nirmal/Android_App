package com.example.firestore_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.Map;

public class sign_up extends AppCompatActivity {
    FirebaseFirestore db;
    TextView textView;
    String uname,password;
    Button b;
    String id="",emsg="Noerror";
    Map<String, Object> user;
    static int flag=-1;

    /*
        flag =-1 =>Default value.
        flag = 0 =>User id already registered. Or Failure.
        flag = 1 =>User registration successfull.
    */
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(sign_up.this);
        textView=findViewById(R.id.textView);
        b=findViewById(R.id.button);
        b.setVisibility(View.INVISIBLE);
        db = FirebaseFirestore.getInstance();
        Intent i = getIntent();
        if(i.hasExtra("Uname")){
            uname=i.getStringExtra("Uname");
            password=i.getStringExtra("password");
        }
        // Create a new user with a first, middle, and last name
        user = new HashMap<>();
        user.put("user_name", uname);
        user.put("password", password);
        // Add a new document with a generated ID
        db = FirebaseFirestore.getInstance();
        db.collection("Users_Details")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (!task.isSuccessful()) {
                            textView.setText("Error getting documents. "+task.getException());
                        }
                        else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(flag!=0 && document.getData().values().iterator().next().equals(uname)){
                                    textView.setText("Already registered user id.\nPlease try another id.");
                                    flag=0;
                                    emsg="Already registered user id.\nPlease try another id.";
                                    Toast.makeText(sign_up.this, emsg+"\nFlag = "+flag, Toast.LENGTH_SHORT).show();
                                    intentSwitch();
                                    break;
                                }
                            }
                        }
                    }
                });
        if(flag!=0){
            Toast.makeText(this, "Inside flag!=0 , with flag = "+flag, Toast.LENGTH_SHORT).show();
            addCredentials();
        }
    }
    public void onBackPressed() {
        intentSwitch();
    }
    public void intentSwitch() {
        Toast.makeText(this, "Inside intentSwitch() , with flag = "+flag, Toast.LENGTH_SHORT).show();
        Intent i=new Intent(sign_up.this,MainActivity.class);
        i.putExtra("Activity","sign_up");
        i.putExtra("Status",flag);
        i.putExtra("emsg",emsg);
        startActivity(i);
    }
    public void addCredentials(){
        db.collection("Users_Details")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        id=""+documentReference.getId();
                        flag=1;
                        Toast.makeText(sign_up.this, "Registered", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        flag=0;
                        emsg=e.getMessage()+"";
                    }
                });
    }
}