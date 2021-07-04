package com.example.beinmediatest.ui.main.trash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beinmediatest.R;
import com.example.beinmediatest.databinding.FragmentTrashBinding;
import com.example.beinmediatest.ui.main.feed.model.MovieModel;
import com.example.beinmediatest.ui.main.trash.adapter.DeletedFeedItemAdapter;

import java.util.List;


public class TrashFragment extends Fragment {

    //region Variables
    FragmentTrashBinding binding;
    DeletedFeedItemAdapter deletedFeedItemAdapter;
    //endregion

    //region Lifecycle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trash, container, false);
        binding = FragmentTrashBinding.bind(view);
        setupFeedRecycler();

        return view;
    }
    //endregion

    //region Setups
    void setupFeedRecycler() {
        deletedFeedItemAdapter = new DeletedFeedItemAdapter();
        binding.trashRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.trashRecycler.setAdapter(deletedFeedItemAdapter);

    }

    //endregion

    //region Methods
    public void updateData(List<MovieModel> movieModelList){
        if (deletedFeedItemAdapter.getItemCount() == 0)
            binding.feedViewNoData.noDataMainContainer.setVisibility(View.VISIBLE);
        else
            binding.feedViewNoData.noDataMainContainer.setVisibility(View.GONE);
        deletedFeedItemAdapter.updateList(movieModelList);
    }
    //endregion
}