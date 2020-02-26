package com.example.wallapoop2.login;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wallapoop2.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


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

        checkCredentials();

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

    private void checkCredentials()
    {
        SharedPreferences sharedPref = getActivity().getPreferences(getActivity().MODE_PRIVATE);

        final String token = sharedPref.getString("token", "hola");

        //Get the username and password and pass it as params
        HashMap<String, String> loginParams = new HashMap<String, String>();

        loginParams.put("token", token);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, "http://diegosanstr.ddns.net:5000/check", new JSONObject(loginParams),
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                try {
                                    String res = response.getString("message");
                                    if(res.equals("Va bien la cosa"))
                                    {
                                        myNavCtrl.navigate(R.id.action_start_to_splashFragment);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);

    }

}
