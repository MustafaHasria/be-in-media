package com.example.beinmediatest.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.beinmediatest.R;
import com.example.beinmediatest.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //region Variables
    ActivityMainBinding binding;
    MainPresenter mainPresenter;
    ViewPagerAdapter viewPagerAdapter;
    //endregion


    //region On create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mainPresenter = new MainPresenter(this);
        viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        binding.mainViewPager.setAdapter(viewPagerAdapter);
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager);
        binding.mainViewPager.setOffscreenPageLimit(2);
        binding.mainTabLayout.setTabMode(TabLayout.MODE_FIXED);
        binding.mainTabLayout.getTabAt(0).setCustomView(getCustomTab(0));
        binding.mainTabLayout.getTabAt(1).setCustomView(getCustomTab(1));
    }
    //endregion

    //region Methods
    public View getCustomTab(int position) {
        @SuppressLint("InflateParams")
        LinearLayout mCustomTab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_group_tab_layout, null);
        TextView tabTextView = mCustomTab.findViewById(R.id.tab_group_text_view);
        switch (position) {
            case 0:
                tabTextView.setText(R.string.feed);
                break;
            case 1:
                tabTextView.setText(getString(R.string.my_trash));
                break;
        }
        return mCustomTab;
    }
    //endregion
}