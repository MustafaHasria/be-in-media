package com.example.beinmediatest.ui.main.trash.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.beinmediatest.R;
import com.example.beinmediatest.ui.main.feed.model.MovieModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DeletedFeedItemAdapter extends RecyclerView.Adapter<DeletedFeedItemAdapter.DeletedFeedItemViewHolder> {

    //region Variables
    List<MovieModel> movieModelList;
    Context context;
    //endregion

    //region Constructor

    public DeletedFeedItemAdapter() {
        movieModelList = new ArrayList<>();
    }

    //endregion

    //region Adapter
    public DeletedFeedItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deleted_feed_list_item, parent, false);
        context = parent.getContext();
        return new DeletedFeedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DeletedFeedItemViewHolder holder, int position) {
        if (!movieModelList.get(position).isFromLocal()) {
            holder.deletedFeedListItemTextName.setText(movieModelList.get(position).getName());
            holder.deletedFeedListItemTextDescription.setText(movieModelList.get(position).getSummary());
            Glide.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_downloading).error(R.drawable.ic_error))
                    .load(movieModelList.get(position).getImage().getMedium()).thumbnail(0.3f)
                    .into(holder.deletedFeedListItemImage);


        } else {
            holder.deletedFeedListItemImage.setImageBitmap(movieModelList.get(position).getLocalImage());
            holder.deletedFeedListItemTextName.setText(R.string.name_data);
            holder.deletedFeedListItemTextDescription.setText(R.string.description_data);
        }

    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }

    public void updateList(List<MovieModel> movieModelList) {
        this.movieModelList = movieModelList;
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        movieModelList.remove(position);
        notifyDataSetChanged();
    }

    //endregion

    //region View holder
    public class DeletedFeedItemViewHolder extends RecyclerView.ViewHolder {

        //region Components
        TextView deletedFeedListItemTextName;
        TextView deletedFeedListItemTextDescription;
        TextView deletedFeedListItemTextDate;
        ImageView deletedFeedListItemImage;
        //endregion

        public DeletedFeedItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            deletedFeedListItemTextName = itemView.findViewById(R.id.deleted_feed_list_item_text_name);
            deletedFeedListItemTextDescription = itemView.findViewById(R.id.deleted_feed_list_item_text_description);
            deletedFeedListItemTextDate = itemView.findViewById(R.id.deleted_feed_list_item_text_date);
            deletedFeedListItemImage = itemView.findViewById(R.id.deleted_feed_list_item_image);
        }
    }
    //endregion

}
