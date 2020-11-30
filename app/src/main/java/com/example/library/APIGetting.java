package com.example.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class APIGetting extends AsyncTask<Word, String, String> {

    private Context m_con;

    public APIGetting(Context con) {
        m_con = con;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(m_con, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(Word... word) {
        if (word[0].getWord().isEmpty())
            return APIWord.getWords();
        else {
            try {
                return APIWord.addWord(word[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent;
//        if (s == "200")
//            intent = new Intent(m_con, DictionaryActivity.class);
//        else
            intent = new Intent(m_con, DictionaryActivity.class);
        intent.putExtra("list", s);
        Activity acti = (Activity) m_con;
        acti.startActivity(intent);
        Toast.makeText(m_con, "Finished", Toast.LENGTH_SHORT).show();
    }
}
