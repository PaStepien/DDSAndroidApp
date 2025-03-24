package com.example.firstapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String readURL(String inputUrl) {
        String value = "";
        URL url = null;
        try {
            url = new URL(inputUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            System.out.println(con);
            con.setRequestMethod("GET");
            InputStream i = con.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(i));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                value += inputLine + "\n";
            }
            in.close();
        } catch (ProtocolException ex) {
            throw new RuntimeException(ex);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return value;
    }

    MyData mydata;
    EditText textInput;

    Button button;

    TextView textView;

    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textView2 = findViewById(R.id.textView2);

        System.out.println("Test");

        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String data = readURL("https://raw.githubusercontent.com/sunetb/U/main/greeting.txt");
                System.out.println(data);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setText(data);
                    }
                });
            }

        });
        backgroundThread.start();


        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        textInput = findViewById(R.id.editTextText);


        mydata = MyData.getInstance();

        textView.setText(mydata.myName);
        button.setOnClickListener(this);


        System.out.println("This is the android app");
    }

    @Override
    public void onClick(View v) {
        String input = "Hello how are you, " + textInput.getText().toString();
        System.out.println(input);
        mydata.myName = input;
        // Update the TextView
        textView.setText(mydata.myName);

    }

    ;

}