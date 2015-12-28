package com.twogroup.biggift.main.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LKP on 2015/12/20.
 */

/**
 * SQLiteOpenHelper使用步骤
 * 1.自定义类继承SQLiteOpenHelper
 * 2.添加有参的构造方法
 * 3.重写onCreate方法，在该方法中创建表
 * 4.重写onUpgrade的方法，在该方法中执行更新操作
 * 5.实例化自定义类对象
 * 6.通过自定义类的实例通过：
 *      getWritableDatabase：获取可写的SQLiteDataBase对象
 *      getReaderableDatabase：获取只读的SQLiteDataBase对象
 * 7.通过SQLiteDataBase对象对数据库进行操作
 *
 *
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    /**
     * MySQLiteOpenHelper  有参的构造方法
     * @param context  上下文
     * @param name  我们需要打开获取创建数据库
     * @param factory  游标工厂 默认为null就够了
     * @param version  数据库版本号，从1开始递增。
     */
    public MySQLiteOpenHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }
    /**
     * 创建表的
     * 当数据库第一次被创建的时候被执行
     */
    public void onCreate(SQLiteDatabase db) {
String sql="CREATE TABLE IF NOT EXISTS users (id integer PRIMARY KEY AUTOINCREMENT , username varchar2(11),passwords varchar(12))";

        db.execSQL(sql);
    }

    /**
     * 当数据库版本更新后执行该方法
     * 在该方法中可以执行更新操作（添加表，修改字段，添加索引，增加视图....）
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
