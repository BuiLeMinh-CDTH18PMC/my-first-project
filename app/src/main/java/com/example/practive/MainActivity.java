package com.example.practive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

    public RecyclerView recyclerView;
    public TinTucAdapter iAdapter;
    ArrayList<TinTuc> lst_word;
    LinkedList<TinTuc> lst;
    public Context m_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lst = new LinkedList<TinTuc>();

        m_context = this;
        String jSonString = null;
        try {
            jSonString = new APIGetting(this).execute(new TinTuc()).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if(get_lst_word(jSonString)){
            lst = convertToList(lst_word);
        }

        recyclerView = findViewById(R.id.recyclerView);
        iAdapter = new TinTucAdapter(this, lst);
        recyclerView.setAdapter(iAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private Boolean get_lst_word(String jSonString) {
        try {
            lst_word = new ArrayList();
            JSONArray jr = new JSONArray(jSonString);
            int num = jr.length();
            for (int i = 0; i < num; i++) {
                JSONObject jb = (JSONObject) jr.getJSONObject(i);
                TinTuc tintuc = new TinTuc();
                tintuc.setTieuDe(jb.getString("td"));
                tintuc.setNoiDung(jb.getString("nd"));
                tintuc.setLoaiDanhMuc(Integer.parseInt(jb.getString("dm")));
                lst_word.add(tintuc);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LinkedList<TinTuc> convertToList(ArrayList<TinTuc> arrWord) {
        LinkedList<TinTuc> lstTin = new LinkedList<TinTuc>();
        arrWord.forEach(lstTin::addLast);
        return lstTin;
    }

    public void clickSearch(View view) {
        int sl = 0;
        String getText = ((EditText) findViewById(R.id.editSearch)).getText().toString();
        if(xoaGiua(getText.trim()).length() < 4){
            Toast.makeText(m_context, "Tu khoa qua ngan", Toast.LENGTH_SHORT).show();
            return;
        }
        getText = xoaGiua(getText.trim());
        if (getText.isEmpty()) {
            recyclerView = findViewById(R.id.recyclerView);
            iAdapter = new TinTucAdapter(view.getContext(), lst);
            recyclerView.setAdapter(iAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            return;
        }

        LinkedList<TinTuc> result = new LinkedList<TinTuc>();
        int len = lst.size();
        for (int i = 0; i < len; i++) {
            if (lst.get(i).getTieuDe().toLowerCase().contains(getText.toLowerCase())
                    || lst.get(i).getNoiDung().toLowerCase().contains(getText.toLowerCase())){
                result.addLast(lst.get(i));
                sl++;
            }
        }

        Toast.makeText(m_context, "So luong ket qua: "+sl, Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.recyclerView);
        iAdapter = new TinTucAdapter(view.getContext(), result);
        recyclerView.setAdapter(iAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    public String xoaGiua(String str){
        return str.replaceAll(" ", "");
    }
}