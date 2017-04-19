package test.bwie.com.my_todaynews.Activity_;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import test.bwie.com.my_todaynews.R;
import test.bwie.com.my_todaynews.Utils.Night_styleutils;

public class Denglu_ extends AppCompatActivity implements View.OnClickListener{
    private int theme=0;
    private ImageView imageview_;
    private Button btn_login;
    private Button  btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu_);

        initview();  //初始化
        setjianting();//设置监听
    }
    private void setjianting() {
        imageview_.setOnClickListener((View.OnClickListener) this);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    private void initview() {
        imageview_ = (ImageView) findViewById(R.id.image06);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register=(Button)findViewById(R.id.btn_register);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){   //返回
            case R.id.image06:
                finish();
                break;

            case R.id.btn_login:
                Intent intent=new Intent(Denglu_.this,Shoujihao_denglu.class);
                startActivity(intent);
                break;
            case R.id.btn_register:
                Intent intent2=new Intent(Denglu_.this,Shoujihao_zhuce.class);
                startActivity(intent2);
                break;
        }



    }
}
