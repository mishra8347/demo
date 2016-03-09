package com.mishra.mock.appbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mishra.mock.R;

public abstract class BaseFragment extends Fragment implements View.OnClickListener
{
    private static final String TAG = BaseFragment.class.getName();

    public abstract int getFragmentLayoutId();

    public abstract int getContentContainerViewGroupIds();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.base_fragment_view_container, container, false);
        initViews(view, inflater);
        return view;
    }

    private void initViews(View view, LayoutInflater inflater)
    {
        ViewGroup subFragmentViewContainer = (ViewGroup) view.findViewById(R.id.subFragmentViewContainer);
        View subFragmentView = inflater.inflate(getFragmentLayoutId(), subFragmentViewContainer, false);
        subFragmentViewContainer.removeAllViews();
        subFragmentViewContainer.addView(subFragmentView);
//        View fragmentContentView = getView().findViewById(getContentContainerViewGroupIds());
//        fragmentContentView.setVisibility(View.VISIBLE);
    }

    public void showProgressBar()
    {
        ProgressBar pbBaseFragmentProgress = (ProgressBar) getView().findViewById(R.id.pbBaseFragmentProgress);
        pbBaseFragmentProgress.setVisibility(View.VISIBLE);
    }

    protected LinearLayoutManager getLinearLayoutManager()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }
    public void replaceContentFragment(FragmentManager fragmentManager, Fragment fragment, boolean toAdd, int containerId, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim, boolean addToBackStack)
    {
        // Return if fragment to be added in UiBaseActivity is null
        if (fragment == null) {
            throw new IllegalArgumentException("Null fragment passed in " + TAG + "#replaceContentFragment");
        }
        // getting the name of the fragment class
        String mFragmentName = ((Object) fragment).getClass().getSimpleName();
        // replace with new content
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Animation for above ICS
        fragmentTransaction.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim);
        if (toAdd) {
            fragmentTransaction.add(containerId, fragment, mFragmentName);
        } else {
            fragmentTransaction.replace(containerId, fragment, mFragmentName);
        }
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(mFragmentName);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }


}