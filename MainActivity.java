package test.bwie.com.my_todaynews;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import test.bwie.com.my_todaynews.Activity_.Denglu_;
import test.bwie.com.my_todaynews.Activity_.Diaozhuan_activity;
import test.bwie.com.my_todaynews.Activity_.Left_menu;
import test.bwie.com.my_todaynews.Activity_.MyFragment;
import test.bwie.com.my_todaynews.Activity_.Shouji_denglu;
import test.bwie.com.my_todaynews.Adapter.MainAdapter;
import test.bwie.com.my_todaynews.Utils.NextUtil;
import test.bwie.com.my_todaynews.Utils.Night_styleutils;
import test.bwie.com.my_todaynews.Utils.UiUtils;
import test.bwie.com.my_todaynews.Utils.Url;
import test.bwie.com.my_todaynews.sqlite.MyHelper;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private int theme = 0;
    private List<String> listtitle = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private TabLayout tableLayout;
    private MainAdapter mainAdapter;
    private ImageView imageview;
    private CircleImageView image05;
    private SlidingMenu menu;
    private TextView gengduo_login;
    private ImageView phone_login;
    private RadioButton button_night;
    private ImageView qq_login;
    private LinearLayout sliding_linearno01;
    private GoogleApiClient client;
    private RelativeLayout relate;
    private TextView name;
    private ImageView imageView;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();

        initdata();//侧滑
        // initfragemnt();
       // data();
        initfragemnt();

        iclude();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void iclude(){
        sp = getSharedPreferences("name", MODE_PRIVATE);
        edit = sp.edit();
        boolean isfrit = sp.getBoolean("isfrit", false);
        if (isfrit){
            Glide.with(MainActivity.this).load(sp.getString("imageview",null)).into(image05);
            sliding_linearno01.setVisibility(View.GONE);  //吧布局显示的在代码中隐藏
            relate.setVisibility(View.VISIBLE);
            Glide.with(MainActivity.this).load(sp.getString("imageview",null)).into(imageView);
            name.setText(sp.getString("name",null));
        }

    }
    //网络判断
//    private void data() {
//
//        //不是wifi连接直接弹出对话框
//        boolean connected = NextUtil.isMobile(MainActivity.this);
//        if (connected) {
//            Toast.makeText(MainActivity.this, "正在使用移动数据", Toast.LENGTH_SHORT);
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
//            alertDialog.setMessage("当前不是wifi连接，是否继续");
//            alertDialog.setPositiveButton("继续", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    mainAdapter = new MainAdapter(getSupportFragmentManager(), fragments, listtitle);
//                    viewpager.setAdapter(mainAdapter);
//                }
//            });
//            alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//            alertDialog.create();
//            alertDialog.show();
//        }
//
//
//    }

    //给tablelayout设置适配
    private void initfragemnt() {
        inittitle();
        initfragment();
        mainAdapter = new MainAdapter(getSupportFragmentManager(), fragments, listtitle);
        //  mainAdapter.setFragments(fragments);
        viewpager.setAdapter(mainAdapter);
        //设置title滑动
        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置tabLayout
        tableLayout.setupWithViewPager(viewpager);

        tableLayout.setTabsFromPagerAdapter(mainAdapter);
        //设置文字的颜色
        tableLayout.setTabTextColors(Color.GRAY, Color.RED);
        //设置下划线的颜色
        tableLayout.setSelectedTabIndicatorColor(Color.RED);
    }

    //Fragment复用
    private void initfragment() {
        //添加每个fragment，设置URL路径
        fragments.add(MyFragment.newInstance(Url.url1));
        fragments.add(MyFragment.newInstance(Url.url2));
        fragments.add(MyFragment.newInstance(Url.url3));
        fragments.add(MyFragment.newInstance(Url.url4));
        fragments.add(MyFragment.newInstance(Url.url5));
        fragments.add(MyFragment.newInstance(Url.url6));
        fragments.add(MyFragment.newInstance(Url.url7));
        fragments.add(MyFragment.newInstance(Url.url8));
        fragments.add(MyFragment.newInstance(Url.url9));
        fragments.add(MyFragment.newInstance(Url.url10));

    }

    //添加title标题
    private void inittitle() {
        //设置标题 string类型集合
        listtitle.add("头条");
        listtitle.add("社会");
        listtitle.add("国内");
        listtitle.add("国际");
        listtitle.add("娱乐");
        listtitle.add("体育");
        listtitle.add("军事");
        listtitle.add("科技");
        listtitle.add("音乐");
        listtitle.add("科技");
    }

    private void initview() {
        tableLayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        imageview = (ImageView) findViewById(R.id.image);
        image05 = (CircleImageView) findViewById(R.id.image05);

        //点击图片侧滑
        image05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断点击侧滑第二个界面  显示
                if (menu.isSecondaryMenuShowing()) {
                    menu.showContent();
                } else {
                    menu.showSecondaryMenu();
                }
            }
        });
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Diaozhuan_activity.class);
                startActivity(intent);

            }
        });
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);//进入动画
        finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        startActivity(intent);
    }


    //设置侧滑
    private void initdata() {
        menu = new SlidingMenu(MainActivity.this);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        // menu.setShadowDrawable(R.color.colorAccent);


        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        int widthPixels = this.getResources().getDisplayMetrics().widthPixels;
        menu.setBehindOffset(widthPixels / 5 * 1);

        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.activity_left_menu);
        //找侧滑菜单的id
        gengduo_login = (TextView) menu.findViewById(R.id.gengduo_login);
        phone_login = (ImageView) menu.findViewById(R.id.phone_login);
        button_night = (RadioButton) menu.findViewById(R.id.button_night);
        sliding_linearno01 = (LinearLayout) menu.findViewById(R.id.sliding_linearno01);
        relate = (RelativeLayout) menu.findViewById(R.id.relate);
        name = (TextView) menu.findViewById(R.id.name);
        imageView = (ImageView) menu.findViewById(R.id.imageView);
        button_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.switchAppTheme(MainActivity.this);
                reload();
            }
        });
        gengduo_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Denglu_.class);
                startActivity(intent2);

            }
        });
        phone_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Shouji_denglu.class);
                startActivity(intent);
            }
        });

        //QQ登录
        qq_login = (ImageView) menu.findViewById(R.id.qq_login);
        qq_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击QQ", Toast.LENGTH_SHORT).show();
                UMShareAPI.get(MainActivity.this).doOauthVerify(MainActivity.this, SHARE_MEDIA.QQ.toSnsPlatform().mPlatform, umAuthListener);

            }
        });


    }


    //授权
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action,Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            //
            switch (action) {
                case ACTION_AUTHORIZE://授权登录后执行的操作
                    //获得数据
                    UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);

                    break;
                case ACTION_DELETE://注销

                    break;
                case ACTION_GET_PROFILE://获取用户信息
                    //页面切换
                    // Glide.with(MainActivity.this).load(data.get("iconurl")).into(iv);
                    Glide.with(MainActivity.this).load(data.get("iconurl")).error(R.mipmap.ic_launcher).into(image05);
                   //
                    sliding_linearno01.setVisibility(View.GONE);  //吧布局显示的在代码中隐藏
                    relate.setVisibility(View.VISIBLE);
                    Glide.with(MainActivity.this).load(data.get("iconurl")).error(R.mipmap.ic_launcher).into(imageView);
                     name.setText(data.get("name"));

                    edit.putBoolean("isfrit",true);
                    edit.putString("name",data.get("name"));
                    edit.putString("imageview",data.get("iconurl"));
                    edit.commit();
                    break;
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
