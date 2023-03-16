package com.course.implement.newsapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.course.implement.newsapp.R;
import com.course.implement.newsapp.ViewPagerAdapter;
import com.course.implement.newsapp.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private ViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        pagerAdapter = new ViewPagerAdapter(this);
        setupViewpager();
    }

    private void setupViewpager(){
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("AllNews"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tech"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tesla"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Business"));

        binding.viewpagerFragment.setAdapter(pagerAdapter);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewpagerFragment.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        binding.viewpagerFragment.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });
    }

}