package com.example.wasteai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wasteai.Model.Prevalent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComplaintActivity extends AppCompatActivity {


    Button complaintbutton;
    EditText queID, queDescriptn;
    DatabaseReference complaintList;
    ComplaintsData complaintsData;
    String[] items = new String[]{"Select Complaint Type", "Unpicked Open Garbage","Unpicked Dustbin Garbage","Installation of Dustbin","Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        queID = (EditText)findViewById(R.id.editTextQueryID);
        queDescriptn = (EditText)findViewById(R.id.editTextComplaintDescriptn);

        final Spinner complaintspin = (Spinner) findViewById(R.id.complaintregardspinner);
        final ArrayAdapter complaintdrop = new ArrayAdapter(this,android.R.layout.simple_spinner_item,items);
        complaintdrop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        complaintspin.setAdapter(complaintdrop);

        complaintbutton = findViewById(R.id.complaintbutton);
        complaintsData = new ComplaintsData();

        complaintList = FirebaseDatabase.getInstance().getReference().child("ComplaintsData");

        complaintbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String queryid = queID.getText().toString().trim();
                String phno = Prevalent.currentOnlineUser.getPhone();
                String queryreg = complaintspin.getSelectedItem().toString();
                String querydesc = queDescriptn.getText().toString().trim();

                complaintsData.setQueryID(queryid);
                complaintsData.setPhNo(phno);
                complaintsData.setComplaintRegarding(queryreg);
                complaintsData.setComplaintDescription(querydesc);

                complaintList.push().setValue(complaintsData);

                queID.setText("");
                complaintspin.setSelection(0);
                queDescriptn.setText("");
                Toast.makeText(ComplaintActivity.this, "Complaint submitted", Toast.LENGTH_LONG).show();

            }
        });



    }
}