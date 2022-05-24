package com.example.bucketlist.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.bucketlist.R;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Get categories
        LinearLayout mLinearLayout = view.findViewById(R.id.linearLayout);
        final int childCount = mLinearLayout.getChildCount();
        for (int i = 0; i < childCount; i++ ){
            RelativeLayout rl = (RelativeLayout) mLinearLayout.getChildAt(i);


           TextView tv = (TextView) rl.getChildAt(0);
          rl.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 showToast(tv.getText().toString());
              }
          });


        }

//        Get search bar
        SearchView searchView = view.findViewById(R.id.search_bar);

//        Button mSearchButton = view.findViewById(R.id.button_search);
//        mSearchButton.setOnClickListener(v -> listener.onSearchSelected());
//
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showToast(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

//    private OnItemSelectedListener listener;
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if (context instanceof OnItemSelectedListener) {
//            listener = (OnItemSelectedListener) context;
//        } else {
//            throw new ClassCastException(context.toString() + " must implement OnItemSelectedListener");
//        }
//    }

    private void showToast(String msg) {
        Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}