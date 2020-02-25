package com.example.wallapoop2.app;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wallapoop2.MainActivity;
import com.example.wallapoop2.R;

import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment
{

    private ImageView image;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        image = view.findViewById(R.id.imageViewProfile);

        image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pickFromGallery();
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

}
