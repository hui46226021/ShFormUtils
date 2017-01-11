package com.sh.shformutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sh.shfrom.check.CheckType;
import com.sh.shfrom.annotation.FromCheck;
import com.sh.shfrom.form.FromInit;
import com.sh.shfrom.annotation.FromInjection;
import com.sh.shfrom.form.FromUtls;


public class ResultActivity extends AppCompatActivity {
    @FromInjection(name = "name", message = "名字")
    TextView nameEdit;
    //type 是 验证类型枚举   默认 是自定义验证
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
            /**
             * 实体对象 映射到表单 返回true 表示映射成功
             */
            FromUtls.objectToFrom(this,userModel);
        }

    }

    @Override
    protected void onDestroy() {
        FromInit.deleteInjection(this);
        super.onDestroy();
    }
}
