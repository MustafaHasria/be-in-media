package com.example.beinmediatest.ui.main.feed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.beinmediatest.R;
import com.example.beinmediatest.ui.main.feed.model.MoviesModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FeedItemAdapter extends RecyclerView.Adapter<FeedItemAdapter.FeedItemViewHolder> {
    //region Variables
    List<MoviesModel> moviesModelList;
    Context context;
    //endregion

    //region Adapter
    public FeedItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_list_item, parent, false);
        context = parent.getContext();
        return new FeedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FeedItemViewHolder holder, int position) {
        holder.feedListItemTextName.setText(moviesModelList.get(position).getName());
        holder.feedListItemTextDescription.setText(moviesModelList.get(position).getSummary());
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_downloading).error(R.drawable.ic_error))
                .load(moviesModelList.get(position).getImage().getMedium()).thumbnail(0.3f)
                .into(holder.feedListItemImage);

    }

    @Override
    public int getItemCount() {
        return moviesModelList.size();
    }

    public void updateList(List<MoviesModel> moviesModelList){
        this.moviesModelList = moviesModelList;
        notifyDataSetChanged();
    }
    //endregion

    //region Interface
    private interface onFeedItemListeners{

    }
    //endregion

    //region View holder
    public class FeedItemViewHolder extends RecyclerView.ViewHolder{

        //region Components
        LinearLayout feedListItemLinearLayoutMainContainer;
        TextView feedListItemTextName;
        TextView feedListItemTextDescription;
        ImageView feedListItemImage;
        //endregion

        public FeedItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            feedListItemLinearLayoutMainContainer = itemView.findViewById(R.id.feed_list_item_linear_layout_main_container);
            feedListItemTextName = itemView.findViewById(R.id.feed_list_item_text_name);
            feedListItemTextDescription = itemView.findViewById(R.id.feed_list_item_text_description);
            feedListItemImage = itemView.findViewById(R.id.feed_list_item_image);
        }
    }
    //endregion
}
