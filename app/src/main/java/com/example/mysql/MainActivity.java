package com.example.mysql;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DBHelper dbHelper;
    EditText et1, et2;
    ArrayList <String> info=new ArrayList<>();
    ListView list;
    Button save, read, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save=findViewById(R.id.saveBut);
        save.setOnClickListener(this);
        read=findViewById(R.id.readBut);
        read.setOnClickListener(this);
        clear=findViewById(R.id.clearBut);
        clear.setOnClickListener(this);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        list=findViewById(R.id.list);
        dbHelper=new DBHelper(this, DBHelper.DATABASE_NAME, 1);

    }


    @Override
    public void onClick(View view) {
        SQLiteDatabase sqldb=dbHelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, info);
        list.setAdapter(adapter);
switch(view.getId()){
    case R.id.saveBut:
cv.put(dbHelper.KEY_NAME,et1.getText().toString());
cv.put(dbHelper.KEY_EMAIL, et2.getText().toString());
sqldb.insert(dbHelper.TABLE_CONTACTS,null, cv);
et1.setText(""); et2.setText("");
        break;
    case R.id.readBut:
        Cursor cursor=sqldb.query(dbHelper.TABLE_CONTACTS, null, null,
                null,null,null, null);
        int idIndex=cursor.getColumnIndex(dbHelper.KEY_ID);
        int nameIndex=cursor.getColumnIndex(dbHelper.KEY_NAME);
        int mailIndex=cursor.getColumnIndex(dbHelper.KEY_EMAIL);
        if(cursor.moveToFirst()){
            do{
        info.add(cursor.getString(idIndex)+" "+cursor.getString(nameIndex)+ " "+ cursor.getString(mailIndex));
        adapter.notifyDataSetChanged();

                }
            while (cursor.moveToNext());
        }else
            cursor.close();
        break;
    case R.id.clearBut:
       sqldb.delete(DBHelper.TABLE_CONTACTS,null,null);
        break;
}
dbHelper.close();
    }
}