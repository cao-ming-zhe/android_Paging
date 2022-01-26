package com.example.paging;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Adapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mLibraryRecyclerView = null;
    private LibraryAdapter mLibraryAdapter = null;
    private LibraryViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLibraryAdapter = new LibraryAdapter();

        mLibraryRecyclerView = (RecyclerView) findViewById(R.id.libraryRecycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        //设置布局管理器
        mLibraryRecyclerView.setLayoutManager(layoutManager);
        //设置适配器
        mLibraryRecyclerView.setAdapter(mLibraryAdapter);

        mViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory())
                .get(LibraryViewModel.class);

        mViewModel.getListLiveData().observe(this,new Observer<PagedList<LibraryBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<LibraryBean> dataBeans) {
                mLibraryAdapter.submitList(dataBeans);
            }
        });

        mLibraryAdapter.setLiveData(mViewModel);

    }
}