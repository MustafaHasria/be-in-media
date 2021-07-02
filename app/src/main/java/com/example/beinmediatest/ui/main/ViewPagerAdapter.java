package com.example.beinmediatest.ui.main;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.beinmediatest.ui.main.feed.FeedFragment;
import com.example.beinmediatest.ui.main.trash.TrashFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    //region Variables
    Activity activity;
    //endregion

    //region Constructor
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        titleArrayList.add("Feed");
        titleArrayList.add("My Trash");
    }
    //endregion

    //region Set context

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    //endregion

    //region Variables
    private final List<String> titleArrayList = new ArrayList<>();
    //endregion


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FeedFragment();
            case 1:
                return new TrashFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


}
