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
public class LoginFragment extends Fragment
{

    private NavController myNavCtrl;
    private String loginURL;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        loginURL = getString(R.string.server_url) + getString(R.string.server_login);

        myNavCtrl = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        Button loginButton = view.findViewById(R.id.buttonLogging);

        final EditText name = view.findViewById(R.id.userLoginName);
        final EditText passwd = view.findViewById(R.id.userLoginPasswd);

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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
                                SharedPreferences sharedPref = getActivity().getPreferences(getActivity().MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                try {
                                    editor.putString("token", response.getString("token"));
                                    editor.putString("userID", response.getString("user_id"));
                                    editor.commit();

                                    Snackbar.make(view, response.toString(), Snackbar.LENGTH_SHORT).show();

                                    myNavCtrl.navigate(R.id.actionLoginToSplash);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

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
            }
        });

        return view;
    }

}
