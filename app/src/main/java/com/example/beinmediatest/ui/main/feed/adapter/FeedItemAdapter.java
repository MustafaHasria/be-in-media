package com.example.beinmediatest.ui.main.feed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.beinmediatest.R;
import com.example.beinmediatest.ui.main.feed.model.MovieModel;
import com.example.beinmediatest.util.ItemMoveCallback;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FeedItemAdapter extends RecyclerView.Adapter<FeedItemAdapter.FeedItemViewHolder> implements
        ItemMoveCallback.ItemTouchHelperContract, Filterable {

    //region Variables
    List<MovieModel> movieModelList;
    List<MovieModel> movieModelListFiltered;
    Context context;
    OnFeedItemListeners onFeedItemListeners;
    //endregion

    //region Constructor

    public FeedItemAdapter(OnFeedItemListeners onFeedItemListeners) {
        this.onFeedItemListeners = onFeedItemListeners;
    }

    //endregion

    //region Adapter
    public FeedItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_list_item, parent, false);
        context = parent.getContext();
        return new FeedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FeedItemViewHolder holder, int position) {
        if (!movieModelList.get(position).isFromLocal()) {
            holder.feedListItemTextName.setText(movieModelListFiltered.get(position).getName());
            holder.feedListItemTextDescription.setText(movieModelListFiltered.get(position).getSummary());
            Glide.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_downloading).error(R.drawable.ic_error))
                    .load(movieModelListFiltered.get(position).getImage().getMedium()).thumbnail(0.3f)
                    .into(holder.feedListItemImage);


        } else{
            holder.feedListItemImage.setImageBitmap(movieModelList.get(position).getLocalImage());
            holder.feedListItemTextName.setText(R.string.name_data);
            holder.feedListItemTextDescription.setText(R.string.description_data);

        }

    }

    @Override
    public int getItemCount() {
        return movieModelListFiltered.size();
    }

    public void updateList(List<MovieModel> movieModelList) {
        this.movieModelList = movieModelList;
        this.movieModelListFiltered = new ArrayList<>(movieModelList);
        notifyDataSetChanged();
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onRowSelected(FeedItemViewHolder myViewHolder) {

    }

    @Override
    public void onRowClear(FeedItemViewHolder myViewHolder) {
    }

    public void removeAt(MovieModel movieModel) {
        movieModelList.remove(movieModel);
        movieModelListFiltered.remove(movieModel);
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        movieModelList.remove(position);
        movieModelListFiltered.remove(position);
        notifyDataSetChanged();
    }

    public void add(MovieModel movieModel) {
        this.movieModelListFiltered.add(0, movieModel);
        this.movieModelList.add(0, movieModel);
        notifyDataSetChanged();
    }


    //endregion

    //region Interface
    public interface OnFeedItemListeners {
        void onFeedListItemLinearLayoutMainContainerClickListener(MovieModel movieModel, int position);

        void onFeedListItemImageButtonShareClickListener(MovieModel movieModel, int position);
    }
    //endregion

    //region View holder
    public class FeedItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //region Components
        ConstraintLayout feedListItemLinearLayoutMainContainer;
        TextView feedListItemTextName;
        TextView feedListItemTextDescription;
        ImageView feedListItemImage;
        View itemView;
        ImageButton feedListItemImageButtonShare;
        //endregion

        public FeedItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            feedListItemLinearLayoutMainContainer = itemView.findViewById(R.id.feed_list_item_linear_layout_main_container);
            feedListItemTextName = itemView.findViewById(R.id.feed_list_item_text_name);
            feedListItemTextDescription = itemView.findViewById(R.id.feed_list_item_text_description);
            feedListItemImage = itemView.findViewById(R.id.feed_list_item_image);
            feedListItemImageButtonShare = itemView.findViewById(R.id.feed_list_item_image_button_share);
            this.itemView = itemView;
            feedListItemLinearLayoutMainContainer.setOnClickListener(this);
            feedListItemImageButtonShare.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.feed_list_item_linear_layout_main_container:
                    onFeedItemListeners.onFeedListItemLinearLayoutMainContainerClickListener(movieModelListFiltered.get(getAdapterPosition()), getAdapterPosition());
                    break;
                case R.id.feed_list_item_image_button_share:
                    onFeedItemListeners.onFeedListItemImageButtonShareClickListener(movieModelListFiltered.get(getAdapterPosition()), getAdapterPosition());
            }
        }
    }
    //endregion

    //region Filter

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<MovieModel> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(movieModelList);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (MovieModel item : movieModelList) {
                        if (item.getName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                movieModelListFiltered.clear();
                movieModelListFiltered.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

    //endregion
}
