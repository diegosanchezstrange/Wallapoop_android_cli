package com.example.wallapoop2.login;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wallapoop2.R;

import org.json.JSONObject;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment
{

    private NavController myNavCtrl;
    private String loginURL = "http://192.168.0.16:5000/login";

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        myNavCtrl = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        Button loginButton = v.findViewById(R.id.buttonLogging);

        final EditText name = v.findViewById(R.id.userLoginName);
        final EditText passwd = v.findViewById(R.id.userLoginPasswd);

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //myNavCtrl.navigate(R.id.actionLoginToSplash);
                //Get the username and password and pass it as params
                HashMap<String, String> loginParams = new HashMap<String, String>();

                loginParams.put("name", name.getText().toString());
                loginParams.put("passwd", passwd.getText().toString());


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, loginURL, new JSONObject(loginParams),
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
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
        });

        return v;
    }

}