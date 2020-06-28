package com.yxz.youyi.activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yxz.youyi.R;
import com.yxz.youyi.fragment.FrChat;
import com.yxz.youyi.fragment.FrIndex;
import com.yxz.youyi.fragment.FrMine;
import com.yxz.youyi.fragment.FrNews;
import com.yxz.youyi.fragment.FrPublish;
import com.yxz.youyi.until.DatabaseHelper;
import com.yxz.youyi.until.StatusBarUtil;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends SupportActivity implements RadioGroup.OnCheckedChangeListener {
    private FrIndex frIndex;
    private FrChat frChat;
    private FrPublish frPublish;
    private FrNews frNews;
    private FrMine frMine;
    private int curPosition = 0;  //当前展示的fragment下标

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup group = findViewById(R.id.rd_group);
        group.setOnCheckedChangeListener(this);

        //数据库操作
        DatabaseHelper helper = new DatabaseHelper(this);
        helper.getWritableDatabase();


        //设置第一次进入页面不会自动弹出键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //当FitsSystemWindow设置true时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)){
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }

        loadFragment();
        //默认选中fragment
        RadioButton rbIndex = (RadioButton) group.getChildAt(0);
        rbIndex.setChecked(true);
    }
    /**
     * 装载fragment
     */
    private void loadFragment() {
        if (frIndex == null){
            frIndex = new FrIndex();
            frChat = new FrChat();
            frPublish = new FrPublish();
            frNews = new FrNews();
            frMine = new FrMine();
        }else {
            frIndex = findFragment(FrIndex.class);
            frChat = findFragment(FrChat.class);
            frPublish = findFragment(FrPublish.class);
            frNews = findFragment(FrNews.class);
            frMine = findFragment(FrMine.class);
        }
        //装载多个fragment
        loadMultipleRootFragment(R.id.fragment_layout, 0,frIndex,frChat,frPublish,frNews,frMine);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_index:
                showHideFragment(frIndex,getPreFragment());
                curPosition = 0;
                break;
            case R.id.rb_chat:
                showHideFragment(frChat,getPreFragment());
                curPosition = 1;
                break;
            case R.id.rb_publish:
                showHideFragment(frPublish,getPreFragment());
                curPosition = 2;
                break;
            case R.id.rb_news:
                showHideFragment(frNews,getPreFragment());
                curPosition = 3;
                break;
            case R.id.rb_mine:
                showHideFragment(frMine,getPreFragment());
                curPosition = 4;
                break;
        }
    }

    /**
     *获取上一个显示的fragment对象
     * @return
     */
    private SupportFragment getPreFragment(){
        switch (curPosition){
            case 0:
                return frIndex;
            case 1:
                return frChat;
            case 2:
                return frPublish;
            case 3:
                return frNews;
            case 4:
                return frMine;
        }
        return null;
    }
}
