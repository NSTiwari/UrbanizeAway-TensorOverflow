package com.example.wasteai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wasteai.Model.ScatterChartActivity;

import org.w3c.dom.Text;

public class VisualsActivity extends AppCompatActivity {


    ListView listView;
    String mTitle[] = {"Pie", "Bar", "Scatter", "Line", "Radius"};
    String mDesc[] = {"Total waste % in Mumbai", "Waste Index Monthwise", "Types of waste", "Improvement in cleanliness", "Location wise sorting"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visuals);

        listView = findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(this, mTitle, mDesc);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(VisualsActivity.this, GraphActivity.class);
                    startActivity(intent);
                }
                if(position==1) {
                    Intent intent = new Intent(VisualsActivity.this, BarChartActivity.class);
                    startActivity(intent);
                }
                if(position==2) {
                    Intent intent = new Intent(VisualsActivity.this, ScatterChartActivity.class);
                    startActivity(intent);
                }
                if(position==3) {
                    Intent intent = new Intent(VisualsActivity.this, LineChartActivity.class);
                    startActivity(intent);
                }
                if(position==4) {
                    Intent intent = new Intent(VisualsActivity.this, RadiusChartActivity2.class);
                    startActivity(intent);
                }

            }
        });
    }

    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String rTitle[];
        String rDesc[];
        MyAdapter(Context c, String title[], String description[]){
            super(c, R.layout.row, R.id.textView, title);
            this.context=c;
            this.rTitle=title;
            this.rDesc=description;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView myTitle = row.findViewById(R.id.textView);
            TextView myDesc = row.findViewById(R.id.textView2);

            myTitle.setText(rTitle[position]);
            myDesc.setText(rDesc[position]);


            return row;
        }
    }
}
