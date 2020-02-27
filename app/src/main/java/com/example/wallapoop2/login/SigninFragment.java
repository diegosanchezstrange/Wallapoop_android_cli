package com.example.wallapoop2.login;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
public class SigninFragment extends Fragment
{

    private NavController myNavCtrl;
    private String registerURL;

    public SigninFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_signin, container, false);

        registerURL = getString(R.string.server_url) + ":5000/register";

        myNavCtrl = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        Button signinButton = v.findViewById(R.id.buttonSigning);

        final EditText name = v.findViewById(R.id.userSigninName);
        final EditText passwd = v.findViewById(R.id.userSigninPasswd);
        final EditText passwdRepeat = v.findViewById(R.id.userSigningPasswdRepeat);

        signinButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                String passwdStr = passwd.getText().toString();
                String passwdRepeatStr = passwdRepeat.getText().toString();
                if(passwdStr.equals(passwdRepeatStr))
                {
                    HashMap<String, String> loginParams = new HashMap<String, String>();

                    loginParams.put("name", name.getText().toString());
                    loginParams.put("passwd", passwd.getText().toString());


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.POST, registerURL, new JSONObject(loginParams),
                                    new Response.Listener<JSONObject>()
                                    {
                                        @Override
                                        public void onResponse(JSONObject response)
                                        {
                                            Snackbar.make(v, "Usuario registrado.", Snackbar.LENGTH_SHORT).show();

                                            myNavCtrl.navigate(R.id.actionSigninToSplash);

                                        }
                                    }, new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error)
                                {

                                }
                            });

                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    requestQueue.add(jsonObjectRequest);
                }else
                {
                    Snackbar.make(v, "Las claves no coinciden.", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }

}
