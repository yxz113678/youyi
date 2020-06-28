package com.yxz.youyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yxz.youyi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoundAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;

    public FoundAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * listview列表的长度
     *
     * @return
     */
    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        public ImageView imageView;
        private TextView foundname, fooundnum, foundintroduce;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_found, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.img_found);
            holder.foundname = convertView.findViewById(R.id.found_name);
            holder.fooundnum = convertView.findViewById(R.id.found_num);
            holder.foundintroduce = convertView.findViewById(R.id.found_introduce);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //给控件赋值
        holder.foundname.setText("王者荣耀账号出售");
        holder.fooundnum.setText("18000成功购买账号");
        holder.foundintroduce.setText("人人都想要的游戏账号，更多优惠享不停");
        Glide.with(context).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3864462061,3546827695&fm=11&gp=0.jpg").into(holder.imageView);
        return convertView;
    }
}
