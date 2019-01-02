package com.carsuper.coahr.mvp.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author： hengzwd on 2018/8/8.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarSuperSqliteOpenHelper extends SQLiteOpenHelper {

    public static final String CREATE_LOVELYCAR_TABLE = "create table lovelycar(name cd_id(200) primary key,displacement varchar(200),carSerialName varchar(200),defaultcar INT)";
    private Context mContext;

    public CarSuperSqliteOpenHelper(Context context) {
        super(context, "carsuper.db", null, 1);
        this.mContext= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOVELYCAR_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
