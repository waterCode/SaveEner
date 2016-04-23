package com.example.saveenergy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.Model.Alarm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by mc on 16-4-23.
 */
public class AlarmEditActivity extends Activity implements AdapterView.OnItemClickListener{

    ListView listView;
    List<Map<String,String>> datalist;
    Map<String,String> map;
    SimpleAdapter simpleAdapter;

    Alarm alarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_edit);
        listView=(ListView)findViewById(R.id.alarm_edit_list_view);
        alarm=new Alarm();
        datalist=new ArrayList<Map<String, String>>();
        setDataList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        simpleAdapter= new SimpleAdapter(this,datalist,R.layout.alarm_edit_item,new String[]{"name","value"},new int[]{R.id.switch_name,R.id.switch_value});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
    }

    public  void setDataList(){
        datalist.clear();
        map = new HashMap<String, String>();
        map.put("name","目标开关");
        map.put("value","开关"+alarm.getWhitchSwitch());
        datalist.add(map);
        map = new HashMap<String, String>();
        map.put("name","开关需求");
        map.put("value",alarm.getSwitchStatus());
        datalist.add(map);

    }
    private void selectSwitchDialog(){
        String[] items=new String[]{"开关1","开关2","开关3","开关4"};
        AlertDialog.Builder dialog= new AlertDialog.Builder(this)
                .setTitle("开关选择")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alarm.setWhitchSwitch(i);
                        setDataList();
                        simpleAdapter.notifyDataSetChanged();
                    }
                })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        dialog.create().show();
    }

    private  void selectstatusDialog(){
        String[] items=new String[]{"on","off"};
        AlertDialog.Builder builder =new AlertDialog.Builder(this)
                .setTitle("定时开断")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:alarm.setSwitchStatus("on");break;
                            case 1:alarm.setSwitchStatus("off");break;
                        }
                        setDataList();
                        simpleAdapter.notifyDataSetChanged();
                    }
                })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        switch (i){
            case 0:selectSwitchDialog();
                   break;
            case 1:selectstatusDialog();

        }
    }


}
