package com.example.paging;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibraryAdapter extends PagedListAdapter<LibraryBean, LibraryAdapter.LibraryViewHolder> {

    final static String TAG = "LibraryAdapter";

    private static DiffUtil.ItemCallback<LibraryBean> mDiffCallback = new DiffUtil.ItemCallback<LibraryBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull LibraryBean oldItem,@NonNull LibraryBean newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull LibraryBean oldItem,@NonNull LibraryBean newItem) {
            return oldItem.equals(newItem);
        }
    };

    public LibraryAdapter() {
        super(mDiffCallback);
    }

    @NonNull
    @Override
    public LibraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //为每个Item inflater出一个View
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.library_list_item,parent,false);
        LibraryViewHolder viewHolder = new LibraryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryViewHolder holder, int position) {
        //染数据到View

        LibraryBean bean = getItem(position);
        holder.nameTextView.setText(bean.getName());
        holder.swipeRevealLayout.close(false);

        holder.editMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bean.setName("test");
                holder.nameTextView.setText(bean.getName());
                holder.swipeRevealLayout.close(false);
                Log.d(TAG, "onClick: editMaterialCardView");


            }
        });

        holder.deleteMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCurrentList().getDataSource().invalidate();
                Log.d(TAG, "onClick: deleteMaterialCardView");
            }
        });

        holder.editMaterialCardView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    holder.swipeRevealLayout.close(false);
                }
            }
        });

        holder.deleteMaterialCardView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    holder.swipeRevealLayout.close(false);
                }
            }
        });

        holder.contentCardView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    holder.swipeRevealLayout.close(false);
                }
            }
        });

        holder.swipeRevealLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                holder.swipeRevealLayout.close(false);
            }
        });

    }


    public class LibraryViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView;
        public MaterialCardView editMaterialCardView,deleteMaterialCardView,contentCardView;
        public SwipeRevealLayout swipeRevealLayout;
        public LibraryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.library_list_name);
            editMaterialCardView = itemView.findViewById(R.id.library_list_adapter_edit_card_view);
            deleteMaterialCardView = itemView.findViewById(R.id.library_list_delete_card_view);
            contentCardView = itemView.findViewById(R.id.library_list_content_card_view);
            swipeRevealLayout = itemView.findViewById(R.id.library_list_swipe);
        }
    }
}
