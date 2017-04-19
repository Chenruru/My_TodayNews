package test.bwie.com.my_todaynews.Activity_;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import test.bwie.com.my_todaynews.R;
import test.bwie.com.my_todaynews.Utils.Night_styleutils;

public class Shoujihao_zhuce extends AppCompatActivity implements View.OnClickListener {
    private int theme = 0;
    private ImageView imageview_;
    private Button bu_next;
    private EditText edit_text;
    public EventHandler eh; //事件接收器
    private CountDownTimer cdt;//计时器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoujihao_zhuce);
        initview();
        setjianting();
        initdata();
    }

    private void initdata() {

        eh = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) { //回调完成

                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码成功

                        startActivity(new Intent(Shoujihao_zhuce.this, Yanzheng_ma.class)); //页面跳转

                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){ //获取验证码成功

                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){ //返回支持发送验证码的国家列表

                    }
                }else{
                    ((Throwable)data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    private void setjianting() {

        imageview_.setOnClickListener((View.OnClickListener) this);
        edit_text.setOnClickListener((View.OnClickListener) this);
        bu_next.setOnClickListener((View.OnClickListener) this);
    }

    private void initview() {
        imageview_ = (ImageView) findViewById(R.id.image06);
        edit_text = (EditText) findViewById(R.id.edit_text);
        bu_next = (Button) findViewById(R.id.bu_next);
        cdt = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };
    }

    /**
     * 正则匹配手机号码
     * @param tel
     * @return
     */
    public boolean checkTel(String tel){
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher matcher = p.matcher(tel);
        return matcher.matches();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {   //返回
            case R.id.image06:
                finish();
                break;
        }
        switch (v.getId()) {   //手机号
            case R.id.bu_next:
                SMSSDK.getSupportedCountries();//获取短信目前支持的国家列表
                if(!edit_text.getText().toString().trim().equals("")){
                    if (checkTel(edit_text.getText().toString().trim())) {
                        SMSSDK.getVerificationCode("+86",edit_text.getText().toString());//获取验证码
                        cdt.start();
                        Intent intent=new Intent(Shoujihao_zhuce.this,Zhuce_activity.class);
                        intent.putExtra("user",edit_text.getText().toString());
                        startActivity(intent);
                    }else{
                        Toast.makeText(Shoujihao_zhuce.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Shoujihao_zhuce.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}

