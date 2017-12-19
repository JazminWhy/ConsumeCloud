package com.example.jasmin.consumecloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
String jsonString = "";
TextView ausgabe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ausgabe = findViewById(R.id.ausgabe);

        //Konstruktur override => TextView übergeben
        MyAsynchTast cloud1 = new MyAsynchTast(ausgabe);
        //die onBackground wird NUR von android aufgerufen, die onpostexecute wird nur von android aufgerufen
        cloud1.execute("http://webtechlecture.appspot.com/personen/list");
    }
}
