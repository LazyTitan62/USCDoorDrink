package com.csci310.uscdoordrink;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.csci310.uscdoordrink.FragmentDaily;
import com.csci310.uscdoordrink.FragmentMonthly;
import com.csci310.uscdoordrink.FragmentWeekly;
import com.csci310.uscdoordrink.R;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private String userName;
    private String date;
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_daily, R.string.tab_weekly,R.string.tab_monthly};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm, String userName, String date) {
        super(fm);
        mContext = context;
        this.userName = userName;
        this.date = date;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;
        switch(position){
            case 0:
                fragment = new FragmentDaily(userName, date);
                break;
            case 1:
                fragment = new FragmentWeekly(userName, date);
                break;
            case 2:
                fragment = new FragmentMonthly(userName, date);
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}