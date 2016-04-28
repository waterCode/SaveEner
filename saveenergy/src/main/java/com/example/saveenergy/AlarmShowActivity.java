package com.example.saveenergy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewStub;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.data.DatabaseOperator;
import com.example.data.SaveEnergyDataBaseHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by mc on 16-4-17.
 */
public class AlarmShowActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    ListView listView;
    ArrayList<String> sList=new ArrayList<>();
    ImageButton  iButton;
    SimpleCursorAdapter cursorAdapter;
    SQLiteDatabase wb;
    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_show_view);
        listView=(ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        iButton=(ImageButton)findViewById(R.id.add_button);
        iButton.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        SaveEnergyDataBaseHelper helper=SaveEnergyDataBaseHelper.getInstance(this);
        wb = helper.getWritableDatabase();
        mCursor = wb.query(SaveEnergyDataBaseHelper.ALARM_TABLE,null,null,null,null,null,null);
        String[] colums = new String[]{SaveEnergyDataBaseHelper.COL_ALARM_TIME,SaveEnergyDataBaseHelper.COL_SWTICH_NAME,
                            SaveEnergyDataBaseHelper.COL_SWITCH_STATUS};
        int[] layouId=new int[]{R.id.alarm_show,R.id.alarm_item_whit_swit,R.id.alarm_status};
        cursorAdapter=new SimpleCursorAdapter(this,R.layout.alarm_item,mCursor,colums,layouId, CursorAdapter.FLAG_AUTO_REQUERY);
        listView.setAdapter(cursorAdapter);
    }

    @Override
    protected void onPause() {
        wb.close();
        mCursor.close();
        super.onPause();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_button:
                Intent intent=new Intent(this,AlarmEditActivity.class);
                startActivity(intent);
                Toast.makeText(this,"hasAdd",Toast.LENGTH_SHORT).show();
                cursorAdapter .notifyDataSetChanged();


        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,DeleteAlarmActivity.class);
        startActivity(intent);
        return false;
    }



}
