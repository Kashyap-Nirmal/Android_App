package com.example.databases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class sqlite  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite);
        DatabaseHandler db = new DatabaseHandler(this);
        TextView tv = findViewById(R.id.textView);
        // Inserting Contacts
        tv.setText("Inserting ..");
        db.addContact(new Contact("ABC", "9999999999"));
        db.addContact(new Contact("DEF", "8888888888"));
        db.addContact(new Contact("GHI", "7777777777"));
        db.addContact(new Contact("JKL", "6666666666"));

        // Reading all contacts
        tv.setText("Reading all contacts..\n\n");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: " + cn.getID() + " , Name: " + cn.getName() + " , Phone: " +
                    cn.getPhoneNumber()+"\n";
            // Writing Contacts to log
            tv.append(log);
        }
        tv.append("Total no. of contacts = "+db.getContactsCount());
    }
}
