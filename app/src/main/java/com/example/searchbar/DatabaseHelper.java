package com.example.searchbar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Assigning name to Database,Table and columns of table.
    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="student_details";
    public static final String COL_1="ID";
    public static final String COL_2="Name";
    public static final String COL_3="Surname";
    public static final String COL_4="Marks";

    public DatabaseHelper(@Nullable Context context ){
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    //Insertion function.
    public boolean insert(String name,String surname,String marks)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        long result=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result== -1)
            return false;
        else
            return true;
    }
    //Function to retrieve data.
    public Cursor getData()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery(" select * from "+TABLE_NAME,null);
        return res;
    }
    //Function to update the data.
    public boolean updateData(String id,String name,String sur,String marks)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,sur);
        contentValues.put(COL_4,marks);
        sqLiteDatabase.update(TABLE_NAME,contentValues,"ID=?",new String[]{ id });
        return true;
    }
    //Function to delete data
    public int deleteData(String id)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"ID = ?",new String[] { id });
    }
}
