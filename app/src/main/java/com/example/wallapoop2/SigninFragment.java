package com.example.wallapoop2;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment
{

    private NavController myNavCtrl;

    public SigninFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signin, container, false);

        myNavCtrl = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        Button signinButton = v.findViewById(R.id.buttonSigning);

        signinButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myNavCtrl.navigate(R.id.actionSigninToSplash);
            }
        });

        return v;
    }

}
