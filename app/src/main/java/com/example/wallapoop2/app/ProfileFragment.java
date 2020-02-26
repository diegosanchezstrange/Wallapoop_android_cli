package com.example.wallapoop2.app;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wallapoop2.MainActivity;
import com.example.wallapoop2.R;
import com.example.wallapoop2.product.Product;
import com.example.wallapoop2.product.RecyclerProductAdapter;
import com.example.wallapoop2.product.RecyclerUserProductAdapter;
import com.example.wallapoop2.product.onClickInterface;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment
{

    private NavController myNavCtrl;

    public static List<Product> listaProductos = new ArrayList<Product>();

    public static int lastItemClicked;

    private TextView name;
    private ImageView image;

    private RecyclerView recyclerView;
    private RecyclerUserProductAdapter productsAdapter;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        listaProductos.clear();

        myNavCtrl = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        image = view.findViewById(R.id.imageViewProfile);

        image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pickFromGallery();
            }
        });

        name = view.findViewById(R.id.tvName);

        SharedPreferences sharedPref = getActivity().getPreferences(getActivity().MODE_PRIVATE);

        name.setText(sharedPref.getString("userID", "0"));

        setupRecyclerView(view);

        return view;
    }


    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == MainActivity.RESULT_OK)
        {
            switch (requestCode)
            {
                case 1:
                    Uri selectedImage = data.getData();

                    // TODO
                    // Almacenar la foto en la base de datos y obtenerla desde ahí para que sea persistente(?)

                    image.setImageURI(selectedImage);

                    break;
            }
        }
    }


    private void pickFromGallery()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");

        String[] mimeTypes = {"image/jpeg", "image/png"};

        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

        startActivityForResult(intent, MainActivity.GALLERY_REQUEST_CODE);
    }


    private void setupRecyclerView(View v)
    {

        recyclerView = v.findViewById(R.id.recyclerUserProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));


        onClickInterface onClickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc) {
                ProfileFragment.lastItemClicked = abc;
                myNavCtrl.navigate(R.id.actionListToProduct);
            }
        };

        for(Product p : ListFragment.listaProductos)
        {
           if (p.getUploaderID() == uploader)
           {
               listaProductos.add(p);
           }
        }


        productsAdapter = new RecyclerUserProductAdapter( this, listaProductos, this.getContext(), R.layout.product_list, onClickInterface);

        recyclerView.setAdapter(productsAdapter);
    }
}
