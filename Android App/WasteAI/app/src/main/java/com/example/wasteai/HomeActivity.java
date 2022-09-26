package com.example.wasteai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView graphs;
    TextView complaint;
    TextView civicbody;
    TextView captureimg;
    TextView takeimg;
    TextView achievements;
    TextView history;
    TextView detection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        graphs=(TextView)findViewById(R.id.graph);
        complaint=(TextView)findViewById(R.id.complaint);
        civicbody=(TextView)findViewById(R.id.civicbody);
//        captureimg=(TextView)findViewById(R.id.captureImg);
        takeimg=(TextView)findViewById(R.id.takeimg);
        achievements = (TextView)findViewById(R.id.txtachievements);
        history=(TextView)findViewById(R.id.myhistory);
        detection = (TextView) findViewById(R.id.detection);

        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAchievementsActivity();
            }
        });

        graphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGraphActivity();
            }
        });

        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openComplaintActivity();
            }
        });

        civicbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCivicBodiesActivity();
            }
        });

        takeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTakeimageActivity();
            }
        });

//        captureimg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openCaptureActivity();
//            }
//        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistoryActivity();
            }
        });

        detection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                PackageManager manager = getPackageManager();
                try {
                    i = manager.getLaunchIntentForPackage("org.tensorflow.codelabs.objectdetection");
                    if (i == null)
                        throw new PackageManager.NameNotFoundException();
                    i.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(i);
                } catch (PackageManager.NameNotFoundException e) {

                }
            }
        });
    }



    private void openAchievementsActivity() {
        Intent intent = new Intent(HomeActivity.this, AchievmentsActivity.class);
        startActivity(intent);

    }
//    private void openCaptureActivity() {
//        Intent intent = new Intent(HomeActivity.this, CaptureActivity.class);
//        startActivity(intent);
//
//    }

    private void openTakeimageActivity() {
        Intent intent = new Intent(HomeActivity.this, TakeimageActivity.class);
        startActivity(intent);

    }

    public void openGraphActivity(){
        Intent intent = new Intent(HomeActivity.this, VisualsActivity.class);
        startActivity(intent);
    }

    public void openComplaintActivity(){
        Intent intent = new Intent(HomeActivity.this, ComplaintActivity.class);
        startActivity(intent);
    }

    public void openCivicBodiesActivity(){
        Intent intent = new Intent(HomeActivity.this, CivicBodiesActivity.class);
        startActivity(intent);
    }

    public void openHistoryActivity(){
        Intent intent = new Intent(HomeActivity.this, MyHistoryActivity.class);
        startActivity(intent);
    }
}