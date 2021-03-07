package com.example.bankaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Accounts extends AppCompatActivity {

    private static final String API_KEY = BuildConfig.ApiKey;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        lv = findViewById(R.id.listView);
        Context context = getApplicationContext();

        LoadPage();

        Button refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoadPage();
                Toast toast = Toast.makeText(getApplicationContext(), "Page refreshed", Toast.LENGTH_LONG);
                toast.show();

            }});
    }


    public void LoadPage(){
        TextView textView = findViewById(R.id.textView3);
        //Request
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = API_KEY;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Here are your accounts :");

                        lv = (ListView) findViewById(R.id.listView);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            //textView.setText("Response is: "+ jsonArray);

                            ArrayList<String> listdata = new ArrayList<String>();
                            if (jsonArray != null) {
                                for (int i=0;i<jsonArray.length();i++){
                                    listdata.add(jsonArray.getString(i));
                                }
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,listdata);
                            lv.setAdapter(adapter);

                            String textToSave = jsonArray.toString();
                            writeFile(textToSave);



                        } catch (JSONException | IOException e) {


                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    JSONArray jsonArray = null;
                    try{jsonArray = new JSONArray(readFromFile());}
                    catch(JSONException e){
                        Toast toast = Toast.makeText(getApplicationContext(), "JsonArray", Toast.LENGTH_LONG);
                        toast.show();
                    };
                    ArrayList<String> listdata = new ArrayList<String>();
                    if (jsonArray != null) {
                        for (int i=0;i<jsonArray.length();i++){
                            listdata.add(jsonArray.getString(i));
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,listdata);
                    lv.setAdapter(adapter);
                    Toast toast = Toast.makeText(getApplicationContext(), "Loading data from the latest saved file", Toast.LENGTH_LONG);
                    toast.show();

                } catch (JSONException e) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Failed to get data from internet or saved file. You have to connect to internet at least once to get the data.", Toast.LENGTH_LONG);
                    toast.show();
                    e.printStackTrace();
                }
            }
        });

        //Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public void writeFile(String data) throws IOException {

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("accounts.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast toast = Toast.makeText(getApplicationContext(), "File filled. You can access them offline too", Toast.LENGTH_LONG);
            toast.show();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = getApplicationContext().openFileInput("accounts.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

}