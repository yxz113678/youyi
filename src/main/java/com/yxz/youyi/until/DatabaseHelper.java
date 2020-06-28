package com.yxz.youyi.until;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    /**
     * @ context 上下文
     * @ name    数据库名称
     * @ factory 游标工厂
     * @ version 数据库版本号
     */
    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION_CODE);
    }

    /**
     * 第一次创建才会被调用
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建时的回调
        Log.d(TAG, "创建数据库......");
        //创建字段
        //sql create table table_name(_id integer,acdetail varchar,acnum integer,actime datetime,imageView varchar)
        String sql = "create table "+Constants.TABLE_NAME+"(_id integer,acdetail varchar,acnum integer,actime datetime,imageView varchar)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级数据库时的回调
        Log.d(TAG, "升级数据库......");

        /**
         * 版本
         */
        switch (oldVersion){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}
