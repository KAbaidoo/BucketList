package com.example.bucketlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class InterestsActivity extends AppCompatActivity {
    public static final String INTERESTS = "com.example.bucketlist.viewmodel.interests";
    private ArrayList<String> interests = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        GridLayout gridLayout = findViewById(R.id.interests_grid);
        Button mButton = findViewById(R.id.button_done);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra(INTERESTS, interests);
                setResult(Activity.RESULT_OK, i);;
        finish();
            }
        });
        //        Get categories



        final int childCount = gridLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            MaterialCardView cardView = (MaterialCardView) gridLayout.getChildAt(i);
            cardView.setOnClickListener(v -> {
                if (cardView.isChecked()) {
                    cardView.setChecked(false);
                    TextView tv = (TextView) cardView.getChildAt(0);
                    interests.remove(tv.getText().toString());
                    Log.d("INTERESTS", String.valueOf(interests));
                } else {
                    cardView.setChecked(true);
                    TextView tv = (TextView) cardView.getChildAt(0);
                    interests.add(tv.getText().toString());
                    Log.d("INTERESTS", String.valueOf(interests));
                }
            });
        }


    }


    private void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}