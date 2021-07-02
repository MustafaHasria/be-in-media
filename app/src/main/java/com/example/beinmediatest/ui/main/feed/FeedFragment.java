package com.example.beinmediatest.ui.main.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beinmediatest.R;
import com.example.beinmediatest.databinding.FragmentFeedBinding;
import com.example.beinmediatest.ui.main.MainActivity;
import com.example.beinmediatest.ui.main.feed.adapter.FeedItemAdapter;
import com.example.beinmediatest.util.AppConst;

import org.jetbrains.annotations.NotNull;


public class FeedFragment extends Fragment {


    //region Variables
    FragmentFeedBinding binding;
    FeedViewModel feedViewModel;
    MainActivity mainActivity;
    FeedItemAdapter feedItemAdapter;

    //endregion
    public FeedFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        mainActivity = (MainActivity) getActivity();
        feedViewModel = new ViewModelProvider(this).get(FeedViewModel.class);
        binding = FragmentFeedBinding.bind(view);
        setupFeedRecycler();
        feedViewModel.getFeeds();

        return view;
    }

    //region Setup
    void setupFeedRecycler() {
        feedItemAdapter = new FeedItemAdapter();
        binding.feedViewRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
        binding.feedViewRecycler.setAdapter(feedItemAdapter);
    }
    //endregion

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        feedViewModel.getListLiveData().observe(getActivity(), moviesModels -> {
            feedItemAdapter.updateList(moviesModels);
        });
        feedViewModel.getStates().observe(getActivity(), s -> {
            if (s.equals(AppConst.NOTHING) || s.equals(AppConst.SUCCESS))
                binding.feedViewProgressBar.setVisibility(View.GONE);
            else if (s.equals(AppConst.LOADING))
                binding.feedViewProgressBar.setVisibility(View.VISIBLE);
            else if (s.equals(AppConst.Fail)) {
                binding.feedViewProgressBar.setVisibility(View.GONE);
                binding.feedViewServerDown.serverDownMainContainer.setVisibility(View.VISIBLE);
            }
        });
    }
}