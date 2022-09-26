package com.example.wasteai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wasteai.Model.Prevalent;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;





public class TakeimageActivity extends AppCompatActivity {
    private String saveCurrentDate, saveCurrentTime;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    TextView textView1, textView2, textView3, textView4, textView5;
    FusedLocationProviderClient fusedLocationProviderClient;

    private static final int GalleryPick = 1;
    private Uri ImageUri;
    String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference pictureData, awardData,testData;
    private ProgressDialog loadingBar;
    GalleryPicturesData galleryPicturesData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeimage);

//        Toast.makeText(this, "Welcome Admin...", Toast.LENGTH_SHORT).show();


        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        galleryPicturesData = new GalleryPicturesData();
        pictureData = FirebaseDatabase.getInstance().getReference().child("GalleryPicturesData");
        awardData = FirebaseDatabase.getInstance().getReference().child("AwardsData");
//        testData = FirebaseDatabase.getInstance().getReference().child("TestData");


        AddNewProductButton = (Button) findViewById(R.id.add_new_product);
        InputProductImage = (ImageView) findViewById(R.id.select_product_image);

        loadingBar = new ProgressDialog(this); //initialise

        textView1 = findViewById(R.id.text_view1);
        textView2 = findViewById(R.id.text_view2);
        textView3 = findViewById(R.id.text_view3);
        textView4 = findViewById(R.id.text_view4);
        textView5 = findViewById(R.id.text_view5);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();


            }
        });

        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateProductData();

                if (ActivityCompat.checkSelfPermission(TakeimageActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //when permission granted
                    getLocation();
                } else {
                    //when permission denied
                    ActivityCompat.requestPermissions(TakeimageActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });

    }


    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);



        }
    }

    private void ValidateProductData() {


        if (ImageUri == null) {
            Toast.makeText(this, "Products image is mandatory..", Toast.LENGTH_SHORT).show();
        } else {
            StoreProductInformation();
        }
    }


    private void StoreProductInformation() {

        Calendar calender = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calender.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calender.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");//Unique ramdom key to store image



        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(TakeimageActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(TakeimageActivity.this, "Image uploaded successfully ", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();

                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            downloadImageUrl = task.getResult().toString();
//                            Log.d("TAG",downloadImageUrl);
//                            galleryPicturesData.setUserNo(downloadImageUrl);
//                            testData.child("testid").setValue(downloadImageUrl);

                            Toast.makeText(TakeimageActivity.this, "got the Image url successfully", Toast.LENGTH_SHORT).show();


                        }

                    }
                });

            }
        });
    }



    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //initialize location
                Location location = task.getResult();
                if (location != null) {
                    try {
                        //initialize geoCoder
                        Geocoder geocoder = new Geocoder(TakeimageActivity.this, Locale.getDefault());
                        //initialize address list
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        //set latitude on TextView
                        textView1.setText(Html.fromHtml("<font color='#6200EE'><b>Latitude:</b><br></font>" + addresses.get(0).getLatitude()
                        ));
//                        pictureData.child("Latitude").setValue(addresses.get(0).getLatitude());

                        //set longitude on TextView
                        textView2.setText(Html.fromHtml("<font color='#6200EE'><b>Longitude:</b><br></font>" + addresses.get(0).getLongitude()
                        ));
                        //set Country on TextView
                        textView3.setText(Html.fromHtml("<font color='#6200EE'><b>Country Name:</b><br></font>" + addresses.get(0).getCountryCode()
                        ));
                        //set locality on TextView
                        textView4.setText(Html.fromHtml("<font color='#6200EE'><b>Locality:</b><br></font>" + addresses.get(0).getLocality()
                        ));
                        //set address on TextView
                        textView5.setText(Html.fromHtml("<font color='#6200EE'><b>Address:</b><br></font>" + addresses.get(0).getAddressLine(0)
                        ));

                        galleryPicturesData.setLatitude(addresses.get(0).getLatitude());
                        galleryPicturesData.setLongitude(addresses.get(0).getLongitude());
                        galleryPicturesData.setCountry(addresses.get(0).getCountryCode());
                        galleryPicturesData.setLocality(addresses.get(0).getLocality());
                        galleryPicturesData.setAddress(addresses.get(0).getAddressLine(0));

//                        Log.d("TAG",downloadImageUrl);
                        galleryPicturesData.setUserNo(Prevalent.currentOnlineUser.getPhone());

//                        pictureData.child(Prevalent.currentOnlineUser.getPhone()).push().setValue(galleryPicturesData);
                        String keyID = pictureData.push().getKey();
                        galleryPicturesData.setQueryID("Query"+ keyID.substring(keyID.length() - 6));
                        Log.i("HALLOO","Query"+ keyID.substring(keyID.length() - 6));

                        pictureData.child(keyID).setValue(galleryPicturesData);




//                        awardData.child(Prevalent.currentOnlineUser.getPhone()).push().setValue(keyID);
                        awardData.child(Prevalent.currentOnlineUser.getPhone()).child(keyID).setValue(keyID);

                        Toast.makeText(TakeimageActivity.this,"Location sent successfully",Toast.LENGTH_LONG).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}