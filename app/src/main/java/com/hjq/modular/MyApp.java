package com.hjq.modular;

import android.app.Application;

import io.objectbox.BoxStore;

/**
 * Created by Administrator on 2017/10/18.
 */

public class MyApp extends Application {
    BoxStore boxStore;
    @Override
    public void onCreate() {
        super.onCreate();
          boxStore = MyObjectBox.builder().androidContext(MyApp.this).build();
// do this in your activities/fragments to get hold of a Box
//        notesBox = ((App) getApplication()).getBoxStore().boxFor(Note.class);
    }
}
