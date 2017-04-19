package test.bwie.com.my_todaynews.Activity_;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import test.bwie.com.my_todaynews.R;
import test.bwie.com.my_todaynews.Utils.Night_styleutils;

public class Yanzheng_ma extends AppCompatActivity {

    private int theme=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yanzheng_ma);
    }
}
