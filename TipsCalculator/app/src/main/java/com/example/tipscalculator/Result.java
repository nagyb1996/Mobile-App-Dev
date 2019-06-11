package com.example.tipscalculator;

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

    //define decimal format to display tips in dollars and cents
    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button reset_button = findViewById(R.id.reset_button); // get reset button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = this.getIntent().getExtras(); // get bundle from Calculator activity

        // get tip adjusted and unadjusted from bundle
        Double adjusted_tip = bundle.getDouble("tip_adjusted");
        Double unadjusted_tip = bundle.getDouble("tip_unadjusted");

        // display adjusted tip formatted with dollar sign
        TextView adjusted_tip_msg = findViewById(R.id.tip_with_adjustment_value);
        String rounded_adjusted = df.format(adjusted_tip);
        String adjusted_tip_string = "$" + rounded_adjusted + " per person";
        adjusted_tip_msg.setText(adjusted_tip_string);

        // display unadjusted tip formatted with dollar sign
        TextView unadjusted_tip_msg = findViewById(R.id.tip_without_adjustment_value);
        String rounded_unadjusted = df.format(unadjusted_tip);
        String unadjusted_tip_string = "$" + rounded_unadjusted + " per person";
        unadjusted_tip_msg.setText(unadjusted_tip_string);

        // on reset call calculator activity
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("myApp", "Result Reset button is clicked");
                startActivity(new Intent(Result.this, Calculator.class));
            }
        });

    }

}
