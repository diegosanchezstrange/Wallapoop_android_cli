package com.example.wallapoop2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.wallapoop2.app.PublishProductFragment;
import com.example.wallapoop2.app.ProfileFragment;
import com.example.wallapoop2.app.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends FragmentActivity
{

    public static BottomNavigationView myBottomBar;
    public NavController myNavCtrl;

    public static final Integer GALLERY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBottomBar = this.findViewById(R.id.bottom_nav);
        myNavCtrl = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(myBottomBar, myNavCtrl);

        setOnBottomBarClicks(myBottomBar);

    }

    @Override
    protected void onStart()
    {
        super.onStart();

    }



    public void setOnBottomBarClicks (BottomNavigationView bottomBar)
    {
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {

                Fragment fragment = null;

                switch (menuItem.getItemId())
                {
                    case R.id.navigation_home:
                        fragment = new ListFragment();
                        break;

                        /*
                    case R.id.navigation_favs:
                        fragment = new ListFragment();
                        break;
                         */

                    case R.id.navigation_publish:
                        fragment = new PublishProductFragment();
                        break;

                    case R.id.navigation_profile:
                        fragment = new ProfileFragment();
                        break;

                    case R.id.navigation_settings:
                        fragment = new SettingsFragment();
                        break;
                }

                replaceFragment(fragment);

                return true;
            }
        });
    }


    public void replaceFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();
    }

}
