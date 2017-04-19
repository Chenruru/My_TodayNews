package test.bwie.com.my_todaynews.Adapter;





import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * date: 2017/4/10
 * author:陈茹
 * 类的用途:
 */
//private String[] titles = {"头条","社会","国内","国际","娱乐","体育","军事","会计"};
public class MainAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> listtitle;

    public MainAdapter(FragmentManager fm, List<Fragment> fragments, List<String> listtitle) {
        super(fm);
        this.fragments = fragments;
        this.listtitle = listtitle;
    }

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listtitle.get(position);
    }
}
