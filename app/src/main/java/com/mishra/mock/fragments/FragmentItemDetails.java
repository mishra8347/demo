package com.mishra.mock.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mishra.mock.R;
import com.mishra.mock.adapters.ViewPagerAdapter;
import com.mishra.mock.appbase.BaseFragment;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Shailendra on 08-03-2016.
 */
public class FragmentItemDetails extends BaseFragment implements ViewPagerAdapter.IViewPagerClickListener
{


    @Override
    public int getFragmentLayoutId()
    {
        return R.layout.fragment_layout_common_home;
    }

    @Override
    public int getContentContainerViewGroupIds()
    {
        return R.id.contentContainer;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initView(getArguments().getString("data", null) + "");

    }

    private void initView(String data)
    {
        ViewPager viewPager = (ViewPager) getView().findViewById(R.id.viewPager);
        CircleIndicator circleIndicator = (CircleIndicator) getView().findViewById(R.id.circleIndicator);
        viewPager.setAdapter(new ViewPagerAdapter(getContext(), this));
        circleIndicator.setViewPager(viewPager);
        getTextViewById(R.id.txtView1).setText(data);
        getTextViewById(R.id.txtView2).setText(data);
        getTextViewById(R.id.txtView3).setText(data);
        getTextViewById(R.id.txtItemName).setText(data);
        getButtonByID(R.id.green).setOnClickListener(this);
        getButtonByID(R.id.red).setOnClickListener(this);
        getButtonByID(R.id.blue).setOnClickListener(this);


    }

    private Button getButtonByID(int resourceId)
    {
        return (Button) getView().findViewById(resourceId);
    }

    private TextView getTextViewById(int resourceId)
    {
        return (TextView) getView().findViewById(resourceId);
    }

    private LinearLayout setContainerBG()
    {
        return (LinearLayout) getView().findViewById(R.id.containerButton);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.red:
                setContainerBG().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                break;
            case R.id.green:
                setContainerBG().setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                break;
            case R.id.blue:
                setContainerBG().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

    @Override
    public void onViewPagerClickListener(int position)
    {
        Bundle bundle = new Bundle();
        bundle.putString("position", position + "");
        FragmentForViewPager fragmentForViewPager = new FragmentForViewPager();
        fragmentForViewPager.setArguments(bundle);
        navigateTo(fragmentForViewPager);
    }

    protected void navigateTo(Fragment fragment)
    {
        replaceContentFragment(getChildFragmentManager(), fragment, true, R.id.subFragmentViewContainer, 0, 0, 0, 0, true);
    }

    @Override
    public void replaceContentFragment(FragmentManager fragmentManager, Fragment fragment, boolean toAdd, int containerId, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim, boolean addToBackStack)
    {
        super.replaceContentFragment(fragmentManager, fragment, toAdd, containerId, enterAnim, exitAnim, popEnterAnim, popExitAnim, addToBackStack);
//        fragmentManager.executePendingTransactions();
    }

}
