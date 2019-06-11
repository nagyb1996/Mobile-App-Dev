package com.example.tipscalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Calculator extends AppCompatActivity {

    private double tip_per_person_adjusted; // store value of calculated adjusted tip
    private double tip_per_person_unadjusted; // store value of calculated unadjusted tip

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Button calculator_button = findViewById(R.id.calculate_button); // find calculate button by id
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // behavior when calculate button is clicked
        calculator_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("myApp", "Calculator Calculate button is clicked");

                // EditText input for bill total, tip percent, and number of people
                EditText bill_input = findViewById(R.id.bill_total_input);
                EditText tip_input = findViewById(R.id.tip_percent_input);
                EditText people_input = findViewById(R.id.number_people_input);

                // ensure appropriate bill total input
                Double entered_bill;
                if (TextUtils.isEmpty(bill_input.getText().toString())) {
                    bill_input.setError("Bill Total cannot be empty"); // throw error if empty
                    return;
                } else if (Double.valueOf(bill_input.getText().toString()) <= 0) {
                    bill_input.setError("Bill Total must be greater than 0"); // throw error if less than or each to 0
                    return;
                } else {
                    entered_bill = Double.valueOf(bill_input.getText().toString()); // else get double value of bill total
                }

                // ensure appropriate tip percent input
                double entered_tip;
                if (TextUtils.isEmpty(tip_input.getText().toString())) {
                    tip_input.setError("Tip Percentage cannot be empty"); // throw error if empty
                    return;
                } else if (Double.valueOf(tip_input.getText().toString()) < 1 || Double.valueOf(tip_input.getText().toString()) > 100) {
                    tip_input.setError("Tip Percentage must be between 1 and 100"); // throw error if less than 1 or greater than 100
                    return;
                } else {
                    entered_tip = Double.valueOf(tip_input.getText().toString()); // else get double value of tip percentage
                }

                // ensure appropriate number of people input
                int entered_people;
                if (TextUtils.isEmpty(people_input.getText().toString())) {
                    people_input.setError("Number of People cannot be empty"); // throw error if empty
                    return;
                } else if (Integer.valueOf(people_input.getText().toString()) < 1) {
                    people_input.setError("Number of People must greater than or equal to 1"); // throw error if less than 1
                    return;
                } else {
                    entered_people = Integer.valueOf(people_input.getText().toString()); // else get int value of number of people
                }

                // write bill total, tip percentage, and total number of people to log
                Log.v("myApp", "entered bill total " + entered_bill);
                Log.v("myApp", "entered tip percentage  " + entered_tip);
                Log.v("myApp", "entered number of people  " + entered_people);

                // calculate unadjusted tip
                tip_per_person_unadjusted = (entered_bill * entered_tip) / (entered_people * 100);

                // write calculated unadjusted tip to log
                Log.v("myApp", "unadjusted tip per person  " + tip_per_person_unadjusted);

                // if there are 6 or more people, add 18% gratuity to bill total and adjust tip
                if (entered_people >= 6) {

                    // write adjustment to log
                    Log.v("myApp", "greater than 6 people, adding 18% gratuity");

                    // add 18% to entered bill
                    entered_bill = entered_bill + (entered_bill * .18); // add 18% gratuity

                    // write adjusted bill total to log
                    Log.v("myApp", "new bill total " + entered_bill);

                    // calculate adjusted tip per person
                    tip_per_person_adjusted = (entered_bill * entered_tip) / (entered_people * 100);

                    // write adjusted to to log
                    Log.v("myApp", "adjusted tip per person  " + tip_per_person_adjusted);
                } else {
                    // else adjusted tip is unadjusted tip
                    tip_per_person_adjusted = tip_per_person_unadjusted;

                    Log.v("myApp", "adjusted tip per person  " + tip_per_person_adjusted);

                }

                // new instance of intent
                Intent intent = new Intent();

                // target result activity
                intent.setClass(Calculator.this, Result.class);

                // create new bundle, add adjusted and unadjusted tip per person
                Bundle bundle = new Bundle();
                bundle.putDouble("tip_unadjusted", tip_per_person_unadjusted);
                bundle.putDouble("tip_adjusted", tip_per_person_adjusted);

                // add bundle to intent
                intent.putExtras(bundle);
                //call result activity
                startActivity(intent);

            }
        });

    }

}
