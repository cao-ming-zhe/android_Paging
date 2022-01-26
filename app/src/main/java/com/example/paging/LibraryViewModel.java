package com.example.paging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.ArrayList;

public class LibraryViewModel extends ViewModel {

    private final LiveData<PagedList<LibraryBean>> mListLiveData;

    public LibraryViewModel(){

                PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)                         //配置分页加载的数量
                .setEnablePlaceholders(false)     //配置是否启动PlaceHolders
                .setInitialLoadSizeHint(20)              //初始化加载的数量
                .build();

        mListLiveData = new LivePagedListBuilder(new LibrarySourceFactory(), config)
//                .setBoundaryCallback(null)
//                .setFetchExecutor(null)
                .build();
    }

    public LiveData<PagedList<LibraryBean>> getListLiveData() {
        return mListLiveData;
    }

}
