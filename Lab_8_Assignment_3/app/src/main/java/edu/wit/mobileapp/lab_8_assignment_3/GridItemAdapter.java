package edu.wit.mobileapp.lab_8_assignment_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridItemAdapter extends ArrayAdapter<GridItem> {
    private LayoutInflater mInflater;

    public GridItemAdapter(Context context, int rid, List<GridItem> list){
        super(context, rid, list);
        mInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get data
        GridItem item = getItem(position);

        // Use layout file to generate View
        View view = mInflater.inflate(R.layout.grid_item, null);

        // Set image
        ImageView image;
        image = view.findViewById(R.id.image);
        image.setImageBitmap(item.image);

        // Set name
        TextView name;
        name = view.findViewById(R.id.name);
        name.setText(item.name);

        // Set date
        TextView dateTextView = view.findViewById(R.id.date);
        dateTextView.setText(item.date);

        return view;
    }
}