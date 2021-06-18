package com.example.first;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;

public class MyPageAdapter extends FragmentPagerAdapter {
    public MyPageAdapter(FragmentManager manager){
        super(manager);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new FirstFragment ();
        }else{
            return new SecondFragment ();
        }
    }
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "主页";
        }else{
            return "书单";
        }
    }
    @Override
    public int getCount() {
        return 2;
    }
}