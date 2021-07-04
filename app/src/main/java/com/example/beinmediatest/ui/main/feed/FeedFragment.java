package com.example.beinmediatest.ui.main.feed;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.beinmediatest.R;
import com.example.beinmediatest.databinding.FragmentFeedBinding;
import com.example.beinmediatest.ui.bottomsheet.BottomSheetWarning;
import com.example.beinmediatest.ui.main.MainActivity;
import com.example.beinmediatest.ui.main.feed.adapter.FeedItemAdapter;
import com.example.beinmediatest.ui.main.feed.model.MovieModel;
import com.example.beinmediatest.util.AppConst;
import com.example.beinmediatest.util.ItemMoveCallback;
import com.example.beinmediatest.util.NotificationUtil;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.SimpleOnSearchActionListener;

import static com.example.beinmediatest.util.AppConst.ITEM_POSITION;
import static com.example.beinmediatest.util.AppConst.NAME;


public class FeedFragment extends Fragment implements FeedItemAdapter.OnFeedItemListeners {


    //region Variables
    FragmentFeedBinding binding;
    FeedViewModel feedViewModel;
    MainActivity mainActivity;
    FeedItemAdapter feedItemAdapter;
    boolean isGrid = true;
    ItemTouchHelper touchHelper;
    MovieModel movieModel;

    //endregion

    //region Life cycle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        binding = FragmentFeedBinding.bind(view);
        mainActivity = (MainActivity) getActivity();
        feedViewModel = new ViewModelProvider(FeedFragment.this).get(FeedViewModel.class);

        setupFeedRecycler();
        setUpRefreshLayout();
        setupSearchBar();

        feedViewModel.getFeeds();

        feedViewModel.getListLiveData().observe(getActivity(), moviesModels -> {
            feedItemAdapter.updateList(moviesModels);
            if (moviesModels.size() == 0)
                binding.feedViewNoData.noDataMainContainer.setVisibility(View.GONE);

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

        binding.feedFloatingActionButtonSwitch.setOnClickListener(v -> {
            isGrid = !isGrid;
            if (isGrid) {
                binding.feedFloatingActionButtonSwitch.setImageResource(R.drawable.ic_baseline_grid_on);
                binding.feedViewRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
            } else {
                binding.feedFloatingActionButtonSwitch.setImageResource(R.drawable.ic_baseline_grid_off);
                binding.feedViewRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
            }

        });

        binding.feedViewNoData.noDataButtonAddImage.setOnClickListener(v -> {
            openCamera();
        });

        return view;
    }

    //endregion

    //region Setups
    void setupFeedRecycler() {
        feedItemAdapter = new FeedItemAdapter(this);
        binding.feedViewRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
        binding.feedViewRecycler.setAdapter(feedItemAdapter);

        // rearrange the positions of the item on click
        ItemTouchHelper.Callback callback =
                new ItemMoveCallback(feedItemAdapter);
        touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.feedViewRecycler);

    }

    void setUpRefreshLayout() {
        binding.feedSwipeRefresh.setColorSchemeResources(R.color.accent);
        binding.feedSwipeRefresh.setOnRefreshListener(this::loadData);
    }


    private void setupSearchBar() {
        binding.feedSearchBar.setOnSearchActionListener(new SimpleOnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                super.onSearchStateChanged(enabled);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                feedItemAdapter.getFilter().filter(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                switch (buttonCode) {
                    case MaterialSearchBar.BUTTON_NAVIGATION:
                        break;
                    // Camera
                    case MaterialSearchBar.BUTTON_SPEECH:
                        openCamera();
                        break;
                }
            }
        });
    }

    //endregion

    //region Methods
    void loadData() {
        hideWarning();
        binding.feedSwipeRefresh.setRefreshing(false);
        feedViewModel.getFeeds();
    }

    void hideWarning() {
        binding.feedViewNoData.noDataMainContainer.setVisibility(View.GONE);
        binding.feedViewServerDown.serverDownMainContainer.setVisibility(View.GONE);
        binding.feedViewNoInternet.noInternetMainContainer.setVisibility(View.GONE);

    }

    public void deleteItem() {
        feedItemAdapter.removeAt(movieModel);
    }

    public void deleteItem(int position) {
        feedItemAdapter.removeAt(position);
    }

    public void openCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        mainActivity.startActivityForResult(cameraIntent, AppConst.FEED_CAMERA_RESULT);
    }

    //endregion

    //region Adapter listeners

    @Override
    public void onFeedListItemLinearLayoutMainContainerClickListener(MovieModel movieModel, int position) {
        BottomSheetWarning bottomSheet = new BottomSheetWarning();
        this.movieModel = movieModel;
        bottomSheet.setMovieModel(movieModel);
        bottomSheet.setFeedFragment(this);
        bottomSheet.setItemPosition(position);
        bottomSheet.show(getChildFragmentManager(), "hi");

    }

    @Override
    public void onFeedListItemImageButtonShareClickListener(MovieModel movieModel, int position) {
        String urlImage;
        if (movieModel.isFromLocal())
            urlImage = "soon you can send url local photo";
        else
            urlImage = movieModel.getImage().getMedium();

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, urlImage);
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(sharingIntent, "share"));
    }
    //endregion

    //region On activity result
    public void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppConst.FEED_CAMERA_RESULT && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            MovieModel movieModel = new MovieModel();
            movieModel.setLocalImage(photo);
            movieModel.setFromLocal(true);
            feedItemAdapter.add(movieModel);
            NotificationUtil.createNotification("Add Item", "Item added successfully", mainActivity);

        }

    }
    //endregion


//    public static void shareScreen(String text, Context context) {
//
//        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//        sharingIntent.setType("text/plain");
//        sharingIntent.putExtra(Intent.EXTRA_TEXT, text);
//        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(Intent.createChooser(sharingIntent, "share"));
//    }
}