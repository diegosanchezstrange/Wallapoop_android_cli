package com.example.wallapoop2.app;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wallapoop2.MainActivity;
import com.example.wallapoop2.R;
import com.example.wallapoop2.product.Product;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublishProductFragment extends Fragment
{

    private String publishURL;
    private ImageView image;
    private Bitmap imageBitMap;


    public PublishProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_product_publish, container, false);

        publishURL = getString(R.string.server_url) + getString(R.string.server_products);

        final TextInputEditText name, price, desc;

        name = view.findViewById(R.id.inputNamePublish);
        price = view.findViewById(R.id.inputPricePublish);
        desc = view.findViewById(R.id.inputDescPublish);

        image = view.findViewById(R.id.imageViewProductPublish);

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

                HashMap<String, String> params = new HashMap<>();

                SharedPreferences sharedPref = getActivity().getPreferences(getActivity().MODE_PRIVATE);

                String uploader = sharedPref.getString("userID", "0");

                try
                {
                    params.put("nombre", name.getText().toString());
                    params.put("descripcion", desc.getText().toString());
                    params.put("precio", price.getText().toString());
                    params.put("product_owner", uploader);
                    params.put("image", imageToString(imageBitMap));

                }
                catch (Exception ex)
                {
                    Snackbar.make(view, R.string.snackbar_publish_msg, Snackbar.LENGTH_SHORT).show();
                }


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, publishURL, new JSONObject(params),
                                new Response.Listener<JSONObject>()
                                {
                                    @Override
                                    public void onResponse(JSONObject response)
                                    {
                                        try
                                        {
                                            Snackbar.make(view, response.getString("message"), Snackbar.LENGTH_LONG).show();

                                            Fragment fragment = new ListFragment();

                                            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                                            fragmentTransaction.commit();
                                        }
                                        catch (JSONException e)
                                        {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Snackbar.make(view, error.getMessage(), Snackbar.LENGTH_LONG).show();
                            }
                        });

                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(jsonObjectRequest);

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

                    image.setImageURI(selectedImage);
                    try
                    {
                        imageBitMap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), selectedImage);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    }

    private String imageToString(Bitmap image)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
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
