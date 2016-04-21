package com.example.saveenergy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by mc on 16-4-17.
 */
public class AlarmShowActivity extends Activity implements View.OnClickListener {

    ListView listView;
    ArrayList<String> sList=new ArrayList<>();
    ImageButton iButton;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_show_view);
        listView=(ListView)findViewById(R.id.listView);
        iButton=(ImageButton)findViewById(R.id.add_button);
        iButton.setOnClickListener(this);
        sList.add("dasf");
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,sList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_button:sList.add("haha");
                Toast.makeText(this,"hasAdd",Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();


        }
    }
}
