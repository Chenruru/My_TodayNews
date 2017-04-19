package test.bwie.com.my_todaynews.Activity_;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import test.bwie.com.my_todaynews.R;
import test.bwie.com.my_todaynews.Utils.Night_styleutils;

public class Shoujihao_denglu extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageview_;
    private Button btn_login;
    private Button button_denglu;
    private EditText edie_duanixn;
    private EditText edie_phone;
    private TextView text_zhuce;
    private TextView text_mima;
    private SharedPreferences sharedPreferences;

    private int theme=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoujihao_denglu);
        initview();  //初始化
        setjianting();//设置监听
        initVarble();//变量

    }


    private void initVarble() {

        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("jizhu", false)) {
            edie_phone.setText(sharedPreferences.getString("name", ""));
            edie_duanixn.setText(sharedPreferences.getString("pwd", ""));
            // ck_jizhu.setChecked(true);
        }//if(sharedPreferences.getBoolean("zidong",false)){
        // ck_zidong.setChecked(true);
        //}

    }


    //设置监听
    private void setjianting() {
        imageview_.setOnClickListener((View.OnClickListener) this);
        button_denglu.setOnClickListener(this);
        text_zhuce.setOnClickListener(this);
        text_mima.setOnClickListener(this);
    }

    //查找id
    private void initview() {
        imageview_ = (ImageView) findViewById(R.id.image06);
        button_denglu = (Button) findViewById(R.id.button_denglu);
        edie_duanixn = (EditText) findViewById(R.id.edie_duanixn);
        edie_phone = (EditText) findViewById(R.id.edie_phone);
        text_zhuce = (TextView) findViewById(R.id.text_zhuce);
        text_mima = (TextView) findViewById(R.id.text_mima);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {   //返回
            case R.id.image06:
                finish();
                break;
            case R.id.text_mima:
                Intent intent = new Intent(this, Zhaohui_mima.class);
                startActivity(intent);
                break;
            //账号注册跳转
            case R.id.text_zhuce:
                Intent intent3 = new Intent(this, Shoujihao_zhuce.class);
                startActivity(intent3);
                break;
            case R.id.button_denglu:
                String name = edie_duanixn.getText().toString();
                String password = edie_phone.getText().toString();

                if (name.length() <= 0 && password.length() <= 0) {

                    Toast.makeText(Shoujihao_denglu.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else if (name.length() <= 0) {
                    Toast.makeText(Shoujihao_denglu.this, "请输入密码 ", Toast.LENGTH_SHORT).show();

                } else if (name != null && password != null) {
                    Intent intent2 = new Intent(this, Xiangji.class);
                    startActivity(intent2);
                }



                    break;


                }

        }

    }
