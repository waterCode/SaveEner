package com.example.saveenergy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.data.MySharedPerferences;

public class EditSwitchNameActivity extends AppCompatActivity implements View.OnClickListener{

    MySharedPerferences dataSettingFile;
    EditText switchName1,switchName2,switchName3,switchName4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSettingFile = new MySharedPerferences(this,MySharedPerferences.DATASETTINGFILE);
        setContentView(R.layout.edit_switch_name_layout);
        switchName1 = (EditText) findViewById(R.id.switch_name1);
        switchName2 = (EditText) findViewById(R.id.switch_name2);
        switchName3 = (EditText) findViewById(R.id.switch_name3);
        switchName4 = (EditText) findViewById(R.id.switch_name4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button save = (Button) findViewById(R.id.one_key_save);
        save.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        switchName1.setText(dataSettingFile.getString(MySharedPerferences.SWITCH_NAME1,"开关1"));
        switchName2.setText(dataSettingFile.getString(MySharedPerferences.SWITCH_NAME2,"开关2"));
        switchName3.setText(dataSettingFile.getString(MySharedPerferences.SWITCH_NAME3,"开关3"));
        switchName4.setText(dataSettingFile.getString(MySharedPerferences.SWITCH_NAME4,"开关4"));
    }



    @Override
    public void onClick(View view) {

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSettingFile.setString(MySharedPerferences.SWITCH_NAME1,switchName1.getText().toString());
        dataSettingFile.setString(MySharedPerferences.SWITCH_NAME2,switchName2.getText().toString());
        dataSettingFile.setString(MySharedPerferences.SWITCH_NAME3,switchName3.getText().toString());
        dataSettingFile.setString(MySharedPerferences.SWITCH_NAME4,switchName4.getText().toString());
    }
}
