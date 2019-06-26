package edu.wit.mobileapp.lab_8_assignment_3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create image & place it at /res/drawable
        final Bitmap defaultImage = BitmapFactory.decodeResource(getResources(), R.drawable.catbounce);

        // String to store date from device
        final Date date = Calendar.getInstance().getTime();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String formattedDate = dateFormat.format(date);

        // Create testing data
        List<GridItem> list = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            GridItem gridItem = new GridItem();
            gridItem.image = defaultImage;
            gridItem.name = "Title-" + i;
            gridItem.date = formattedDate;
            list.add(gridItem);
        }

        // Create and assign GridItemAdapter
        GridItemAdapter gridItemAdapter = new GridItemAdapter(this, 0, list);
        GridView gridView = findViewById(R.id.GridView01);
        gridView.setAdapter(gridItemAdapter);

    }
}
