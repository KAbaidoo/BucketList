package com.example.bucketlist;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bucketlist.fragments.HomeFragment;
import com.example.bucketlist.fragments.ListFragment;
import com.example.bucketlist.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {


    HomeFragment homeFragment = new HomeFragment();
    SearchFragment searchFragment = new SearchFragment();
    ListFragment listFragment = new ListFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
           replaceFragment(homeFragment);
        }

//        grab bottom navigation button
//        open fragment when button clicked
        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home_page:
                        replaceFragment( homeFragment);
                        return true;
                    case R.id.search_page:
                        replaceFragment( searchFragment);
                        return true;
                    case R.id.list_page:
                        replaceFragment( listFragment);
                }
                return false;
            }
        });

    }

    void replaceFragment ( Fragment fragment){
        int fragmentContainer = R.id.fragment_container;
        getSupportFragmentManager().beginTransaction().replace(fragmentContainer, fragment).commit();
    }


//    public void signOut() {
//        // [START auth_fui_signout]
//        AuthUI.getInstance()
//                .signOut(this)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // ...
//                    }
//                });
//        // [END auth_fui_signout]
//        startFirebaseAuthUIActivity();
//
//    }
//
//    void startFirebaseAuthUIActivity() {
//        Intent i = new Intent(getApplicationContext(), FirebaseAuthUIActivity.class);
//        startActivity(i);
//        finish();
//    }

}