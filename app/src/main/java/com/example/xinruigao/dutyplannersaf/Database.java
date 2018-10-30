package com.example.xinruigao.dutyplannersaf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "soliders names";

    private static final String TABLE_NAME = "soldiers";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FEEDS_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_FEEDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        //create table again
        onCreate(db);
    }

    public void addName(SoldierNames soldierNames){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, soldierNames.getName());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<SoldierNames> getNames(){
        List<SoldierNames> results = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            do {
                SoldierNames soldierNames = new SoldierNames(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
                results.add(soldierNames);
            } while (cursor.moveToNext());
        }
        db.close();
        return results;
    }

    public void deleteNames(SoldierNames soldierNames){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " =? ", new String[]{String.valueOf(soldierNames.getId())});
        //opens us TABLE_FEEDS, look up the id and delete it if it exists
        db.close();
    }

}
