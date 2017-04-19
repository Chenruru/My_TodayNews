package test.bwie.com.my_todaynews.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import test.bwie.com.my_todaynews.R;
import test.bwie.com.my_todaynews.Utils.GsonBean;

/**
 * date: 2017/4/10
 * author:陈茹
 * 类的用途:
 */

public class Myadapter extends BaseAdapter {
    List<GsonBean.ResultBean.DataBean> list;
    private Context context;
    public Myadapter(Context context, List<GsonBean.ResultBean.DataBean> list){
        this.context=context;
        this.list=list;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=View.inflate(context,R.layout.fragment_,null);
            ImageView imageview= (ImageView) convertView.findViewById(R.id.imageview);
            TextView textView= (TextView) convertView.findViewById(R.id.textView);

            textView.setText(list.get(position).getTitle());

//            x.image().bind(imageview,list.get(position).getThumbnail_pic_s(),new ImageOptions.Builder()
//                    .setCrop(true)
//                    .build());
            //Xutils网络缓存图片
            x.image().bind(imageview,list.get(position).getThumbnail_pic_s());
        }
        return convertView;
    }
}
