package com.example.wallapoop2.product;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wallapoop2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {


    public ProductDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        Bundle bundle = this.getArguments();

        TextView name, description, price;
        ImageView image;

        name = view.findViewById(R.id.tvName);
        description = view.findViewById(R.id.tvName);
        price = view.findViewById(R.id.tvName);
        image = view.findViewById(R.id.imageViewProductDetail);

        name.setText(bundle.getString("Name"));
        description.setText(bundle.getString("Description"));
        price.setText((int) bundle.getFloat("Price"));
        //image.setBackground();

        return view;
    }

}
