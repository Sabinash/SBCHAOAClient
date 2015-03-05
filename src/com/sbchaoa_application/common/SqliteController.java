package com.sbchaoa_application.common;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteController extends SQLiteOpenHelper {
	private static final String LOGCAT = null;

	public SqliteController(Context applicationcontext) {
		super(applicationcontext, "SBCHAOA.DB", null, 1);
		Log.d(LOGCAT, "Created");
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		 
		String query = "CREATE TABLE Records ( ConnectionId INTEGER PRIMARY KEY, TYPE TEXT,NAME TEXT,FLATNBR TEXT)";
		database.execSQL(query);
		String flag = "CREATE TABLE SetFlag ( ConnectionId INTEGER PRIMARY KEY, FLAG TEXT)";
		database.execSQL(flag);
		Log.d(LOGCAT, "Records Created");
		Log.d(LOGCAT, "SetFlag Created");

	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old,
			int current_version) {
		String query = "DROP TABLE IF EXISTS Records";
		database.execSQL(query);
		String flag = "DROP TABLE IF EXISTS SetFlag";
		database.execSQL(flag);
		onCreate(database);
	}

	public void insertRecords(String type, String name, String flatnbr) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("TYPE", type);
		values.put("NAME", name);
		values.put("FLATNBR", flatnbr);
		database.insert("Records", null, values);
		database.close();
	}
	
	public void insertFlag(String flag) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("FLAG", flag);
		
		database.close();
	}
	
	public String getType() {
		// TODO Auto-generated method stub
		
		
		// Select All Query
        String selectQuery = "SELECT  * FROM Records";
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String type = null;
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	type = cursor.getString(1);
            } while (cursor.moveToNext());
        }
 
        // closing connection
        cursor.close();
        db.close();
		
		
		return type;
	}
	public String getName() {
		// TODO Auto-generated method stub
		
		
		// Select All Query
        String selectQuery = "SELECT  * FROM Records";
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String name = null;
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	name = cursor.getString(2);
            } while (cursor.moveToNext());
        }
 
        // closing connection
        cursor.close();
        db.close();
		
		
		return name;
	}
	public String getFlatNbr() {
		// TODO Auto-generated method stub
		
		
		// Select All Query
        String selectQuery = "SELECT  * FROM Records";
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String flatnbr = null;
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	flatnbr = cursor.getString(3);
            } while (cursor.moveToNext());
        }
 
        // closing connection
        cursor.close();
        db.close();
		
		
		return flatnbr;
	}

	public ArrayList<HashMap<String, String>> getAllRecords() {
		ArrayList<HashMap<String, String>> mList;
		mList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM Records";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();

				map.put("TYPE", cursor.getString(1));
				map.put("NAME", cursor.getString(2));
				map.put("FLATNBR", cursor.getString(3));
				
				mList.add(map);
			} while (cursor.moveToNext());
		}

		return mList;
	}
	public String getFlag() {
		// TODO Auto-generated method stub
		
		
		// Select All Query
        String selectQuery = "SELECT  * FROM SetFlag";
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String flag ="";
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	flag = cursor.getString(1);
            } while (cursor.moveToNext());
        }
 
        // closing connection
        cursor.close();
        db.close();
		
		
		return flag;
	}
	

}
