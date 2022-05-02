package com.example.bucketlist.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bucketlist.R;

public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button mSignOutButton = view.findViewById(R.id.button_signOut);
        mSignOutButton.setOnClickListener(v -> showToast("sign out button clicked!"));

        Button mDetilsButton = view.findViewById(R.id.button_detail);
        mDetilsButton.setOnClickListener(v -> showToast("Detail button clicked!"));
    }

    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener {
        void onSignOutSelected();

        void onDetailSelected();

    }




    private void showToast(String msg) {
        Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}