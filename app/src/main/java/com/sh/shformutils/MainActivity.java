package com.sh.shformutils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sh.shform.check.CheckType;
import com.sh.shform.annotation.FormCheck;
import com.sh.shform.check.FormCheckInterface;
import com.sh.shform.form.FormInit;
import com.sh.shform.annotation.FormInjection;
import com.sh.shform.form.FormUtls;


public class MainActivity extends AppCompatActivity implements FormCheckInterface {
    /**
     * name 对应 实体类 字段名
     * message 参数为空的时候 默认提示 的字符串
     * isNull  该字段是否可以为空  默认 false
     */
    @FormInjection(name = "name", message = "名字",isNull = true)
    EditText nameEdit;
    @FormCheck(type = CheckType.Phone)
    @FormInjection(name = "phone", message = "电话")
    EditText phoneEdit;
    @FormCheck
    @FormInjection(name = "profession", message = "公司-职业")
    EditText professionEdit;
    @FormInjection(name = "workingLife", message = "工作时间")
    Spinner spinner;
    @FormInjection(name = "married")
    CheckBox married;
    @FormInjection(name = "party")
    CheckBox party;

    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEdit = (EditText) findViewById(R.id.name);
        phoneEdit = (EditText) findViewById(R.id.phone);
        professionEdit = (EditText) findViewById(R.id.profession);
        spinner = (Spinner) findViewById(R.id.spinner);
        married = (CheckBox) findViewById(R.id.married);
        party = (CheckBox) findViewById(R.id.party);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
        /**
         * 初始化表单注入  要在 所有控件初始化成功后 调用
         */
        FormInit.injection(this);
    }

    @Override
    protected void onDestroy() {
        FormInit.deleteInjection(this);
        super.onDestroy();
    }


    public void submit(){
        /**
         * 表单自动生成对象
         */
        UserModel userModel = FormUtls.formToObjectAndCheck(this,UserModel.class);
        if(userModel!=null){
            Log.i("userModel",userModel.toString());
            Intent intent = new Intent(this,ResultActivity.class);
            intent.putExtra("userModel",userModel);
            startActivity(intent);
        }

    }

    /**
     * 通过 实现 FormCheckInterface 接口的 formCheck
     * 自定定义 表单检查 默认要返回true
     * @param v
     * @return
     */
    @Override
    public boolean formCheck(View v) {
        switch (v.getId()){
            case R.id.profession:
                if(!(professionEdit.getText()+"").contains("-")){
                    Toast.makeText(this,"职业格式不正确",Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
        }
        return true;
    }

    /**
     * 表单检查 为空回调
     * @param v
     * @param message
     */
    @Override
    public void formCheckParamCall(View v, String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    /**
     * 表单检查 不合法回调
     * @param v
     * @param message
     */
    @Override
    public void formCheckNullCall(View v, String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
