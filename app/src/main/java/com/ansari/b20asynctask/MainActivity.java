package com.ansari.b20asynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button taskButton;
    TextView resultTextView;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskButton = findViewById(R.id.buttonTask);
        resultTextView = findViewById(R.id.textViewResult);

        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    MyTask myTask = new MyTask();
                    myTask.execute();
            }
        });

    }

    class MyTask extends AsyncTask<Void, Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("MY PROGRESS");
            progressDialog.setMessage("Downloading, plz wait !!!!");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {

            for(int i= 0; i<=10;i++){
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String imagineResult = "I have downloaded the data";

            return imagineResult;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
          int val =  values[0];

          resultTextView.setText("downloaded percent is "+ val + " %");
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            resultTextView.setText(result);
        }
    }
}
