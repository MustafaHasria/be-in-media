package com.example.beinmediatest.ui.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.beinmediatest.R;
import com.example.beinmediatest.databinding.BottomSheetWarningBinding;
import com.example.beinmediatest.ui.main.MainActivity;
import com.example.beinmediatest.ui.main.feed.FeedFragment;
import com.example.beinmediatest.ui.main.feed.model.MovieModel;
import com.example.beinmediatest.util.AppConst;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetWarning extends BottomSheetDialogFragment {

    //region Variables
    BottomSheetWarningBinding binding;
    FeedFragment feedFragment;
    public MovieModel movieModel;
    public int itemPosition;
    //endregion

    //region Life cycle
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_warning,
                container, false);
        binding = BottomSheetWarningBinding.bind(view);

        binding.bottomSheetWarningTextWarning.setText("are you sure to delete " + movieModel.getName()+ " ?");

        binding.bottomSheetWarningSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedFragment.deleteItem(itemPosition);
                ((MainActivity)getActivity()).deletedMovieModelList.add(movieModel);
                dismiss();
            }
        });

        binding.bottomSheetWarningNah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
    //endregion

    //region Getters & Setters

    public void setFeedFragment(FeedFragment feedFragment) {
        this.feedFragment = feedFragment;
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    //endregion
}