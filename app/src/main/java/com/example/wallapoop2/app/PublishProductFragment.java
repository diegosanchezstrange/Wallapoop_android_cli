package com.example.wallapoop2.app;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wallapoop2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublishProductFragment extends Fragment {


    public PublishProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publish_product, container, false);
    }

}
