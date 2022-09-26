package com.example.wasteai;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.wasteai.R;

public class CivicBodiesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    //create a list of items for the spinner.
    String[] items = new String[]{"Select any Civic Bodies","Brihanmumbai Municipal Corporation", "Navi Mumbai Municipal Corporation", "Kolkata Municipal Corporation","Thane Municipal Corporation","Kalyan-Dombivli Municipal Corporation"};
    String hello;
    TextView orginfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civic_bodies);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        orginfo = (TextView) findViewById(R.id.civicbodyinfo);
        orginfo.setClickable(true);
        orginfo.setMovementMethod(LinkMovementMethod.getInstance());
        orginfo.setLinkTextColor(Color.BLUE);

        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,items);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Toast.makeText(getApplicationContext(), items[position], Toast.LENGTH_LONG).show();

        switch (position) {
            case 0:
                hello="";
                orginfo.setText(hello);
                break;
            case 1:
                hello = "Bombay Municipal Corporation is the governing civic body of Mumbai. <br>HelpLine No: 022 – 24962125.<br> <a href=\"https://goo.gl/maps/TR7i6bPJGsEagBDG7\">Head Office</a>";
                orginfo.setText(Html.fromHtml(hello));
                break;
            case 2:
                hello = "The Navi Mumbai Municipal Corporation is the municipal organisation of Navi Mumbai, Maharashtra. <br>HelpLine No:  2756 7070, 2756 7071, 9769894944.<br> <a href=\"https://goo.gl/maps/DnRKuV8Wwen2ru569\">Head Office</a>";
                orginfo.setText(Html.fromHtml(hello));
                break;
            case 3:
                hello = "Kolkata Municipal Corporation is responsible for the civic infrastructure and administration of the Indian city of Kolkata. <br>HelpLine No: 2286-1212/1313/1414.<br> <a href='https://goo.gl/maps/ZZ3dWZiJYeaBDxa36'>Head Office</a>";
                orginfo.setText(Html.fromHtml(hello));
                break;
            case 4:
                hello = "The Thane Municipal Corporation, is the civic body that governs the Thane district. <br>HelpLine No: +91-22-25331590 +91-22-25331211.<br><a href=\"https://goo.gl/maps/ZAdYek623gmiWvTr6\">Head Office</a>";
                orginfo.setText(Html.fromHtml(hello));
                break;
            case 5:
                hello = "Kalyan-Dombivli Municipal Corporation is the governing body of the city of Kalyan-Dombivli. <br> HelpLine No: 2203621.<br> <a href=\"https://goo.gl/maps/i5JJr762KUx6yXLWA\">Head Office</a>";
                orginfo.setText(Html.fromHtml(hello));
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}