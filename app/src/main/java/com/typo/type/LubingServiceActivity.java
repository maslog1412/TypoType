package com.typo.type;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LubingServiceActivity extends AppCompatActivity {
    String selectedItems;
    String selectedItems1;
    String stabilizer;
    String emailspec, em;
    EditText emails;
    String message;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lubing_service);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        emails = findViewById(R.id.emails);
        String[] items1 = {"Linear", "Tactile", "Clicky"};
        AutoCompleteTextView autoCompleteTextView1 = findViewById(R.id.autoCompleteTextView1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items1);
        autoCompleteTextView1.setAdapter(adapter1);

        autoCompleteTextView1.setOnItemClickListener((parent, view, position, id) -> {
            selectedItems = (String) parent.getItemAtPosition(position);
        });
        String[] items = {"1", "2","3", "4", "5"};
        AutoCompleteTextView autoCompleteTextView2 = findViewById(R.id.autoCompleteTextView2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        autoCompleteTextView2.setAdapter(adapter2);

        autoCompleteTextView2.setOnItemClickListener((parent, view, position, id) -> {
            selectedItems1 = (String) parent.getItemAtPosition(position);
        });



    }
    public void submit(View view){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        stabilizer = radioButton.getText().toString();
        em = emails.getText().toString();
        message = "Choosen Switch: " + selectedItems + "  Number of Switch: " + selectedItems1 + "  Include Stabilizer: " + stabilizer;
        new NetworkTask().execute();
    }
    private class NetworkTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"sendto\":\"cloydmaslog@gmail.com\"," +
                    "\"name\":\"TypoType\"," +
                    "\"replyTo\":\"" + em + "\"," +
                    "\"ishtml\":\"false\"," +
                    "\"title\":\"New Lubing Service\"," +
                    "\"body\":\"" + message + "\"}");
            Request request = new Request.Builder()
                    .url("https://mail-sender-api1.p.rapidapi.com/")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("X-RapidAPI-Key", "42804f1820msh461c470b51fe748p16463ejsnf95329ac800f")
                    .addHeader("X-RapidAPI-Host", "mail-sender-api1.p.rapidapi.com")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {

                Toast.makeText(LubingServiceActivity.this, "Success", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(LubingServiceActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}