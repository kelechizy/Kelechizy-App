package com.kelechizy.kelechizyapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kelechizy.kelechizyapp.tab.FragmentAbout;
import com.kelechizy.kelechizyapp.tab.FragmentContact;
import com.kelechizy.kelechizyapp.tab.FragmentHome;
import com.kelechizy.kelechizyapp.tab.FragmentPortfolio;

/**
 * Created by Kelechi on 3/3/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentHome tab1 = new FragmentHome();
                return tab1;
            case 1:
                FragmentAbout tab2 = new FragmentAbout();
                return tab2;
            case 2:
                FragmentPortfolio tab3 = new FragmentPortfolio();
                return tab3;
            case 3:
                FragmentContact tab4 = new FragmentContact();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}