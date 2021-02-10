package com.example.passstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Base64;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.Nullable;



public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PASSDB";
    private static final int VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creationExec = "CREATE TABLE PassTB"+"(id INTEGER PRIMARY KEY AUTOINCREMENT,website VARCHAR[25]," +
                " username VARCHAR[25],email VARCHAR[25], password VARCHAR[100])";
        db.execSQL(creationExec);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS PassTB");
        onCreate(db);
    }

    public boolean addPassword(String web,String userName, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("website",web);
        cv.put("username",userName);
        cv.put("email",email);
        byte[] temp = password.getBytes();
        Log.d("Encrypyt",temp.toString());
        for(int i=0;i<4;i++){
            temp = Base64.encode(temp,Base64.DEFAULT);
        }
        cv.put("password",new String(temp));
//        Log.d("Encrypyt",temp.toString());
//        byte[] _temp= Base64.decode(temp,Base64.DEFAULT);
//        for(int i=0;i<5;i++){
//            _temp = Base64.decode(_temp,Base64.DEFAULT);
//            Log.d("Decrypt",new String(_temp));
//        }

        db.insert("PassTB",null,cv);
        return true;
    }

    public ArrayList<String> GetWebsites(){
        ArrayList<String> siteNames = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select DISTINCT website from PassTB", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            siteNames.add(res.getString(0));
            res.moveToNext();
        }

        return siteNames;
    }

    public ArrayList<PassClass> GetPasswords(String webSiteName){
        ArrayList<PassClass> passOfSites = new ArrayList<PassClass>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from PassTB where website is  '"+ webSiteName+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            PassClass temp=new PassClass();
            temp.setWebSite(res.getString(1));
            temp.setUserName(res.getString(2));
            temp.setEmail(res.getString(3));
            temp.setPassWord(res.getString(4));
            passOfSites.add(temp);
            res.moveToNext();
        }

        return passOfSites;
    }

}
