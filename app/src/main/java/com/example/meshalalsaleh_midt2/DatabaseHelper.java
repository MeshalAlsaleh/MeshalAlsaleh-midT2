package com.example.meshalalsaleh_midt2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "NEW.db";
    public static final String TABLE_NAME = "users_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAMES";
    public static final String COL3 = "EMAILS";
    public static final String COL4 = "NUMBER";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY, " +
                " NAMES TEXT, EMAILS TEXT , NUMBER INTEGER)";

        db.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addData(int id,String name, String email, int num) {
        long result;

        Cursor cursor=getSpecifiedResultByID(id);

        if(cursor.getCount()==1)
            return false;
        else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL1, id);
            contentValues.put(COL2, name);
            contentValues.put(COL3, email);
            contentValues.put(COL4, num);

            result = db.insert(TABLE_NAME, null, contentValues);
        }

        if(result == -1 ) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor structuredQuery(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME, new String[]{COL1, COL2, COL3, COL4}, COL1 + "=?",

                new String[]{String.valueOf(ID)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    public Cursor getSpecificResult(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME +" Where NAME=? ",new String[]{name});
        return data;
    }

    public Cursor getSpecifiedResultByID(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME +" Where ID=?  ",new String[]{String.valueOf(ID)});
        return data;

    }






    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public int dltRow(String name){

        SQLiteDatabase db = this.getWritableDatabase();
        int delete=0;

        long result= DatabaseUtils.queryNumEntries(db,TABLE_NAME,"NAME=?",new String[]{name});

        if(result>=1)
            delete=db.delete(TABLE_NAME,"NAME=?",new String[]{name});

        return delete;

    }

    public void update(int id,int email){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3,email);

        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{String.valueOf(id)});

    }



}
