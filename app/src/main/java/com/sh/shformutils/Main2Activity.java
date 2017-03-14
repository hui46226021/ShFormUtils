package com.sh.shformutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sh.zsh.code.check.FormCheckInterface;
import com.sh.zsh.code.form.FormInit;
import com.sh.zsh.code.form.FormUtls;
import com.sh.zsh.code.layout.model.ViewData;
import com.sh.zsh.code.layout.view.FormSpinner;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements FormCheckInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User2Mondl user2Mondl = FormUtls.formToObjectAndCheck(Main2Activity.this,User2Mondl.class);
                if(user2Mondl!=null){
                    Toast.makeText(Main2Activity.this,user2Mondl.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        FormSpinner formSpinner = (FormSpinner) findViewById(R.id.spinner);
        ArrayList<ViewData> list = new ArrayList<>();
        list.add(new ViewData("芝罘",1));
        list.add(new ViewData("莱山",2));
        list.add(new ViewData("开发区",3));
        list.add(new ViewData("牟平区",4));
        list.add(new ViewData("高新区",5));
        list.add(new ViewData("福山区",6));
        formSpinner.setpvOptionsList(list);
        formSpinner.setSelectedListener(new FormSpinner.SelectedListener() {
            @Override
            public void pvOptions(String key, Object value, Object pvOptionsSelectValueObject) {
                Log.e("选中",key+value.toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FormInit.deleteInjection(this);
    }

    @Override
    public boolean formCheck(View v) {
        return true;
    }

    @Override
    public void formCheckParamCall(View v, String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


}
