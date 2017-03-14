package com.sh.zsh.code.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sh.shform.R;
import com.sh.zsh.code.form.FormInit;
import com.sh.zsh.code.layout.model.AtrrContainer;


/**
 * Created by zhush on 2017/3/13
 * E-mail 405086805@qq.com
 * PS  表单布局
 */

public class ShFormLayout extends LinearLayout{

    Context mContext;
    LinearLayout formLinear;
    float rowHeight;
    public ShFormLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        try {
            LayoutInflater.from(context).inflate(R.layout.less_form_layout, this);
        }catch (Exception e){
            e.printStackTrace();
        }
        formLinear = (LinearLayout) findViewById(R.id.form_linear);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.less_from_view, 0, 0);
        rowHeight =ta.getDimension(R.styleable.less_from_view_less_form_row_height,(int) getContext().getResources().getDimension(R.dimen.form_row_height));
    }
    AtrrContainer atrrContainer;
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        LayoutParams lp=   super.generateLayoutParams(attrs);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.less_from_view, 0, 0);
         atrrContainer = new AtrrContainer();

        try {
            atrrContainer.setLessFormTitle(ta.getString(R.styleable.less_from_view_less_form_title));
            atrrContainer.setLessFormName(ta.getString(R.styleable.less_from_view_less_form_name));
            atrrContainer.setLessFormBottomLine(ta.getBoolean(R.styleable.less_from_view_less_form_bottomLine,true));
            atrrContainer.setLessFormTitleImage(ta.getInt(R.styleable.less_from_view_less_form_title_image,0));
            atrrContainer.setLessFormCanClick(ta.getBoolean(R.styleable.less_from_view_less_form_can_click,false));
            atrrContainer.setLessFormMust(ta.getBoolean(R.styleable.less_from_view_less_form_must,false));
            atrrContainer.setLessFormTitle(ta.getString(R.styleable.less_from_view_less_form_title));
            atrrContainer.setLessFormCheckType(ta.getInt(R.styleable.less_from_view_less_form_check_type,0));
            atrrContainer.setLessFormIsNull(ta.getBoolean(R.styleable.less_from_view_less_form_is_null,false));
            atrrContainer.setLessFormGroupTitel(ta.getBoolean(R.styleable.less_from_view_less_form_group_titel,false));
            atrrContainer.setLessFormGroupTopLayout(ta.getBoolean(R.styleable.less_from_view_less_form_group_top_layout,false));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lp;
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if(atrrContainer.getLessFormGroupTopLayout()){
            formLinear.addView(child,params);
            return;
        }

        if(child instanceof FrameLayout||child instanceof LinearLayout||child instanceof ScrollView){
            super.addView(child, params);
        }else {
            //如果是form组标题
            if(atrrContainer.getLessFormGroupTitel()){
                if(child instanceof TextView){
                    child.setBackgroundResource(R.color.form_ui_background);

                    int groupTitelHeight = (int) getContext().getResources().getDimension(R.dimen.form_group_titel_height);
                    RelativeLayout.LayoutParams rp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,groupTitelHeight);
                    child.setLayoutParams(rp1);
                    ((TextView)child).setGravity(Gravity.CENTER_VERTICAL);
                    ((TextView)child).setTextColor(ContextCompat.getColor(getContext(), R.color.form_group_text_color));
                    ((TextView) child).setTextSize(TypedValue.COMPLEX_UNIT_PX,getContext().getResources().getDimension(R.dimen.form_group_text_size));
                    child.setPadding((int) getContext().getResources().getDimension(R.dimen.form_group_padding),0,0,0);
                    formLinear.addView(child);
                }else {
                    throw  new RuntimeException("标题组必须是 TextView");
                }

                return;
            }


            FormRowView formRow = new FormRowView(getContext());

            int height = 0 ;
            if(params.height>rowHeight){
                height=params.height;
            }else {
                height= (int)rowHeight;
            }

            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, height);

            formRow.setLayoutParams(lp1);
            formRow.setAtrr(atrrContainer);//要在 setContentView 前调用
            formRow.setContentView(child);
            formLinear.addView(formRow);
        }
    }
}
