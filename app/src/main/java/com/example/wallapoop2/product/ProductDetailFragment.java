package com.example.wallapoop2.product;


import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wallapoop2.MainActivity;
import com.example.wallapoop2.R;
import com.example.wallapoop2.app.ListFragment;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {


    public ProductDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        controlOnBackPressed();

        Product actual = ListFragment.listaProductos.get(ListFragment.lastItemClicked);

        TextView name, description, price;
        ImageView image;

        name = view.findViewById(R.id.textDetailName);
        description = view.findViewById(R.id.textDetailDescription);
        price = view.findViewById(R.id.textDetailPrice);
        image = view.findViewById(R.id.imageViewProductDetail);

        name.setText(actual.getpName());
        description.setText(actual.getpDescription());
        price.setText(String.valueOf(actual.getpPrice()) + " €");

        Picasso.get().load("http://diegosanstr.ddns.net:5001/img/" + actual.getpName().replace(" ", "_") + ".jpg").resize(120,120).centerCrop().into(image);

        //Context ctx = view.getContext();
        //int id = ctx.getResources().getIdentifier(bundle.getString("Image"), "drawable", ctx.getPackageName());
        //image.setImageResource(id);

        return view;
    }


    private void controlOnBackPressed()
    {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */)
        {
            @Override
            public void handleOnBackPressed()
            {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_productDetailFragment_to_listFragment);
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }
}
