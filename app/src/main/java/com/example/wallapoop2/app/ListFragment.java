package com.example.wallapoop2.app;


import android.os.Bundle;

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

import com.example.wallapoop2.MainActivity;
import com.example.wallapoop2.R;
import com.example.wallapoop2.product.Product;
import com.example.wallapoop2.product.RecyclerProductAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment
{

    private NavController myNavCtrl;

    private RecyclerView recyclerView;
    private RecyclerProductAdapter productsAdapter;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        List<Product> productList = new ArrayList<Product>()
        {{
            add(new Product("Perro", null, null, 130, 1));
            add(new Product("Iphone 8", null, null, 350, 1));
            add(new Product("PS4", null, "@drawable/psone", 120, 1));
            add(new Product("Porro", null, null, 1.2f, 1));
            add(new Product("Usb pen 16GB", null, null, 4.5f, 1));
            add(new Product("Opel Corsa 1998 TDI", null, null, 2200, 1));
            add(new Product("Play Station 3", null, "@drawable/psone", 179, 1));
            add(new Product("Bragas usadas", null, null, 12, 1));
            add(new Product("Adadas Originales", null, null, 70, 1));

            add(new Product("Perro", null, null, 130, 1));
            add(new Product("Iphone 8", null, null, 350, 1));
            add(new Product("PS4", null, "@drawable/psone", 120, 1));
            add(new Product("Porro", null, null, 1.2f, 1));
            add(new Product("Usb pen 16GB", null, null, 4.5f, 1));
            add(new Product("Opel Corsa 1998 TDI", null, null, 2200, 1));
            add(new Product("Play Station 3", null, "@drawable/psone", 179, 1));
            add(new Product("Bragas usadas", null, null, 12, 1));
            add(new Product("Adadas Originales", null, null, 70, 1));
        }} ;



        MainActivity.myBottomBar.setVisibility(View.VISIBLE);

        recyclerView = view.findViewById(R.id.recyclerProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

        productsAdapter = new RecyclerProductAdapter( this, productList, this.getContext(), R.layout.product_list);

        recyclerView.setAdapter(productsAdapter);


        myNavCtrl = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        View.OnClickListener onProductClick = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myNavCtrl.navigate(R.id.actionListToProduct);
            }
        };

        productsAdapter.setProductOnClick(onProductClick);

        return view;
    }






}
