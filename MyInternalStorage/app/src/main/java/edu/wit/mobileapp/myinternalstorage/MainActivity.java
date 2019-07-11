package edu.wit.mobileapp.myinternalstorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
    private String filename = "myfile";
    private TextView text;
    private Button readDataBtn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String string = "Hello World Android!!\n";
        FileOutputStream outputStream;
        context = getApplicationContext();
        text = findViewById(R.id.text);
        readDataBtn = findViewById(R.id.readBtn);
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
            text.setText("Write data to file " + filename + " successfully.");
            text.invalidate();
        } catch (Exception e) {
            Log.v("myApp", "Error: " + e);
        }
        readDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Read file from the file in internal directory
                FileInputStream fis;
                InputStreamReader isr;
                BufferedReader bufferedReader;
                try {
                    fis = context.openFileInput(filename);
                    isr = new InputStreamReader(fis);
                    bufferedReader = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = bufferedReader.readLine())!= null){
                        sb.append(line);
                    }
                    text.setText(sb.toString());
                    text.invalidate();
                } catch (FileNotFoundException e) {
                    Log.v("myApp", "Error = " + e);
                } catch (IOException e) {
                    Log.v("myApp", "Error = " + e);
                }
            }
        });
    } //end of onCreate()
}