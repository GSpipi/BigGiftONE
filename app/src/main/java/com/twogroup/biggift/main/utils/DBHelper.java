package com.twogroup.biggift.main.utils;

import android.os.Environment;

import com.twogroup.biggift.main.entity.UserInfo;

import org.xutils.DbManager;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.db.table.DbModel;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

/**
 * Created by LKP on 2015/12/18.
 */
public class DBHelper    {

static DbManager.DaoConfig config= new DbManager.DaoConfig()
        .setDbName("biggift")
        .setDbDir(Environment.getExternalStorageDirectory())
        .setDbVersion(1)
        .setAllowTransaction(true)
        .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
            @Override
            public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                //数据库更新
            }
        });
private static DbManager db;
    public  static DbManager getDbManager(){
        db= x.getDb(config);
        return db;
    }
    public void insert(UserInfo userInfo){
        try {

            db.save(userInfo);
            db.saveOrUpdate(userInfo);

        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public  void query (UserInfo userInfo){
        try {
            List<UserInfo> all=db.selector(UserInfo.class).findAll();
//            List<UserInfo> list =db.findAll(UserInfo.class);
            db.selector(UserInfo.class).orderBy("name").findAll();
            db.selector(UserInfo.class).getLimit();//限制条数
            db.selector(UserInfo.class).getOffset();//偏移量
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void Mode(){
        try{
            final List<DbModel> dbModelAll=db.findDbModelAll(new SqlInfo(""));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
