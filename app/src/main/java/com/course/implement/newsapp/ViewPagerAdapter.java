package com.course.implement.newsapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.course.implement.newsapp.views.fragments.AllNewsFragment;
import com.course.implement.newsapp.views.fragments.TechCrunchFragment;
import com.course.implement.newsapp.views.fragments.BusinessFragment;
import com.course.implement.newsapp.views.fragments.TeslaFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private Fragment fragment;
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 0:
                fragment = new AllNewsFragment();
                return fragment;
            case 1:
                fragment = new TechCrunchFragment();
                return fragment;
            case 2:
                fragment= new TeslaFragment();
                return fragment;
            default:
                fragment = new BusinessFragment();
                return fragment;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
