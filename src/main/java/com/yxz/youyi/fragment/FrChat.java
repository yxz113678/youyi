package com.yxz.youyi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.yxz.youyi.R;
import com.yxz.youyi.activity.PostdeatilActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class FrChat extends SupportFragment {
    @BindView(R.id.sv)
    SearchView sv;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.pub_content)
    EditText pubContent;
    @BindView(R.id.add_img)
    ImageView addImg;
    @BindView(R.id.post_detail)
    LinearLayout postDetail;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_chat, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.post_detail)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), PostdeatilActivity.class);
        startActivity(intent);
    }
}
