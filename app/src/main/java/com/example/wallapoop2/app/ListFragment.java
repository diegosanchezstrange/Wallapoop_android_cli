package com.example.wallapoop2.app;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wallapoop2.MainActivity;
import com.example.wallapoop2.R;
import com.example.wallapoop2.product.Product;
import com.example.wallapoop2.product.ProductDetailFragment;
import com.example.wallapoop2.product.RecyclerProductAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment
{

    private NavController myNavCtrl;
    private BottomNavigationView bottomBar;

    private RecyclerView recyclerView;
    private RecyclerProductAdapter productsAdapter;

    private String productsURL;

    public static List<Product> listaProductos = new ArrayList<Product>();

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_list, container, false);

        controlOnBackPressed();

        productsURL = getString(R.string.server_url) + getString(R.string.server_products);

        HashMap<String, String> loginParams = new HashMap<String, String>();

        listaProductos.clear();

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, productsURL, null,
                        new Response.Listener<JSONArray>()
                        {
                            @Override
                            public void onResponse(JSONArray response)
                            {
                                for(int i = 0; i < response.length(); i++)
                                {
                                    try {
                                        JSONObject prodActual = response.getJSONObject(i);
                                        String nombre = (String)prodActual.get("NAME");
                                        int precio = (int)prodActual.get("PRICE");
                                        int uploaderId = (int)prodActual.get("PRODUCT_OWNER");
                                        ListFragment.listaProductos.add(new Product(nombre, null, null, precio, uploaderId));

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                                productsAdapter.notifyDataSetChanged();

                            }
                        }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Snackbar.make(view, error.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);


        MainActivity.myBottomBar.setVisibility(View.VISIBLE);

        recyclerView = view.findViewById(R.id.recyclerProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

        productsAdapter = new RecyclerProductAdapter( this, listaProductos, this.getContext(), R.layout.product_list);

        recyclerView.setAdapter(productsAdapter);


        myNavCtrl = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        View.OnClickListener onProductClick = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Bundle bundle = new Bundle();
                bundle.putString("Name","Satisfyer pro");
                bundle.putString("Image","satis.jpg");
                bundle.putString("Price","69.00");
                bundle.putString("Description","Solo dos usos, recién lavado");

                ProductDetailFragment fragment = new ProductDetailFragment();
                fragment.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, fragment)
                        .commit();

                //myNavCtrl.navigate(R.id.actionListToProduct);
            }
        };

        productsAdapter.setProductOnClick(onProductClick);

        return view;
    }


    private void controlOnBackPressed()
    {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */)
        {
            @Override
            public void handleOnBackPressed()
            {
                myNavCtrl.navigate(R.id.actionBack);
                MainActivity.myBottomBar.setVisibility(View.INVISIBLE);
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }
}
