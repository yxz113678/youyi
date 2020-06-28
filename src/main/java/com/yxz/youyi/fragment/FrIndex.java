package com.yxz.youyi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yxz.youyi.R;
import com.yxz.youyi.activity.ScreeningActivity;
import com.yxz.youyi.adapter.ActivityAdapter;
import com.yxz.youyi.adapter.MyPagerAdapter;
import com.yxz.youyi.until.NoScrollListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class FrIndex extends SupportFragment implements View.OnTouchListener, ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener {

    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.live_indicator)
    LinearLayout liveIndicator;
    @BindView(R.id.rl_lb)
    RelativeLayout rlLb;
    @BindView(R.id.yx_wzrr)
    ImageView yxWzrr;
    @BindView(R.id.yx_cjzc)
    ImageView yxCjzc;
    @BindView(R.id.yx_dwrg)
    ImageView yxDwrg;
    @BindView(R.id.yx_xw)
    ImageView yxXw;
    @BindView(R.id.dxcyys)
    ImageView dxcyys;
    @BindView(R.id.hyld)
    ImageView hyld;
    @BindView(R.id.yx_mrzh)
    ImageView yxMrzh;
    @BindView(R.id.yx_mhxy)
    ImageView yxMhxy;
    @BindView(R.id.lv_activity)
    NoScrollListView lvActivity;
    Unbinder unbinder;

    public static final int VIEW_PAGER_DELAY = 2000;

    private List<ImageView> mItems;
    private MyPagerAdapter myPagerAdapter;
    private LinearLayout mBottomLiner;
    private ImageView[] mBottomImages;

    private int currentViewPagerItem;
    //是否自动播放
    private boolean isAutoPlay;

    private MyHandler mHandler;
    private Thread mThread;

    private ActivityAdapter activityAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_index, null);
        unbinder = ButterKnife.bind(this, view);

        String hintStr = "游易游戏皮肤全年大促销";
        SpannableString ss = new SpannableString(hintStr);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(12, true);   //设置搜索框提示信息文字大小
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        search.setHint(new SpannableString(ss));

        //listview
        activityAdapter = new ActivityAdapter(getContext());
        lvActivity.setAdapter(activityAdapter);
        lvActivity.setOnItemClickListener(this);

        mBottomLiner = view.findViewById(R.id.live_indicator);

        mHandler = new MyHandler(this);

        //配置轮播图的viewPager
        mItems = new ArrayList<>();
        myPagerAdapter = new MyPagerAdapter(mItems, getContext());
        viewPager.setAdapter(myPagerAdapter);
        view.setOnTouchListener(this);
        viewPager.addOnPageChangeListener(this);
        isAutoPlay = true;

        //添加ImageView
        addImageView();
        myPagerAdapter.notifyDataSetChanged();
        //设置底部小圆点
        setBottomIndicator();

        return view;
    }

    private void addImageView() {
        ImageView view0 = new ImageView(getContext());
        view0.setImageResource(R.mipmap.pic0);
        ImageView view1 = new ImageView(getContext());
        view1.setImageResource(R.mipmap.pic1);
        ImageView view2 = new ImageView(getContext());
        view2.setImageResource(R.mipmap.pic2);
        ImageView view3 = new ImageView(getContext());
        view3.setImageResource(R.mipmap.pic3);

        view0.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view3.setScaleType(ImageView.ScaleType.CENTER_CROP);

        mItems.add(view0);
        mItems.add(view1);
        mItems.add(view2);
        mItems.add(view3);
    }

    private void setBottomIndicator() {
        //小圆点
        mBottomImages = new ImageView[mItems.size()];
        for (int i = 0; i < mBottomImages.length; i++) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(10, 0, 10, 0);  //设置小圆点之间的间距
            imageView.setLayoutParams(params);
            //如果当前是第一个，设置为选中状态
            if (i == 0) {
                imageView.setImageResource(R.drawable.indicator_select);
            } else {
                imageView.setImageResource(R.drawable.indicator_no_select);
            }
            mBottomImages[i] = imageView;
            //添加到父容器
            mBottomLiner.addView(imageView);
        }
        //让其在最大值的中间开始滑动, 一定要在 mBottomImages初始化之前完成
        int mid = MyPagerAdapter.MAX_SCROLL_VALUE / 2;
        viewPager.setCurrentItem(mid);
        currentViewPagerItem = mid;

        //定时发送消息
        mThread = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    mHandler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(3000);
                        Thread.sleep(FrIndex.VIEW_PAGER_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mThread.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.yx_wzrr, R.id.yx_cjzc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yx_wzrr:
                Intent intent = new Intent(getActivity(), ScreeningActivity.class);
                startActivity(intent);
                break;
            case R.id.yx_cjzc:
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isAutoPlay = false;
                break;
            case MotionEvent.ACTION_UP:
                isAutoPlay = true;
                break;
        }
        return false;
    }

    /**
     * ViewPager的监听事件
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentViewPagerItem = position;
        if (mItems != null) {
            position %= mBottomImages.length;
            int total = mBottomImages.length;

            for (int i = 0; i < total; i++) {
                if (i == position) {
                    mBottomImages[i].setImageResource(R.drawable.indicator_select);
                } else {
                    mBottomImages[i].setImageResource(R.drawable.indicator_no_select);
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                position = position + 1;
                Toast.makeText(_mActivity, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                position = position + 1;
                Toast.makeText(_mActivity, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                position = position + 1;
                Toast.makeText(_mActivity, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                position = position + 1;
                Toast.makeText(_mActivity, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private static class MyHandler extends Handler {
        private WeakReference<FrIndex> mWeakReference;

        public MyHandler(FrIndex frIndex) {
            mWeakReference = new WeakReference<FrIndex>(frIndex);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    FrIndex frIndex = mWeakReference.get();
                    if (frIndex.isAutoPlay) {
                        frIndex.viewPager.setCurrentItem(++frIndex.currentViewPagerItem);
                    }
                    break;
            }
        }
    }
}
