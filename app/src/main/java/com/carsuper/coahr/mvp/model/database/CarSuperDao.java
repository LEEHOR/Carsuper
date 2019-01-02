package com.carsuper.coahr.mvp.model.database;

import android.content.Context;

import com.carsuper.coahr.mvp.view.base.BaseApplication;

/**
 * Author： hengzwd on 2018/8/8.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarSuperDao {

    CarSuperSqliteOpenHelper carSuperSqliteOpenHelper;


    public CarSuperDao(){
        carSuperSqliteOpenHelper =new CarSuperSqliteOpenHelper(BaseApplication.mContext);
    }


    public void  addLovelyCar(){

    }

    public void  removeLovelyCar(){

    }

    public void  selectAllLovelyCar(){

    }

}
