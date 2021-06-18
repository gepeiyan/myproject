package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class ViewpageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_viewpage);
        ViewPager viewPager=findViewById (R.id.viewpager);
        MyPageAdapter pageAdapter=new MyPageAdapter (getSupportFragmentManager ());
        viewPager.setAdapter (pageAdapter);
        TabLayout tabLayout=findViewById (R.id.sliding_tabs);
        tabLayout.setupWithViewPager (viewPager);
    }
}