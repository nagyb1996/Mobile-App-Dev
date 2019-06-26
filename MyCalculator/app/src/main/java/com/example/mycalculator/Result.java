package com.example.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Result extends AppCompatActivity {

    // define decimal formal to display ideal weight with one decimal place
    private static DecimalFormat df = new DecimalFormat("0.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button result_button = findViewById(R.id.result_button); // get reset button from id
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // create bundle from intent.extras from calculator activity
        Bundle bundle = this.getIntent().getExtras();

        // get gender, height in feet and inches, and calculated ideal weight from bundle
        String gender = bundle.getString("bundle_gender");
        int height_feet = bundle.getInt("height_feet");
        int height_inches = bundle.getInt("height_inches");
        double ideal_weight = bundle.getDouble("ideal_weight");

        // display entered gender
        TextView gender_msg = findViewById(R.id.gender_display);
        gender_msg.setText(gender);

        // display entered height formatted with labels
        TextView height_msg = findViewById(R.id.height_display);
        String height_final = height_feet + "' " + height_inches + "''";
        height_msg.setText(height_final);

        // round the calculated weight and display formatted with labels
        String rounded_weight = df.format(ideal_weight);
        TextView ideal_weight_msg = findViewById(R.id.ideal_weight_display);
        String weight_final = rounded_weight + " kg";
        ideal_weight_msg.setText(weight_final);

        // when the reset button is clicked, start Calculator activity to reset app
        result_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("myApp", "Result Reset button is clicked");
                startActivity(new Intent(Result.this, Calculator.class));
            }
        });
    }

}

