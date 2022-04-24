package com.example.bucketlist;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;
import com.github.appintro.AppIntroPageTransformerType;

public class IntroSlider extends AppIntro {
    PreferencesManager mPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mPreferences = new PreferencesManager(this);

        if (mPreferences.isFirstRun()) {
            showIntroSlides();
        } else {
            startMainActivity();
        }
    }


    protected void showIntroSlides() {
        mPreferences.setFirstRun();
        addSlide(AppIntroFragment.createInstance(
                getString(R.string.slide_1_title),
                getString(R.string.slide_1_description),
                R.drawable.ic_humaaans_1, R.color.yellow));

        addSlide(AppIntroFragment.createInstance(
                getString(R.string.slide_2_title),
                getString(R.string.slide_2_description),
                R.drawable.ic_humaaans_2, R.color.celtic_blue
        ));
//slide
        addSlide(AppIntroFragment.createInstance(
                getString(R.string.slide_3_title),
                getString(R.string.slide_3_description),
                R.drawable.ic_humaaans_3, R.color.cinnabar
        ));


        // Fade Transition
        setTransformer(AppIntroPageTransformerType.Fade.INSTANCE);

        // Show/hide status bar
        showStatusBar(true);

        //Speed up or down scrolling
        setScrollDurationFactor(2);

        //Enable the color "fade" animation between two slides (make sure the slide implements SlideBackgroundColorHolder)
        setColorTransitionsEnabled(true);

        //Prevent the back button from exiting the slides
        setSystemBackButtonLocked(true);

        //Activate wizard mode (Some aesthetic changes)
        setWizardMode(true);

        //Show/hide skip button
        setSkipButtonEnabled(true);

        //Enable immersive mode (no status and nav bar)
        setImmersiveMode();

        //Enable/disable page indicators
        setIndicatorEnabled(true);

        //Dhow/hide ALL buttons
        setButtonsEnabled(true);

    }

    protected void startMainActivity() {
        Intent i = new Intent(getApplicationContext(), FirebaseUIActivity.class);
        startActivity(i);
        finish();
    }

//    @Override
//    protected void onSkipPressed(Fragment currentFragment) {
//        super.onSkipPressed(currentFragment);
//        finish();
//    }

    @Override
    protected void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
//        finish();
        startMainActivity();
    }
}

