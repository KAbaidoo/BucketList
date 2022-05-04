package com.example.bucketlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bucketlist.ui.DetailFragment;
import com.example.bucketlist.ui.HomeFragment;
import com.example.bucketlist.ui.ListFragment;
import com.example.bucketlist.ui.SearchFragment;
import com.example.bucketlist.util.OnItemSelectedListener;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    HomeFragment homeFragment = new HomeFragment();
    SearchFragment searchFragment = new SearchFragment();
    ListFragment listFragment = new ListFragment();
    int container = R.id.fragment_container;
    BottomNavigationView mBottomNavigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            replaceFragment(homeFragment);
        }

//        grab bottom navigation button
//        open fragment when button clicked
         mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home_page:
                    replaceFragment(homeFragment);
                    return true;
                case R.id.search_page:
                    replaceFragment(searchFragment);
                    return true;
                case R.id.list_page:
                    replaceFragment(listFragment);
            }
            return false;
        });


    }

    void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(container, fragment).commit();
    }

    @Override
    public void onSignOutSelected() {
        signOut();
    }

    @Override
    public void onDetailSelected() {
        getSupportFragmentManager().beginTransaction()
                .replace(container, new DetailFragment())
                .addToBackStack(null)
                .commit();
        showToast("Detail button clicked!");

    }

    @Override
    public void onSearchSelected() {
        showToast("Search selected!");
    }

    @Override
    public void hideBottomNavView() {
        mBottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void unHideBottomNavView() {
        mBottomNavigationView.setVisibility(View.VISIBLE);
    }


    public void signOut() {
        // [START auth_fui_signOut]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(task -> {
                    showToast("You signed out!");
                    startFirebaseAuthUiActivity();
                    // ...
                });
        // [END auth_fui_signOut]

    }

    protected void startFirebaseAuthUiActivity() {
//        Take user back to sign in activity
        Intent i = new Intent(this, FirebaseAuthUIActivity.class);
        startActivity(i);
    }


    private void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }


}