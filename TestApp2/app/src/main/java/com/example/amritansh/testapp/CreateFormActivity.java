package com.example.amritansh.testapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CreateFormActivity extends AppCompatActivity {

    public static final int GET_FROM_GALLERY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_form);
        Button buttonLoadImage = (Button) findViewById(R.id.b1);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);



            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ImageView iv=(ImageView)findViewById(R.id.i1);
                iv.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    public void alertfunc(View v){
        AlertDialog a=new AlertDialog.Builder(CreateFormActivity.this).create();
        a.setTitle("Done!");
        a.setMessage("Your details have been added(not really)");
        a.setButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        a.show();

    }
    public void decage(View v){
        EditText e=(EditText)findViewById(R.id.inage);
        int age=Integer.parseInt(e.getText().toString());
        if(age>15)age--;
        else {
            AlertDialog a = new AlertDialog.Builder(CreateFormActivity.this).create();
            a.setTitle("Not Allowed!");
            a.setMessage("To Register, your age must be at least 15.");
            a.show();
        }
        e.setText(""+age);
    }
    public void incage(View v){
        EditText e=(EditText)findViewById(R.id.inage);
        int age=Integer.parseInt(e.getText().toString());
        age++;
        e.setText(""+age);
    }
}