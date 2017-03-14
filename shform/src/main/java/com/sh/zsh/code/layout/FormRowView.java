package com.sh.zsh.code.layout;

import android.content.Context;
import android.os.ParcelUuid;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sh.shform.R;
import com.sh.zsh.code.check.CheckType;
import com.sh.zsh.code.check.ViewAttribute;
import com.sh.zsh.code.form.FormInit;
import com.sh.zsh.code.layout.model.AtrrContainer;
import com.sh.zsh.code.layout.view.FormTimeView;

import org.w3c.dom.Text;

/**
 * Created by zhush on 2017/3/14
 * E-mail 405086805@qq.com
 * PS  表单里得一行控件
 */

public class FormRowView extends LinearLayout {
    RelativeLayout frameLayout;//装有控件得 父布局
    TextView lineMust; //必须点击
    TextView lineTital;//标题
    ImageView rightArrow;//右箭头
    View bottomLine;//底部横线
    View contentView;

    public FormRowView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.less_form_row, this);
        frameLayout = (RelativeLayout) findViewById(R.id.content_view);
        lineMust = (TextView) findViewById(R.id.line_must);
        lineTital = (TextView) findViewById(R.id.line_tital);
        rightArrow = (ImageView) findViewById(R.id.rightArrow);
        bottomLine = findViewById(R.id.bottomLine);
    }

    /**
     * 设置属性
     *
     * @param atrrContainer
     */
    AtrrContainer atrrContainer;

    public void setAtrr(AtrrContainer atrrContainer) {
        this.atrrContainer = atrrContainer;
        if (atrrContainer == null) {
            return;
        }
        lineTital.setText(atrrContainer.getLessFormTitle() + ":");
        if (atrrContainer.getLessFormMust()) {
            lineMust.setVisibility(VISIBLE);
        }
        if (atrrContainer.getLessFormCanClick()) {
            rightArrow.setVisibility(VISIBLE);
        }
        if (atrrContainer.getLessFormBottomLine()) {
            bottomLine.setVisibility(VISIBLE);
        }


    }

    /**
     * 添加控件
     *
     * @param view
     */
    void setContentView(View view) {
        if (atrrContainer == null) {
            throw new RuntimeException("setContentView 方法 必须再 setAtrr 后调用");
        }
        contentView = view;

        if (!TextUtils.isEmpty(atrrContainer.getLessFormName())) {
            /**
             * 加入表单映射
             */
            ViewAttribute va = new ViewAttribute();
            try {
                va.setView(view);
                va.setNull(atrrContainer.getLessFormIsNull());
                va.setMessage(atrrContainer.getLessFormTitle());
                va.setCheckType(getCheckType(atrrContainer.getLessFormCheckType()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            FormInit.sevaLineForm(getContext().getClass().getName(), atrrContainer.getLessFormName(), va);
        }


        if (contentView instanceof TextView) {
            if (contentView instanceof CheckBox) {

            } else {
                RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                contentView.setLayoutParams(lp1);
                ((TextView) contentView).setGravity(Gravity.END);
                ((TextView) contentView).setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimension(R.dimen.form_text_size));
                if (atrrContainer.getLessFormCanClick()) {
                    ((TextView) contentView).setTextColor(ContextCompat.getColor(getContext(), R.color.form_blue));
                }
            }

        }

        if (contentView instanceof FormTimeView) {

            ((TextView) contentView).setTextColor(ContextCompat.getColor(getContext(), R.color.form_blue));
            rightArrow.setVisibility(VISIBLE);


        }

        contentView.setBackgroundResource(0);
        frameLayout.addView(contentView);
    }


    @Override
    public void setEnabled(boolean enabled) {
        if (frameLayout.getChildCount() == 1) {
            View childView = frameLayout.getChildAt(0);
            childView.setEnabled(enabled);
            if (childView instanceof TextView) {
                if (atrrContainer.getLessFormCanClick()) {
                    int color = enabled ? ContextCompat.getColor(getContext(), R.color.form_blue) : ContextCompat.getColor(getContext(), R.color.form_text);
                    ((TextView) contentView).setTextColor(color);
                }
            }
        }
    }

    /**
     * 设置是否 必填
     *
     * @param enabled
     */
    public void setMust(boolean enabled) {
        if (enabled) {
            lineMust.setVisibility(VISIBLE);
        } else {
            lineMust.setVisibility(INVISIBLE);
        }
    }

    //    public View get
    public CheckType getCheckType(int type) {
        switch (type) {
            case 10000:
                return CheckType.CUSTOM;
            case 10001:
                return CheckType.PHONE;
            case 10002:
                return CheckType.EMAIL;
            case 10003:
                return CheckType.CHINESE;
            case 10004:
                return CheckType.IDCARD;
            case 10005:
                return CheckType.ISDATA;
            case 10006:
                return CheckType.AMOUNT_MONEY;
            case 10007:
                return CheckType.AMOUNT;
            case 10008:
                return CheckType.URL;
            case 10009:
                return CheckType.PASSWORD;
        }
        return null;
    }
}
