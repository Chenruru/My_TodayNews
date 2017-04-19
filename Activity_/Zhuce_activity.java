package test.bwie.com.my_todaynews.Activity_;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import test.bwie.com.my_todaynews.R;
import test.bwie.com.my_todaynews.Utils.Night_styleutils;

public class Zhuce_activity extends AppCompatActivity implements View.OnClickListener{

    private TextView phone_number;
    private EditText code_input;
    private Button resend;
    private Button enroll;
    private ImageView phone_auth_code_back;
    private CountDownTimer cdt;//计时器
    private CountDownTimer countDownTimer;

    private int theme=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce_activity);
        initview();
        setjiantint();
        initdata();

    }


    //计时器
    private void initdata() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss");
        countDownTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String format = simpleDateFormat.format(millisUntilFinished);
                resend.setText("重新发送"+format+"s");
                resend.setEnabled(false);
            }

            @Override
            public void onFinish() {
                resend.setText("重新加载");
                resend.setEnabled(true);
            }
        };
        countDownTimer.start();

        /**
         * 初始化事件接收器
         */
            EventHandler  eh = new EventHandler(){
                @Override
                public void afterEvent(int event, int result, Object data) {

                    if (result == SMSSDK.RESULT_COMPLETE) { //回调完成

                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码成功

                            startActivity(new Intent(Zhuce_activity.this, Shoujihao_denglu.class)); //页面跳转

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



    ///监听
    private void setjiantint() {
        enroll.setOnClickListener((View.OnClickListener) this);
        phone_auth_code_back.setOnClickListener(this);
        resend.setOnClickListener(this);
    }
    private void initview() {
        phone_number = (TextView) findViewById(R.id.phone_number);
        code_input = (EditText) findViewById(R.id.code_input);
        resend = (Button) findViewById(R.id.resend);
        enroll = (Button) findViewById(R.id.enroll);
        phone_auth_code_back = (ImageView) findViewById(R.id.phone_auth_code_back);
        Intent intent = getIntent();
        Serializable user = intent.getSerializableExtra("user");
        phone_number.setText(user.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.phone_auth_code_back:
              finish();
                break;
            case R.id.enroll:
               Intent intent=new Intent(Zhuce_activity.this, Xiangji.class);
                startActivity(intent);
                break;
            case R.id.resend:
               countDownTimer.start();
                break;
        }
    }
}
