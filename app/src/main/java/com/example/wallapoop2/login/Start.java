package com.example.wallapoop2.login;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wallapoop2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Start extends Fragment
{

    private NavController myNavCtrl;

    public Start()
    {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_start, container, false);

        myNavCtrl = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        Button logButton = v.findViewById(R.id.buttonLog);
        Button signButton = v.findViewById(R.id.buttonSign);

        logButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myNavCtrl.navigate(R.id.actionLoginFragment);
            }
        });

        signButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myNavCtrl.navigate(R.id.actionSigninFragment);
            }
        });

        return v;
    }

}
