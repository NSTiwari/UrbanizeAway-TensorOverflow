package com.example.wasteai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wasteai.Model.Prevalent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyHistoryActivity extends AppCompatActivity {

    // creating variables for our list view.
    private ListView historyListView;

    // creating a variable for database reference.
    DatabaseReference retrievehistory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_history);

        // initializing variables for listviews.
        historyListView = findViewById(R.id.historyListView);

        // creating a new array list.
        final ArrayList<String> historyArrayList = new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.textView28, historyArrayList);
        historyListView.setAdapter(arrayAdapter);

        // below line is used for getting reference
        // of our Firebase Database.
        retrievehistory = FirebaseDatabase.getInstance().getReference().child("GalleryPicturesData");
//        retrievehistory = FirebaseDatabase.getInstance().getReference().child("GalleryPicturesData").child(Prevalent.currentOnlineUser.getPhone());
//        retrievehistory = FirebaseDatabase.getInstance().getReference().child("Aadmin");

        retrievehistory.orderByChild("userNo").equalTo(Prevalent.currentOnlineUser.getPhone()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                historyArrayList.clear();
                for (DataSnapshot snap : snapshot.getChildren()){
                    GalleryPicturesData galleryPicturesData = snap.getValue(GalleryPicturesData.class);
                    String gallpicdata = " QueryID: "+galleryPicturesData.getQueryID() +"\n Latitude: " + galleryPicturesData.getLatitude()
                            +"\n Longitude: " + galleryPicturesData.getLongitude() + "\n Country: " + galleryPicturesData.getCountry()
                            + "\n Locality: " + galleryPicturesData.getLocality() + "\n Address: " + galleryPicturesData.getAddress()
                            + "\n UserNo: " + galleryPicturesData.getUserNo();
                    historyArrayList.add(gallpicdata);
//                    historyArrayList.add(snap.getValue().toString());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}