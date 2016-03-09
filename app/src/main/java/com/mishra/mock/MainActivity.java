package com.mishra.mock;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mishra.mock.appbase.BaseActivity;
import com.mishra.mock.fragments.FragmentDisignPartTwo;
import com.mishra.mock.fragments.HomeFragment;
import com.mishra.mock.utilities.GetDataFromServer;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private String name;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            navigateTo(new HomeFragment());
        } else {
            String name = savedInstanceState.getString("fragment");
            if (name.equalsIgnoreCase("FragmentDisignPartTwo")) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("ArrayList", savedInstanceState.getParcelableArrayList("ArrayList"));
                FragmentDisignPartTwo fragment = new FragmentDisignPartTwo();
                fragment.setArguments(bundle);
                navigateTo(fragment);
            } else {
                navigateTo(new HomeFragment());
            }
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putString("fragment", name);
        outState.putParcelableArrayList("ArrayList", GetDataFromServer.getDataList());
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.design_part_1) {
            navigateTo(new HomeFragment());
        } else if (id == R.id.design_part_2) {
            navigateTo(new FragmentDisignPartTwo());
        }

        closeDrawer();
        return true;
    }

    protected void navigateTo(Fragment fragment)
    {
        name = fragment.getClass().getSimpleName();
        this.fragment = fragment;
        replaceContentFragment(getSupportFragmentManager(), fragment, false, R.id.fragmentFrameContainer, 0, 0, 0, 0, false);
    }

    @Override
    public void replaceContentFragment(FragmentManager fragmentManager, Fragment fragment, boolean toAdd, int containerId, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim, boolean addToBackStack)
    {
        super.replaceContentFragment(fragmentManager, fragment, toAdd, containerId, enterAnim, exitAnim, popEnterAnim, popExitAnim, addToBackStack);
        fragmentManager.executePendingTransactions();
        closeDrawer();
    }

    private void closeDrawer()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


}
