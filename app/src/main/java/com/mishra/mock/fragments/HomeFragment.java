package com.mishra.mock.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mishra.mock.R;
import com.mishra.mock.adapters.LandingPagerAdapter;
import com.mishra.mock.utilities.DeviceUtility;

/**
 * Created by Shailendra on 08-03-2016.
 */
public class HomeFragment extends Fragment implements TabLayout.OnTabSelectedListener
{
    private ViewPager _homeViewPager;
    private TabLayout _homeTablayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    private void setupViewPager(final TabLayout tabLayout, View view)
    {
        LandingPagerAdapter landingPagerAdapter = new LandingPagerAdapter(getChildFragmentManager(), _homeTablayout.getTabCount());
        _homeViewPager = (ViewPager) getView().findViewById(R.id.homeViewPager);
        _homeViewPager.setAdapter(landingPagerAdapter);
        /***It will take title from getPageTitle **/
        _homeViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(_homeTablayout));
    }

    private void initViews()
    {
        _homeTablayout = (TabLayout) getView().findViewById(R.id.homeTablayout);

        _homeTablayout.addTab(_homeTablayout.newTab().setCustomView(getCustomizeTabTextView(getResources().getString(R.string.item1))));
        _homeTablayout.addTab(_homeTablayout.newTab().setCustomView(getCustomizeTabTextView(getResources().getString(R.string.item2))));
        _homeTablayout.addTab(_homeTablayout.newTab().setCustomView(getCustomizeTabTextView(getResources().getString(R.string.item3))));
        _homeTablayout.addTab(_homeTablayout.newTab().setCustomView(getCustomizeTabTextView(getResources().getString(R.string.item4))));
        _homeTablayout.addTab(_homeTablayout.newTab().setCustomView(getCustomizeTabTextView(getResources().getString(R.string.item5))));
        _homeTablayout.setOnTabSelectedListener(this);
        _homeTablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        _homeTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        setupViewPager(_homeTablayout, getView());
    }

    public TextView getCustomizeTabTextView(String tabTitle)
    {
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tabtextview, null, false);
        textView.setWidth((int) (DeviceUtility.getDeviceHeightWidth(getContext())[0]/3.4));
        textView.setText(tabTitle);

        return textView;
    }


    /*Tab overrided methods*/
    @Override
    public void onTabSelected(TabLayout.Tab tab)
    {
        _homeViewPager.setCurrentItem(tab.getPosition());
        LandingPagerAdapter.HomeTabs currentTab = LandingPagerAdapter.HomeTabs.values()[tab.getPosition()];
        switch (currentTab) {
            case ITEM_1:

                break;

            case ITEM_2:

                break;
            case ITEM_3:

                break;
            case ITEM_4:

                break;
            case ITEM_5:

                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab)
    {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab)
    {

    }


}
