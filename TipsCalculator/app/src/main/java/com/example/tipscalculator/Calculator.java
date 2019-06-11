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

    private double tip_per_person_adjusted;
    private double tip_per_person_unadjusted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Button calculator_button = findViewById(R.id.calculate_button);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        calculator_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("myApp", "Calculator Calculate button is clicked");

                EditText bill_input = findViewById(R.id.bill_total_input);
                EditText tip_input = findViewById(R.id.tip_percent_input);
                EditText people_input = findViewById(R.id.number_people_input);

                Double entered_bill;
                if (TextUtils.isEmpty(bill_input.getText().toString())) {
                    bill_input.setError("Bill Total cannot be empty"); // throw error if empty
                    return;
                } else if (Double.valueOf(bill_input.getText().toString()) <= 0 ) {
                    bill_input.setError("Bill Total must be greater than 0"); // throw error if empty
                    return;
                } else {
                    entered_bill = Double.valueOf(bill_input.getText().toString());
                }

                double entered_tip;
                if (TextUtils.isEmpty(tip_input.getText().toString())) {
                    tip_input.setError("Tip Percentage cannot be empty"); // throw error if empty
                    return;
                } else if (Double.valueOf(tip_input.getText().toString()) < 1 || Double.valueOf(tip_input.getText().toString()) > 100) {
                    tip_input.setError("Tip Percentage must be between 1 and 100"); // throw error if empty
                    return;
                } else {
                    entered_tip = Double.valueOf(tip_input.getText().toString());
                }

                int entered_people;
                if (TextUtils.isEmpty(people_input.getText().toString())) {
                    people_input.setError("Number of People cannot be empty"); // throw error if empty
                    return;
                } else if (Integer.valueOf(people_input.getText().toString()) < 1) {
                    people_input.setError("Number of People must greater than or equal to 1"); // throw error if empty
                    return;
                } else {
                    entered_people = Integer.valueOf(people_input.getText().toString());
                }

                Log.v("myApp", "entered bill total " + entered_bill);
                Log.v("myApp", "entered tip percentage  " + entered_tip);
                Log.v("myApp", "entered number of people  " + entered_people);

                tip_per_person_unadjusted = (entered_bill * entered_tip) / (entered_people * 100);

                Log.v("myApp", "unadjusted tip per person  " + tip_per_person_unadjusted);

                if (entered_people >= 6) {

                    Log.v("myApp", "greater than 6 people, adding 18% gratuity");

                    entered_bill = entered_bill + (entered_bill * .18); // add 18% gratuity

                    Log.v("myApp", "new bill total " + entered_bill);

                    tip_per_person_adjusted = (entered_bill * entered_tip) / (entered_people * 100);

                    Log.v("myApp", "adjusted tip per person  " + tip_per_person_adjusted);
                } else {

                    tip_per_person_adjusted = (entered_bill * entered_tip) / (entered_people * 100);

                    Log.v("myApp", "adjusted tip per person  " + tip_per_person_adjusted);

                }

                Intent intent = new Intent();

                intent.setClass(Calculator.this, Result.class);

                Bundle bundle = new Bundle();
                bundle.putDouble("tip_unadjusted", tip_per_person_unadjusted);
                bundle.putDouble("tip_adjusted", tip_per_person_adjusted);


                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

}
