package com.example.paging;


import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.paging.PagedList;
import androidx.paging.PositionalDataSource;

import java.util.ArrayList;
import java.util.List;

public class LibrarySource extends PositionalDataSource<LibraryBean> {


    private ArrayList<LibraryBean> mLibraries = new ArrayList<>();



    @Override
    public void loadInitial(@NonNull LoadInitialParams loadInitialParams, @NonNull LoadInitialCallback loadInitialCallback) {
        loadInitialCallback.onResult(getLibrary(0, loadInitialParams.requestedLoadSize), 0, loadInitialParams.requestedLoadSize);

    }

    @Override
    public void loadRange(@NonNull LoadRangeParams loadRangeParams, @NonNull LoadRangeCallback loadRangeCallback) {
        Log.i("test","数据加载..."+loadRangeParams.startPosition+"------"+loadRangeParams.loadSize);
        loadRangeCallback.onResult(getLibrary(loadRangeParams.startPosition,loadRangeParams.loadSize));

    }


    private List<LibraryBean> getLibrary(int startPositional, int count){
        List<LibraryBean> list = new ArrayList<>();
        for(int i = startPositional; i < startPositional+count; i ++){


            LibraryBean bean = new LibraryBean();
            bean.setId("id:" + i);
            bean.setName("name:" + i);
            list.add(bean);
        }

        Log.d("test", "startPositional: " + startPositional);
        return list;

    }

}
