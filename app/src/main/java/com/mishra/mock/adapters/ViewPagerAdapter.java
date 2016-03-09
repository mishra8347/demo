package com.mishra.mock.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mishra.mock.R;

import java.lang.ref.WeakReference;

/**
 * Created by  Shailendra on 09-03-2016.
 */
public class ViewPagerAdapter extends PagerAdapter implements View.OnClickListener
{

    private WeakReference<Context> current;
    IViewPagerClickListener iViewPagerClickListener;

    @Override
    public void onClick(View v)
    {
       int position= (int) v.getTag();
        iViewPagerClickListener.onViewPagerClickListener(position);
    }

    public interface IViewPagerClickListener
    {
        void onViewPagerClickListener(int position);
    }

    public ViewPagerAdapter(Context context, IViewPagerClickListener iViewPagerClickListener)
    {
        current = new WeakReference<Context>(context);
        this.iViewPagerClickListener = iViewPagerClickListener;
    }

    @Override
    public int getCount()
    {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {

        LayoutInflater inflater = (LayoutInflater) current.get()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewLayout = inflater.inflate(R.layout.viewpager_row, container,
                false);
        viewLayout.setOnClickListener(this);
        viewLayout.setTag(position);
        ((ViewPager) container).addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        ((ViewPager) container).removeView((LinearLayout) object);

    }

}
