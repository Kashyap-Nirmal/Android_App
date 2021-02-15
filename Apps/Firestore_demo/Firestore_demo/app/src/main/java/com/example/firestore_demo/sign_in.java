package com.example.firestore_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.client.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.Map;

public class sign_in extends AppCompatActivity {
    FirebaseFirestore db;
    TextView textView;
    EditText et1;
    String uname="null",password="null";
    Button b;
    static int count=-5;
    int flag=-1;
    /*
        flag = -1=>Default
        flag =  0=>Multiple attempts greater than 5.
        flag =  1=>User signed in successfully,
        flag =  2=>Admin user id and password. To display all the registered users.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(sign_in.this);
        textView =findViewById(R.id.textView);
        Intent i = getIntent();
        if(i.hasExtra("Uname") && i.hasExtra("password") && i.hasExtra("Count")){
            uname=i.getStringExtra("Uname");
            password=i.getStringExtra("password");
            count=i.getIntExtra("Count",-1);
            if(count>=5)
                flag=0;
        }
        else
            textView.setText("One of the *Required fields in empty.");
        final Map<String, Object> user = new HashMap<>();
        if("admin".equals(uname) && "adminpass".equals(password)){
            flag=2;
            textView.setText("All the registered users from Firestore are as below:\n");
        }
        else{
            user.put("user_name", uname);
            user.put("password", password);
        }
        b=findViewById(R.id.button);
        b.setVisibility(View.INVISIBLE);
        b=findViewById(R.id.button2);
        b.setVisibility(View.INVISIBLE);
        et1=findViewById(R.id.editText);
        et1.setVisibility(View.INVISIBLE);
        et1=findViewById(R.id.editText2);
        et1.setVisibility(View.INVISIBLE);
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
                                if(flag!=1 && flag!=2 && document.getData().values().containsAll(user.values())){
                                    if(document.getData().values().iterator().next().equals(uname))
                                        textView.setText("Welcome "+document.getData().values().iterator().next());
                                    flag=1;
                                }
                                if(flag==2){
                                    textView.append(document.getData().values().iterator().next()+"\n");
                                }
                        }
                        if(flag==0)
                            textView.setText("Too many wrong attempts\nNumber of wrong Attempts = "+count);
                        else if(flag==-1){
                            textView.setText("Credentials not matched");
                            Toast.makeText(sign_in.this, "Credentials not matched", Toast.LENGTH_SHORT).show();
                            count++;
                            intentSwitch();
                        }
                    }
                }
            });
    }
    public void onBackPressed(){
        intentSwitch();
    }
    public void intentSwitch() {
        Intent i=new Intent(sign_in.this,MainActivity.class);
        i.putExtra("Activity","sign_in");
        i.putExtra("Count",count);
        startActivity(i);
    }
}