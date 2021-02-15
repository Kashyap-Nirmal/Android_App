//Reference- https://www.javatpoint.com/android-popup-menu-example
//Reference- https://www.javatpoint.com/android-context-menu-example
//Reference- https://www.javatpoint.com/android-option-menu-example

package com.example.menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button button;
    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        button=findViewById(R.id.button);
        button.setMinimumWidth(width/2);
        button.setOnClickListener(this);
        button=findViewById(R.id.button1);
        button.setMinimumWidth(width/2);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.v=v;
        switch(v.getId()){
            case R.id.button:
                    onClick0(this.v);
                break;
            case R.id.button1:
                    onClick1(this.v);
                break;
        }
    }

    public void onClick0(View v){
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(MainActivity.this, button);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),
                Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        popup.show();//showing popup menu
    }

    public void onClick1(View v){
        registerForContextMenu(button);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Select The Action");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==R.id.action1){
            Toast.makeText(getApplicationContext(),"Action1 Selected", Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.action2){
            Toast.makeText(getApplicationContext(),"Acton2 Selected",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


