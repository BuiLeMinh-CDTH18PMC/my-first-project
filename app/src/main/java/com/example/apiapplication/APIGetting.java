package com.example.apiapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.LinkedList;

public class APIGetting extends AsyncTask<String, String, String> {
    private Context m_con;
    public APIGetting(Context con)
    {
        m_con = con;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(m_con, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(m_con, s, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(m_con, Question.class);
        intent.putExtra("message", s);
        Activity activity = (Activity) m_con;
        activity.startActivity(intent);
    }

    @Override
    protected String doInBackground(String... level) {
        return APIQuestion.getQuestions(level[0]);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
