package com.baidu.aip.asrwakeup3.uiasr.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


public class FragAdapter extends FragmentPagerAdapter {

    private Fragment[] mFragments;


     public FragAdapter(android.support.v4.app.FragmentManager fm,Fragment[] fragments) {
        super(fm);
        // TODO Auto-generated constructor stub
        mFragments=fragments;


    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments[i];
    }

}