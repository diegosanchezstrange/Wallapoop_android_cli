package com.example.wallapoop2.app;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wallapoop2.MainActivity;
import com.example.wallapoop2.R;
import com.example.wallapoop2.product.Product;
import com.google.android.material.textfield.TextInputEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublishProductFragment extends Fragment
{

    private ImageView image;


    public PublishProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publish_product, container, false);

        final TextInputEditText name, price, desc;

        name = view.findViewById(R.id.inputNamePublish);
        price = view.findViewById(R.id.inputPricePublish);
        desc = view.findViewById(R.id.inputDescPublish);

        image = view.findViewById(R.id.imageViewProfile);

        image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pickFromGallery();
            }
        });

        Button publishButton = view.findViewById(R.id.buttonPublish);

        publishButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                publishProduct(name, price, desc, image);

                // TODO
                // Recoger el producto y mandarlo al servidor

            }
        });

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


    public Product publishProduct(TextInputEditText name, TextInputEditText price, TextInputEditText desc, ImageView img)
    {
        String productName, productDescription, productImg;
        Float productPrice;

        productName = name.getText().toString();
        productPrice = Float.parseFloat(price.getText().toString());
        productDescription = desc.getText().toString();

        return new Product(productName, productDescription, 1, productPrice, 1);
    }

}
