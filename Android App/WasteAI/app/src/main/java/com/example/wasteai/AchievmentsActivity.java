package com.example.wasteai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.wasteai.Model.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AchievmentsActivity extends AppCompatActivity {

    DatabaseReference awardCount;
//    private ProgressBar progressBarAnimation;
//    private ObjectAnimator progressAnimator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievments);

//        init();
//        progressAnimator.setDuration(7000);
//        progressAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                progressBarAnimation.setVisibility(View.GONE);
//            }
//        });

        final ImageView award1 = (ImageView)findViewById(R.id.award1);
        final ImageView award2 = (ImageView)findViewById(R.id.award2);
        final ImageView award3 = (ImageView)findViewById(R.id.award3);
        final ImageView award4 = (ImageView)findViewById(R.id.award4);
        final ImageView award5 = (ImageView)findViewById(R.id.award5);
        final ImageView award6 = (ImageView)findViewById(R.id.award6);

        awardCount = FirebaseDatabase.getInstance().getReference().child("AwardsData");
        Query userPictureCount = awardCount.child(Prevalent.currentOnlineUser.getPhone());

        userPictureCount.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = (int) snapshot.getChildrenCount();


                if(count>=1 && count<25)
                {
                    award1.setImageResource(R.drawable.early_bird_unlocked);
                }
                else if(count>=25 && count<50)
                {
                    award1.setImageResource(R.drawable.early_bird_unlocked);
                    award2.setImageResource(R.drawable.the_picker_unlocked);
                }
                else if(count>=50 && count<100)
                {
                    award1.setImageResource(R.drawable.early_bird_unlocked);
                    award2.setImageResource(R.drawable.the_picker_unlocked);
                    award3.setImageResource(R.drawable.the_collector_unlocked);
                }
                else if(count>=100 && count<500)
                {
                    award1.setImageResource(R.drawable.early_bird_unlocked);
                    award2.setImageResource(R.drawable.the_picker_unlocked);
                    award3.setImageResource(R.drawable.the_collector_unlocked);
                    award4.setImageResource(R.drawable.good_citizen_unlocked);
                }
                else if(count>=500 && count<1000)
                {
                    award1.setImageResource(R.drawable.early_bird_unlocked);
                    award2.setImageResource(R.drawable.the_picker_unlocked);
                    award3.setImageResource(R.drawable.the_collector_unlocked);
                    award4.setImageResource(R.drawable.good_citizen_unlocked);
                    award5.setImageResource(R.drawable.responsible_citizen_unlocked);
                }
                else if(count>=1000)
                {
                    award1.setImageResource(R.drawable.early_bird_unlocked);
                    award2.setImageResource(R.drawable.the_picker_unlocked);
                    award3.setImageResource(R.drawable.the_collector_unlocked);
                    award4.setImageResource(R.drawable.good_citizen_unlocked);
                    award5.setImageResource(R.drawable.responsible_citizen_unlocked);
                    award6.setImageResource(R.drawable.unstoppable_maniac_unlocked);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//        int count = 342;




    }

//    private void init(){
//        progressBarAnimation = findViewById(R.id.progressBar);
//        progressAnimator = ObjectAnimator.ofInt(progressBarAnimation,"progress",0,50);
//
//    }
}