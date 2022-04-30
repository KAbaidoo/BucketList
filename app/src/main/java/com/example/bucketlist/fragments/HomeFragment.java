package com.example.bucketlist.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bucketlist.FirebaseAuthUIActivity;
import com.example.bucketlist.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class HomeFragment extends Fragment {
    public HomeFragment() {
        super(R.layout.fragment_home);
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//
//        return inflater.inflate(R.layout.fragment_home,container,false);
//
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button mSignoutButton = (Button) view.findViewById(R.id.button_signOut);

        mSignoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });


    }

    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(getContext())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        showToast("You signed out!");
                        startFirebaseAuthUiActivity();

                        // ...
                    }
                });
        // [END auth_fui_signout]

    }
    protected void startFirebaseAuthUiActivity() {
//        Start the main activity
        Intent i = new Intent(getContext(), FirebaseAuthUIActivity.class);
        startActivity(i);

    }
    private void showToast(String msg) {
        Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}