package com.example.apiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Question extends AppCompatActivity {

    ArrayList<QuizObject> lst_cauhoi;
    int point=0;
    int cur=0;
    TextView m_txt_num;
    TextView m_txt_content;
    TextView m_rad_DA1;
    TextView m_rad_DA2;
    TextView m_rad_DA3;
    TextView m_rad_DA4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        m_txt_num = (TextView) findViewById(R.id.txtNum);
        m_txt_content = (TextView) findViewById(R.id.txtContent);
        m_rad_DA1 = (RadioButton) findViewById(R.id.radDA1);
        m_rad_DA2 = (RadioButton) findViewById(R.id.radDA2);
        m_rad_DA3 = (RadioButton) findViewById(R.id.radDA3);
        m_rad_DA4 = (RadioButton) findViewById(R.id.radDA4);
        Intent intent = getIntent();
        String jSonString = intent.getStringExtra("message");
//        Toast.makeText(this, "reception: "+jSonString, Toast.LENGTH_SHORT).show();
        if( get_lst_cauhoi(jSonString)==true )
        {
            m_txt_num.setText("Câu hỏi 1:");
            m_txt_content.setText(lst_cauhoi.get(0).NoiDung);
            m_rad_DA1.setText("A. "+lst_cauhoi.get(0).DapAn1);
            m_rad_DA2.setText("B. "+lst_cauhoi.get(0).DapAn2);
            m_rad_DA3.setText("C. "+lst_cauhoi.get(0).DapAn3);
            m_rad_DA4.setText("D. "+lst_cauhoi.get(0).DapAn4);
        }
        else
        {
            m_txt_content.setText("API can not run.");
            m_txt_num.setVisibility(View.INVISIBLE);
            m_rad_DA1.setVisibility(View.INVISIBLE);
            m_rad_DA2.setVisibility(View.INVISIBLE);
            m_rad_DA3.setVisibility(View.INVISIBLE);
            m_rad_DA4.setVisibility(View.INVISIBLE);
        }
    }

    private Boolean get_lst_cauhoi(String jSonString){
        try {
            lst_cauhoi=new ArrayList();
            JSONArray jr = new JSONArray(jSonString);
            int num = jr.length();
            for(int i=0;i<num;i++)
            {
                JSONObject jb = (JSONObject)jr.getJSONObject(i);
                QuizObject quiz=new QuizObject();
                quiz.NoiDung = jb.getString("NoiDung");
                quiz.DapAn1 = jb.getString("DapAn1");
                quiz.DapAn2 = jb.getString("DapAn2");
                quiz.DapAn3 = jb.getString("DapAn3");
                quiz.DapAn4 = jb.getString("DapAn4");
                quiz.Dung = jb.getString("DA_Dung");
                quiz.Chon="0";
                lst_cauhoi.add(quiz);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

}