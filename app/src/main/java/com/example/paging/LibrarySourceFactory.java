package com.example.paging;


import androidx.paging.DataSource;

public class LibrarySourceFactory extends DataSource.Factory<Integer, LibraryBean> {

    @Override
    public DataSource<Integer, LibraryBean> create() {
        return new LibrarySource();
    }
}
