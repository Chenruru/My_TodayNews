package test.bwie.com.my_todaynews.Activity_;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import test.bwie.com.my_todaynews.R;
import test.bwie.com.my_todaynews.Utils.MyXutils;

/**
 * date: 2017/4/10
 * author:陈茹
 * 类的用途:
 */

public class MyFragment extends Fragment {

    private ListView listview;
    private String name;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_list, null);
        listview = (ListView) view.findViewById(R.id.listview);
        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.get("name").toString();
            Log.d("MyFragment", name);
        }
        MyXutils x=new MyXutils(listview,getActivity());
            x.getdata(name);

        return view;
    }

    public static MyFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("name", name);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);

        return fragment;
    }


}
