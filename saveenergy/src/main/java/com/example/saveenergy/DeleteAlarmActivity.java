package com.example.saveenergy;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.data.DatabaseOperator;
import com.example.data.SaveEnergyDataBaseHelper;

/**
 * Created by mc on 16-4-27.
 */
public class DeleteAlarmActivity extends Activity {

    ListView listView;
    MyListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_delete_view);
        ImageButton button = (ImageButton) findViewById(R.id.calcel_delete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listView = (ListView)findViewById(R.id.delete_alarm_list);
        initData();

    }

    private void initData(){
        SaveEnergyDataBaseHelper helper = SaveEnergyDataBaseHelper.getInstance(this);
        SQLiteDatabase dWriter = helper.getWritableDatabase();
        Cursor cursor = dWriter.query(SaveEnergyDataBaseHelper.ALARM_TABLE,null,null,null,null,null,null);
        String[] alarmColums = new String[]{SaveEnergyDataBaseHelper.COL_ALARM_TIME,SaveEnergyDataBaseHelper.COL_SWTICH_NAME};
        int[] layoutId = new int[]{R.id.alarm_delete_time,R.id.alarm_name_delete};
        adapter = new MyListViewAdapter(this,R.layout.alarm_delete_item,cursor,alarmColums,layoutId, CursorAdapter.FLAG_AUTO_REQUERY);
        listView.setAdapter(adapter);

    }


    public class MyListViewAdapter extends SimpleCursorAdapter{
        public MyListViewAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
        }

        @Override
        public void bindView(View view, final Context context, final Cursor cursor) {
            super.bindView(view, context, cursor);
            final int id = cursor.getInt(0);
            ImageButton imageButton = (ImageButton)view.findViewById(R.id.alarm_delete_button);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseOperator operator = new DatabaseOperator(context);
                    operator.delete(id);
                    cursor.requery();
                    adapter.notifyDataSetChanged();

                }
            });

        }


    }
}
