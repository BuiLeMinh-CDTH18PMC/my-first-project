package com.example.apiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Glide.with(this).load(R.drawable.logo).into((ImageView)findViewById(R.id.imageView));
    }

    public void btnLevel_click(View view) throws ExecutionException, InterruptedException {
        Button btn = (Button)view;
        new APIGetting(this).execute(view.getId()==R.id.hardLevel?"1":"2").get();
    }
}