package com.practice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.practice.R;
import com.practice.Response.Item;
import com.practice.Response.VolumeInfo;

import java.util.List;


public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private List<Item> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public BooksAdapter(Context context, List<Item> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.book_list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VolumeInfo volumeInfo = mData.get(position).getVolumeInfo();
        holder.name.setText(volumeInfo.getTitle());
        holder.date.setText(volumeInfo.getPublishedDate());
        holder.author.setText(volumeInfo.getPublishedDate());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    Item getItem(int id) {
        return mData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView author, name, date;

        ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            author = itemView.findViewById(R.id.author);
            name = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
