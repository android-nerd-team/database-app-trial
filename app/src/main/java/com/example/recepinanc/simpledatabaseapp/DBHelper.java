package com.example.recepinanc.simpledatabaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Recepinanc on 18.09.2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "firstDB";
    private static final String TABLE_COUNTRIES = "ulkeler";
    private static final String COUNTRY_ID = "id";
    private static final String COUNTRY_NAME = "country_name";
    private static final String COUNTRY_CODE = "country_code";

    //super methodu bir alttaki constructor'ý iþaret etmektedir
    public DBHelper(Context context) {
        // context is context , name is DATABASE_NAME, no factory, version is 1
        super(context, DATABASE_NAME, null, 1);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Creating the table for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_COUNTRIES + " (" + COUNTRY_ID + " INTEGER PRIMARY KEY, " +
                COUNTRY_NAME + " TEXT, " + COUNTRY_CODE + " TEXT);";
        Log.d("DBHelper", "SQL : " + sql);
        db.execSQL(sql);
    }

    //Upgrading the table in  the other versions of our program
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_COUNTRIES);
        onCreate(db);
    }

    public void insertCountry(Country country) {
        //Hangi database olduðunu belirt
        SQLiteDatabase db = this.getWritableDatabase();

        //Verileri tutmasý için container oluþtur.
        ContentValues values = new ContentValues();

        //verileri ekle
        values.put("country_name", country.getCountryName());
        values.put("country_code", country.getCountryCode());

        //Yeni satýr oluþtur
        db.insert(TABLE_COUNTRIES, null, values);
        //Commit
        db.close();
    }

    public boolean deleteCountry(String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_COUNTRIES,COUNTRY_CODE + "=" + code,null) > 0;
    }

    public List<Country> getAllCountries() {
        //Veritabanýndan gelen sonuçlarý saklayacaðýmýz list
        List<Country> countries = new ArrayList<Country>();
        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor methodu basit bir select oluþturmak için idealdir
        //Cursor objesi bize sonuçlar içinde dolaþma olanaðý saðlar
        Cursor cursor = db.query(TABLE_COUNTRIES, new String[]{"id", "country_name", "country_code"}
                , null, null, null, null, null);

        while (cursor.moveToNext()) {
            Country country = new Country();
            country.setId(cursor.getInt(0));
            country.setCountryName(cursor.getString(1));
            country.setCountryCode(cursor.getString(2));
            countries.add(country);
        }

        return countries;
    }

}


















