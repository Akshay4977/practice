package com.practice;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.practice.adapter.MyAdapter;
import com.practice.fragment.BooksFragment;
import com.practice.fragment.NewsFragment;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpager);
        addTabs(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void addTabs(ViewPager viewPager) {
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        adapter.addFrag(new NewsFragment(), "NEWS");
        adapter.addFrag(new BooksFragment(), "BOOKS");
        viewPager.setAdapter(adapter);
    }
}
