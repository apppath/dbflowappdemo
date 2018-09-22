package com.example.basicprogramming.dbflowappdemo.dbconfig;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "bookDatabase";
    public static final int VERSION = 2;

}
