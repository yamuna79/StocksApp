package com.example.yamuna.newappstocks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yamuna on 9/30/2016.
 */
public class SQLiteDatabaseManager {

    SQLHelper helper;

    public SQLiteDatabaseManager(Context context){
        helper = new SQLHelper(context);
    }

    public long insertData(String symbol){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLHelper.SYMBOL, symbol);

        long id = db.insert(SQLHelper.TABLE_NAME, null, contentValues);
        db.close();

        return id;

    }

    public String getData(){
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] columns = {SQLHelper.SYMBOL};

        Cursor cursor = db.query(SQLHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        while(cursor.moveToNext()){
            int indexName = cursor.getColumnIndex(SQLHelper.SYMBOL);
            String sym = cursor.getString(indexName);

            buffer.append(sym+"\n");
        }

        return buffer.toString();
    }

    public class SQLHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "usersDatabase";
        private static final int DATABASE_VERSION = 1;

        Context context;
        private static final String TABLE_NAME = "STOCKS";
        private static final String SID = "_id";
        private static final String SYMBOL = "Symbol";

        private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + " ("+ SID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ SYMBOL +" VARCHAR(255));";

        public SQLHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
