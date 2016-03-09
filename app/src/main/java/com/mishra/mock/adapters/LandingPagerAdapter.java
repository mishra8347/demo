package com.mishra.mock.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mishra.mock.fragments.FragmentItemDetails;

/**
 * Created by Deepak Pawar on 1/17/2016.
 */
public class LandingPagerAdapter extends FragmentPagerAdapter
{
    int _tabCount;

    public LandingPagerAdapter(FragmentManager fm, int tabCount)
    {
        super(fm);
        this._tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position)
    {
        Fragment _contentTabFragment = null;
        HomeTabs currentTab = HomeTabs.values()[position];
        switch (currentTab) {
            case ITEM_1:

                _contentTabFragment = new FragmentItemDetails();
                makeDataForFragments(_contentTabFragment, position);
                break;

            case ITEM_2:
                _contentTabFragment = new FragmentItemDetails();
                makeDataForFragments(_contentTabFragment, position);
                break;

            case ITEM_3:
                _contentTabFragment = new FragmentItemDetails();
                makeDataForFragments(_contentTabFragment, position);
                break;
            case ITEM_4:
                _contentTabFragment = new FragmentItemDetails();
                makeDataForFragments(_contentTabFragment, position);
                break;
            case ITEM_5:
                _contentTabFragment = new FragmentItemDetails();
                makeDataForFragments(_contentTabFragment, position);
                break;


        }
        return _contentTabFragment;
    }

    private void makeDataForFragments(Fragment fragment, int i)
    {
        Bundle bundle = new Bundle();
        bundle.putString("data", "Item " + (i+1));
        fragment.setArguments(bundle);
    }

    @Override
    public int getCount()
    {
        return _tabCount;
    }


    private String getTabName(int position)
    {
        for (HomeTabs item : HomeTabs.values()) {
            if (item.getCode() == position) {
                return item.getTabName();
            }
        }
        return "";

    }

    public enum HomeTabs
    {
        ITEM_1("ITEM 1", 1),
        ITEM_2("ITEM 2", 2),
        ITEM_3("ITEM 3", 3),
        ITEM_4("ITEM 4", 4),
        ITEM_5("ITEM 5", 5);

        String _tabName;
        int _tabPosition;

        HomeTabs(String tabName, int code)
        {
            this._tabName = tabName;
            this._tabPosition = code;
        }

        HomeTabs(int code)
        {
            this._tabPosition = code;
        }

        public String getTabName()
        {
            return _tabName;
        }

        public int getCode()
        {
            return _tabPosition;
        }
    }
}
