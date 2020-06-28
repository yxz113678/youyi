package com.yxz.youyi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yxz.youyi.R;
import com.yxz.youyi.activity.AttentionbarActivity;
import com.yxz.youyi.activity.PublishpostActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class FrMine extends SupportFragment {

    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.attention_bar)
    LinearLayout attentionBar;
    @BindView(R.id.btn_post)
    LinearLayout btnPost;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_mine, null);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.attention_bar, R.id.btn_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.attention_bar:
                //跳转到关注的吧页面
                Intent intent1 = new Intent();
                intent1.setClass(getActivity(), AttentionbarActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_post:
                //跳转到发布帖子页面
                //1.创建意图
                Intent intent = new Intent();
                //2.指定要跳转的页面
                intent.setClass(getActivity(), PublishpostActivity.class);
                //3.执行跳转
                startActivity(intent);
                break;
        }
    }
}
