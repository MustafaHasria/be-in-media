package com.example.beinmediatest.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.beinmediatest.R;
import com.example.beinmediatest.databinding.ActivityMainBinding;
import com.example.beinmediatest.ui.main.feed.FeedFragment;
import com.example.beinmediatest.ui.main.feed.model.MovieModel;
import com.example.beinmediatest.ui.main.trash.TrashFragment;
import com.example.beinmediatest.util.AppConst;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //region Variables
    ActivityMainBinding binding;
    ViewPagerAdapter viewPagerAdapter;
    public List<MovieModel> deletedMovieModelList;
    //endregion

    //region Life cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        deletedMovieModelList = new ArrayList<>();
        viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        binding.mainViewPager.setAdapter(viewPagerAdapter);
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager);
        binding.mainViewPager.setOffscreenPageLimit(2);
        binding.mainTabLayout.setTabMode(TabLayout.MODE_FIXED);
        binding.mainTabLayout.getTabAt(0).setCustomView(getCustomTab(0));
        binding.mainTabLayout.getTabAt(1).setCustomView(getCustomTab(1));
        binding.mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1){
                    Fragment fragment = viewPagerAdapter.getItem(position);
                    if (fragment instanceof TrashFragment)
                        ((TrashFragment) Objects.requireNonNull(binding.mainViewPager.getAdapter()).instantiateItem(binding.mainViewPager, position)).updateData(deletedMovieModelList);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    //endregion

    //region On activity result

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConst.FEED_CAMERA_RESULT) {
            int position = binding.mainViewPager.getCurrentItem();
            Fragment fragment = viewPagerAdapter.getItem(position);
            if (fragment instanceof FeedFragment)
                ((FeedFragment) Objects.requireNonNull(binding.mainViewPager.getAdapter()).instantiateItem(binding.mainViewPager, position)).handleOnActivityResult(requestCode, resultCode, data);
        }

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