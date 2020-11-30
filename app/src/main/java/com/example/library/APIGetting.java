package com.example.library;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.IOException;

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
        Toast.makeText(m_con, "Finished", Toast.LENGTH_SHORT).show();
    }
}
