package com.yxz.youyi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yxz.youyi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScreeningActivity extends AppCompatActivity {

    @BindView(R.id.ll_wzt)
    LinearLayout llWzt;
    @BindView(R.id.search)
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening);
        ButterKnife.bind(this);

        String hintStr = "王者荣耀皮肤";
        SpannableString ss = new SpannableString(hintStr);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(12, true);   //设置搜索框提示信息文字大小
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        search.setHint(new SpannableString(ss));
    }

    @OnClick(R.id.ll_wzt)
    public void onViewClicked() {
        Intent intent = new Intent(ScreeningActivity.this, DetailsActivity.class);
        startActivity(intent);
    }
}
