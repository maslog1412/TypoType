package com.typo.type;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class KeyBoardServiceActivity extends AppCompatActivity {
    EditText email, emails;
    private ProgressDialog progressDialog;

    String emailspec, em;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board_service);
        email = findViewById(R.id.email);
        emails = findViewById(R.id.emails);

    }

    public void submit(View view) {
        emailspec = email.getText().toString();
        em = emails.getText().toString();
        new NetworkTask().execute();
    }

    private class NetworkTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"sendto\":\"cloydmaslog@gmail.com\",\"name\":\"TypoType\",\"replyTo\":\"" + em + "\",\"ishtml\":\"false\",\"title\":\"New Keyboard Build Service\",\"body\":\"" + emailspec + "\"}");
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

                Toast.makeText(KeyBoardServiceActivity.this, "Success", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(KeyBoardServiceActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
