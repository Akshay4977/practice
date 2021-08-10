package com.practice.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.practice.fragment.BooksFragment;
import com.practice.fragment.NewsFragment;

public class MyAdapter extends FragmentPagerAdapter {
    private int totalTabs;
    private Context context;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BooksFragment booksFragment = new BooksFragment();
                return booksFragment;
            case 1:
                NewsFragment newsFragment = new NewsFragment();
                return newsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
