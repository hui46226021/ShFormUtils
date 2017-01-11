package com.sh.shformutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sh.shform.check.CheckType;
import com.sh.shform.annotation.FormCheck;
import com.sh.shform.form.FormInit;
import com.sh.shform.annotation.FormInjection;
import com.sh.shform.form.FormUtls;


public class ResultActivity extends AppCompatActivity {
    @FormInjection(name = "name", message = "名字")
    TextView nameEdit;
    //type 是 验证类型枚举   默认 是自定义验证
    @FormCheck(type = CheckType.Phone)
    @FormInjection(name = "phone", message = "电话")
    TextView phoneEdit;
    @FormCheck
    @FormInjection(name = "profession", message = "公司-职业")
    TextView professionEdit;
    @FormInjection(name = "workingLife", message = "工作时间")
    TextView spinner;
    @FormInjection(name = "married")
    CheckBox married;
    @FormInjection(name = "party")
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
        FormInit.injection(this);

        UserModel userModel = (UserModel) getIntent().getSerializableExtra("userModel");
        if(userModel!=null){
            /**
             * 实体对象 映射到表单 返回true 表示映射成功
             */
            FormUtls.objectToForm(this,userModel);
        }

    }

    @Override
    protected void onDestroy() {
        FormInit.deleteInjection(this);
        super.onDestroy();
    }
}
