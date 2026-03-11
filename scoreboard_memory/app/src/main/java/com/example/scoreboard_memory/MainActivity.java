package com.example.scoreboard_memory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button add_user;
    TextView userId;
    TextView username;
    TextView score;
    Button aktualizuj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        add_user = findViewById(R.id.button);
        aktualizuj = findViewById(R.id.button2);
        userId = findViewById(R.id.editTextText);
        username = findViewById(R.id.editTextText2);
        score = findViewById(R.id.editTextText3);

        ListView listView = findViewById(R.id.listView);
        List<Item> lista = new ArrayList<>();


        ItemAdapter adapter = new ItemAdapter(this, lista);
        listView.setAdapter(adapter);

        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jso = new JSONObject();
                JSONArray jsonArray;
                File file = new File(getFilesDir(), "scores.json");
                if (file.exists()){
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null){
                            sb.append(line);
                        }
                        bufferedReader.close();
                        jsonArray = new JSONArray(sb.toString());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    jsonArray = new JSONArray();
                }
                try{
                    jso.put("userId", Integer.parseInt(userId.getText().toString()));
                    jso.put("username", username.getText().toString());
                    jso.put("score", Integer.parseInt(score.getText().toString()));

                    jsonArray.put(jso);

                    FileOutputStream fso = new FileOutputStream(file);
                    fso.write(jsonArray.toString().getBytes());
                    fso.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
        });

        aktualizuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jso = new JSONObject();
                JSONArray jsonArray;
                File file = new File(getFilesDir(), "scores.json");
                if (file.exists()){
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null){
                            sb.append(line);
                        }
                        bufferedReader.close();
                        jsonArray = new JSONArray(sb.toString());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    jsonArray = new JSONArray();
                    Toast.makeText(MainActivity.this, "Plik nie Istnieje, prosze najpierw kogos dodac", Toast.LENGTH_SHORT).show();
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int userId = jsonObject.getInt("userId");
                        String username = jsonObject.getString("username");
                        int score = jsonObject.getInt("score");
                        Log.v("test", userId + " " + username + " " + score);
//                        if (lista.contains(lista.add(new Item(userId, username, score)))){
//                        }else {
//                            lista.add(new Item(userId, username, score));
//                        }

                        lista.add(new Item(userId, username, score));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });



    }

}