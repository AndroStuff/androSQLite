package io.github.sanjeet291196.sqlitesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by sanjit on 15/8/16.
 * Project: SQLite Sample
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database";
    private static final int version = 1;
    private static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS Sample(Data VARCHAR);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //
    }

    public boolean insertData(String Data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Data", Data);
        db.insert("Sample", null, contentValues);
        return true;
    }

    public ArrayList<String> getAllData() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Sample", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            array_list.add(res.getString(res.getColumnIndex("Data")));
            res.moveToNext();
        }

        res.close();
        return array_list;
    }
}
