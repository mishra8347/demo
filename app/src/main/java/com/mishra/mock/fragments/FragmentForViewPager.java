package com.mishra.mock.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mishra.mock.R;
import com.mishra.mock.utilities.CustomToast;

/**
 * Created by Shailendra on 09-03-2016.
 */
public class FragmentForViewPager extends Fragment

{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragmen_viewpager, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout contentContainerViewPager = (LinearLayout) getView().findViewById(R.id.contentContainerViewPager);
        contentContainerViewPager.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CustomToast.showToast(getContext(), getArguments().getString("position") + "", Toast.LENGTH_LONG);
            }
        });

    }
}
