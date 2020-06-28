package com.yxz.youyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yxz.youyi.R;

public class ActivityAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;

    public ActivityAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * listview列表的长度
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

    static class ViewHolder{
        public ImageView imageView;
        private TextView acdetail,acnum,actime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_activity, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.img_ac);
            holder.acdetail = convertView.findViewById(R.id.ac_detail);
            holder.acnum = convertView.findViewById(R.id.ac_num);
            holder.actime = convertView.findViewById(R.id.ac_time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        //给控件赋值
        holder.acdetail.setText("王者荣耀体验服新版本爆料，好消息：演员辅助无所遁形");
        holder.acnum.setText("18000阅读量");
        holder.actime.setText("06-27 15:24");
        Glide.with(context).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3864462061,3546827695&fm=11&gp=0.jpg").into(holder.imageView);
        return convertView;
    }
}
