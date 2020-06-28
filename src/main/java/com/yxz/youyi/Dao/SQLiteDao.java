package com.yxz.youyi.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yxz.youyi.until.Constants;
import com.yxz.youyi.until.DatabaseHelper;

/**
 * 数据库操作类
 */

public class SQLiteDao {

    private final DatabaseHelper mhelper;

    public SQLiteDao(Context context){

        //创建数据库
        mhelper = new DatabaseHelper(context);

    }

    public void insert(){

        SQLiteDatabase db = mhelper.getWritableDatabase();

        String sql = "insert into" + Constants.TABLE_NAME + "(_id,acdetail,acnum,actime,imageView) values(?,?,?,?,?)";

        ContentValues values = new ContentValues();
        //添加数据
        values.put("_id", 1);

        db.insert(Constants.TABLE_NAME,null,values);
        db.close();

    }

    public void delete(){}

    public void upate(){}

    public void query(){}
}
