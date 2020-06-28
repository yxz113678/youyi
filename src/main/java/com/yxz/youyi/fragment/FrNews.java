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
import com.yxz.youyi.activity.SubscribeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class FrNews extends SupportFragment {
    @BindView(R.id.zixun)
    ImageView zixun;
    @BindView(R.id.dingyue)
    ImageView dingyue;
    @BindView(R.id.my_dingyue)
    LinearLayout myDingyue;
    @BindView(R.id.guanzhu)
    ImageView guanzhu;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_news, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.dingyue)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), SubscribeActivity.class);
        startActivity(intent);
    }
}
