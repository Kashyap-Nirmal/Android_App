package com.example.text_over_images;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.OutputStream;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class imageview extends AppCompatActivity implements View.OnClickListener,RotationGesture.
OnRotationGestureListener {

    private static int RESULT_LOAD_IMAGE = 1;
    private static Button b;
    private static ImageView iv;
    private static EditText et;
    private static TextView tv;
    private static File[] filelist;
    private static Bitmap bitmap;
    private static Uri selectedImage;
    private static Uri path;
    private static ScaleGestureDetector scaleGestureDetector;
    private static RotationGesture rotationGesture;
    private static int index = 0;
    private static int length = 0;
    private static String act = "",path1="";
    private static int flag = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview);
        rotationGesture = new RotationGesture(imageview.this);
        scaleGestureDetector = new ScaleGestureDetector(imageview.this, new
        simpleOnScaleGestureListener());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Intent i = getIntent();
        if (i.hasExtra("Action") && flag == 0) {
            act = i.getStringExtra("Action");
        } else
            Log.d("TxtOvrImg", "!!!!!!!!!!!!!NO extra");
        iv = findViewById(R.id.imageView);
        et = findViewById(R.id.editText);
        tv = findViewById(R.id.textView);
        b = findViewById(R.id.button2);
        b.setOnClickListener(this);
        b = findViewById(R.id.button3);
        b.setOnClickListener(this);
        b = findViewById(R.id.button4);
        b.setOnClickListener(this);
        b = findViewById(R.id.button5);
        b.setOnClickListener(this);
        b = findViewById(R.id.button6);
        b.setOnClickListener(this);
        iv.setMaxHeight(500);
        tv.setText("Add your text here");
        tv.setOnTouchListener(new View.OnTouchListener() {
            float lastX = 0, lastY = 0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case (MotionEvent.ACTION_DOWN):
                        lastX = motionEvent.getX();
                        lastY = motionEvent.getY();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dx = motionEvent.getX() - lastX;
                        float dy = motionEvent.getY() - lastY;
                        float finalX = view.getX() + dx;
                        float finalY = view.getY() + dy + view.getHeight();
                        view.setX(finalX);
                        view.setY(finalY);
                        break;
                }
                return true;
            }
        });

        if (act.equals("dir"))
            loadDir();
        else if (act.equals("img"))
            loadImage();


        et.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv.setText(s);
            }
        });
    }

    @Override
    public void OnRotation(RotationGesture rotationDetector) {
        float angle = rotationDetector.getAngle();
        tv = findViewById(R.id.textView);
        tv.setRotation(angle);
    }

    public class simpleOnScaleGestureListener extends ScaleGestureDetector.
    SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            //change the font size of the text on pinch
            float size = tv.getTextSize();
            float factor = detector.getScaleFactor();
            float product = size * factor;
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, product);
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        rotationGesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        String msg = "Load more images from directory";
        switch (v.getId()) {
            case R.id.button2:
            case R.id.button3:
                iv = findViewById(R.id.imageView);
                if (length != 0) {
                    if (v.getId() == R.id.button2)
                        index--;
                    else
                        index++;
                    checkIndex();
                    bitmap = BitmapFactory.decodeFile(filelist[index].getAbsolutePath());
                    iv.setImageBitmap(bitmap);
                } else
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4:
                bitmap = BitmapFactory.decodeFile(filelist[index].getAbsolutePath());
                if(saveFile(bitmap,"test.png"))
                    Toast.makeText(this, "File saved "+path1, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:
                colorPicker();
                break;
            case R.id.button6:
                textSelect();
                break;
        }
    }

    public void checkIndex() {
        if (index >= length)
            index = 0;
        else if (index < 0)
            index = length - 1;
        if (length == 0)
            index = 0;
    }

    public void loadImage() {
        flag = 1;
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
        act = "";
    }

    public void loadDir() {
        flag = 1;
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        startActivityForResult(Intent.createChooser(i, "Choose directory"), 9999);
        act = "";
    }

    public void loadDir1(){
        path1 = path.getPath();
        path1=Environment.getExternalStorageDirectory()+"/"+path1.substring(14)+"/";
        final String[] EXTENSIONS = new String[]{"gif", "png", "bmp" ,"jpg" };
        final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
            @Override
            public boolean accept(final File dir, final String name) {
                for (final String ext : EXTENSIONS) {
                    if (name.endsWith("." + ext)) {
                        return (true);
                    }
                }
                return (false);
            }
        };
        File dir = new File(path1);
        filelist = dir.listFiles(IMAGE_FILTER );
        length=filelist.length;
        bitmap=BitmapFactory.decodeFile(filelist[index].getAbsolutePath());
        iv.setImageBitmap(bitmap);
        tv = findViewById(R.id.textView);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null!= data &&
        data.getData() != null) {
            try {
                selectedImage = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                iv = findViewById(R.id.imageView);
                iv.setImageBitmap(bitmap);
                iv.setMaxHeight(500);
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode == 9999 && resultCode == RESULT_OK) {
            path = data.getData();
            loadDir1();
        }
    }

    public boolean colorPicker() {
        ColorPickerDialogBuilder.with(imageview.this, R.style.AppTheme_ColorPicker)
                .setTitle("Choose Color")
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(20)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {
                        tv.setTextColor(i);
                    }
                })
                .setPositiveButton("Ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                        tv.setTextColor(i);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).build().show();
        return true;
    }

    public void textSelect(){
        final Context context = this;
        final Dialog dialog = new Dialog(context);
        String[] listItem;
        dialog.setContentView(R.layout.custom);
        dialog.setTitle("Font Style");
        ListView listView;
        listView=dialog.findViewById(R.id.listView);
        listItem = getResources().getStringArray(R.array.array_technology);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch(position){
                    case 0:
                        tv.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
                        break;
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public boolean saveFile(Bitmap sourceImageBitmap ,String filename) {
        try{
            File pathf = new File(Environment.getExternalStorageDirectory()+"/"+path.getPath().substring(14)+"/");
            File file = new File(pathf, "test.jpg");
            OutputStream out = new FileOutputStream(file);
            out.flush();
            out.close();
            return true;
        }
        catch(Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void onBackPressed() {
        flag = 0;
        Intent i = new Intent(imageview.this, MainActivity.class);
        startActivity(i);
    }
}