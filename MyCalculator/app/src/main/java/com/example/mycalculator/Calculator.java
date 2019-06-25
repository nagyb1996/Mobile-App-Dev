package com.example.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class Calculator extends AppCompatActivity {

    private int gender = -1; //female is 0, male is 1
    private double ideal_weight = 0; // store calculated ideal weight
    private int gender_radio_error = 0; // no gender radio button selected error flag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Button calculator_button = findViewById(R.id.calculator_button); // get calculate button from id
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RadioGroup radioGroup; // create instance of RadioGroup
        radioGroup = findViewById(R.id.radioGroup); // define radio group
        radioGroup.clearCheck(); // clear radio buttons in group onCreate

        // Check which radio button is selected
        // 0 is female, 1 is male
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton male_radio = findViewById(R.id.male_radio);
                if (checkedId == R.id.female_radio) {
                    gender = 0; // female selected
                    if (gender_radio_error == 1) // if there is a no selection error, clear it
                        male_radio.setError(null);
                    Log.v("myApp", "selected gender is female"); // write selected gender to log

                } else if (checkedId == R.id.male_radio) {
                    gender = 1; // male selected
                    if (gender_radio_error == 1)  // if there is a no selection error, clear it
                        male_radio.setError(null);
                    Log.v("myApp", "selected gender is male"); // write selected gender to log
                }
            }
        });

        // define behavior when calculate button is clicked
        calculator_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("myApp", "Calculator Calculate button is clicked"); // write calculate button clicked to log

                // if no gender radio button was selected, throw error and return
                if (gender == -1) {
                    RadioButton male_radio = findViewById(R.id.male_radio);
                    male_radio.setError("Gender cannot be empty"); // throw error
                    gender_radio_error = 1; // set no selection error flag
                    return;
                }

                EditText feet = findViewById(R.id.feet_input); // setup EditText to retrieve height in feet entered
                EditText inches = findViewById(R.id.inches_input); // setup EditText to retrieve height in inches entered

                int entered_feet;
                // ensure a value was entered for height in feet
                if (TextUtils.isEmpty(feet.getText().toString())) {
                    feet.setError("Height in feet cannot be empty"); // throw error if empty
                    return;
                }
                // if there is an input, check that it is an integer
                else {

                    try {
                        Integer.parseInt(feet.getText().toString());
                    } catch (Exception E) {
                        feet.setError("Height in feet must be an integer");
                        return;
                    }

                }

                // ensure that height in feet is greater than zero
                if (Integer.valueOf(feet.getText().toString()) < 1) {
                    feet.setError("Height in feet must be greater than or equal to one"); // throw error less than 1
                    return;
                } else {
                    entered_feet = Integer.valueOf(feet.getText().toString()); // retrieve height in feet entered
                }

                int entered_inches;
                // ensure a value was entered for height in inches
                if (TextUtils.isEmpty(inches.getText().toString())) {
                    inches.setError("Height in inches cannot be empty"); // throw error if empty
                    return;
                }
                // if there is an input, check that it is an integer
                else {

                    try {
                        Integer.parseInt(inches.getText().toString());
                    } catch (Exception E) {
                        inches.setError("Height in inches must be an integer");
                        return;
                    }

                }

                // ensure that height in inches is greater than zero
                if (Integer.valueOf(inches.getText().toString()) < 1) {
                    inches.setError("Height in inches must be greater than or equal to one"); // throw error less than 1
                    return;
                } else {
                    entered_inches = Integer.valueOf(inches.getText().toString()); // retrieve height in feet entered
                }

                int height_inches = ((entered_feet * 12) + (entered_inches)); // combine retrieved values to find entered height in inches

                // ensure that the entered height is not less than minimum, as a negative weight will be returned
                if (gender == 0) { // for females, the minimum height is 3'5"
                    if (height_inches < 41) {
                        feet.setError(" Minimum height for females is 3'5'' "); // throw error less than 1
                        return;
                    }
                } else { // for males, the minimum height is 3'3"
                    if (height_inches < 39) {
                        feet.setError(" Minimum height for males is 3'3'' "); // throw error less than 1
                        return;
                    }
                }

                // print entered and calculated value to log
                Log.v("myApp", "entered feet " + entered_feet);
                Log.v("myApp", "entered inches " + entered_inches);
                Log.v("myApp", "total inches " + height_inches);

                // String to store string of selected gender
                String bundle_gender = "";

                // if female is selected
                if (gender == 0) {

                    // calculate ideal weight using female equation
                    ideal_weight = 45.5 + 2.3 * (height_inches - 60);
                    bundle_gender = "Female"; // set gender string to Female

                    // write ideal weight to log
                    Log.v("myApp", "ideal weight is " + ideal_weight);


                } else if (gender == 1) {

                    // calculate ideal weight using male equation
                    ideal_weight = 50 + (2.3 * (height_inches - 60));
                    bundle_gender = "Male"; // set gender string to Male

                    // write ideal weight to log
                    Log.v("myApp", "ideal weight is " + ideal_weight);

                }

                // create new instance of Intent
                Intent intent = new Intent();

                // intent targets Result class
                intent.setClass(Calculator.this, Result.class);

                // create new bundle, put gender, height in feet and inches, and calculated ideal weight in bundle
                Bundle bundle = new Bundle();
                bundle.putString("bundle_gender", bundle_gender);
                bundle.putInt("height_feet", entered_feet);
                bundle.putInt("height_inches", entered_inches);
                bundle.putDouble("ideal_weight", ideal_weight);

                // write contents of bundle to log
                Log.v("myApp", "gender " + bundle_gender);
                Log.v("myApp", "height feet " + entered_feet);
                Log.v("myApp", "height inches " + entered_inches);
                Log.v("myApp", "ideal_weight" + ideal_weight);

                // add bundle to intent
                intent.putExtras(bundle);
                // start result activity with defined intent
                startActivity(intent);
            }
        });


    }
}

