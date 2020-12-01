package com.example.kiemtraapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ArrayList<Word> lst_word;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    public LinkedList<Word> mWordList = new LinkedList<Word>();
    public Context m_context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_context = this;
        String jSonString = null;
        try {
            jSonString = new APIGetting(this).execute(new Word()).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if(get_lst_word(jSonString)){
            mWordList = convertToList(lst_word);
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new WordListAdapter(this, mWordList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        search(this);
    }

    private void search(Context context){
        EditText field = findViewById(R.id.editSearch);
        field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String getText = ((EditText) findViewById(R.id.editSearch)).getText().toString().trim();
                if (getText.isEmpty()) {
                    mRecyclerView = findViewById(R.id.recyclerView);
                    mAdapter = new WordListAdapter(context, mWordList);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    return;
                }
                LinkedList<Word> result = new LinkedList<Word>();
                int len = mWordList.size();
                for (int i = 0; i < len; i++) {
                    if (mWordList.get(i).getWord().toLowerCase().contains(getText.toLowerCase()))
                        result.addLast(mWordList.get(i));
                }

                mRecyclerView = findViewById(R.id.recyclerView);
                mAdapter = new WordListAdapter(context, result);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            }
        });
    }

    private Boolean get_lst_word(String jSonString) {
        try {
            lst_word = new ArrayList();
            JSONArray jr = new JSONArray(jSonString);
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject jb = (JSONObject) jr.getJSONObject(i);
                Word word = new Word();
                word.setWord(jb.getString("word"));
                word.setDefinition(jb.getString("definition"));
                lst_word.add(word);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LinkedList<Word> convertToList(ArrayList<Word> arrWord) {
        LinkedList<Word> lstWord = new LinkedList<Word>();
        arrWord.forEach(lstWord::addLast);
        return lstWord;
    }

    public void clickSearch(View view) throws ExecutionException, InterruptedException {
        String getText = ((EditText) findViewById(R.id.editSearch)).getText().toString();
        if(getText.length() < 3) {
            Toast.makeText(this, "Nhap it nhat 3 ky tu", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            String jSonString;
            jSonString = new APIGetting(this).execute(new Word(getText, "")).get();
            if(get_lst_word(jSonString)){
                mWordList = convertToList(lst_word);
            }

            mRecyclerView = findViewById(R.id.recyclerView);
            mAdapter = new WordListAdapter(m_context, mWordList);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(m_context));
        }
    }
}