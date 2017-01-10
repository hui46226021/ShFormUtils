package com.sh.shformutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.sh.shformutils.form.CheckType;
import com.sh.shformutils.form.FromCheck;
import com.sh.shformutils.form.FromInit;
import com.sh.shformutils.form.FromInjection;
import com.sh.shformutils.form.FromUtls;

public class ResultActivity extends AppCompatActivity {
    @FromInjection(name = "name", message = "名字")
    TextView nameEdit;
    @FromCheck(type = CheckType.Phone)
    @FromInjection(name = "phone", message = "电话")
    TextView phoneEdit;
    @FromCheck
    @FromInjection(name = "profession", message = "公司-职业")
    TextView professionEdit;
    @FromInjection(name = "workingLife", message = "工作时间")
    TextView spinner;
    @FromInjection(name = "married")
    CheckBox married;
    @FromInjection(name = "party")
    CheckBox party;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        nameEdit = (TextView) findViewById(R.id.name);
        phoneEdit = (TextView) findViewById(R.id.phone);
        professionEdit = (TextView) findViewById(R.id.profession);
        spinner = (TextView) findViewById(R.id.spinner);
        married = (CheckBox) findViewById(R.id.married);
        party = (CheckBox) findViewById(R.id.party);

        /**
         * 初始化表单注入  要在 所有控件初始化成功后 调用
         */
        FromInit.injection(this);

        UserModel userModel = (UserModel) getIntent().getSerializableExtra("userModel");
        if(userModel!=null){
            FromUtls.objectToFrom(this,userModel);
        }

    }

    @Override
    protected void onDestroy() {
        FromInit.deleteInjection(this);
        super.onDestroy();
    }
}
