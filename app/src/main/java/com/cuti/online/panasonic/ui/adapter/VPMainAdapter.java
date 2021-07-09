package com.cuti.online.panasonic.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class VPMainAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list_fragment;
    ArrayList<String> list_title;

    public VPMainAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void setList_fragment(ArrayList<Fragment> list_fragment) {
        this.list_fragment = list_fragment;
    }

    public void setList_title(ArrayList<String> list_title) {
        this.list_title = list_title;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position);
    }
}
