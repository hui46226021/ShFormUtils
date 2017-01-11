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

import com.sh.shfrom.check.CheckType;
import com.sh.shfrom.annotation.FromCheck;
import com.sh.shfrom.check.FromCheckInterface;
import com.sh.shfrom.form.FromInit;
import com.sh.shfrom.annotation.FromInjection;
import com.sh.shfrom.form.FromUtls;


public class MainActivity extends AppCompatActivity implements FromCheckInterface {
    @FromInjection(name = "name", message = "名字")
    EditText nameEdit;
    @FromCheck(type = CheckType.Phone)
    @FromInjection(name = "phone", message = "电话")
    EditText phoneEdit;
    @FromCheck
    @FromInjection(name = "profession", message = "公司-职业")
    EditText professionEdit;
    @FromInjection(name = "workingLife", message = "工作时间")
    Spinner spinner;
    @FromInjection(name = "married")
    CheckBox married;
    @FromInjection(name = "party")
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
        FromInit.injection(this);
    }

    @Override
    protected void onDestroy() {
        FromInit.deleteInjection(this);
        super.onDestroy();
    }


    public void submit(){
        /**
         * 表单自动生成对象
         */
        UserModel userModel = FromUtls.fromToObjectAndCheck(this,UserModel.class);
        if(userModel!=null){
            Log.i("userModel",userModel.toString());
            Intent intent = new Intent(this,ResultActivity.class);
            intent.putExtra("userModel",userModel);
            startActivity(intent);
        }

    }

    /**
     * 自定定义 表单检查 默认要返回true
     * @param v
     * @return
     */
    @Override
    public boolean fromCheck(View v) {
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
    public void fromCheckParamCall(View v, String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    /**
     * 表单检查 不合法回调
     * @param v
     * @param message
     */
    @Override
    public void fromCheckNullCall(View v, String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
