package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class AddNewWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);
    }

    public void click(View view) throws ExecutionException, InterruptedException {
        String word = ((EditText) findViewById(R.id.txtWord)).getText().toString();
        String def = ((EditText) findViewById(R.id.txtDef)).getText().toString();
        if(checkNull(word) || checkNull(def)){
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
            return;
        }
        Word newWord = new Word(word, def);
//        Toast.makeText(this, newWord.toString(), Toast.LENGTH_SHORT).show();
        Button btn = (Button)view;
        new APIGetting(this).execute(newWord).get();
    }

    private boolean checkNull(String str){
        if(str.isEmpty() || str.trim().isEmpty())
            return true;
        return false;
    }

    public void clickBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}