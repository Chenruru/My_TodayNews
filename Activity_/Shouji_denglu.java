package test.bwie.com.my_todaynews.Activity_;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import test.bwie.com.my_todaynews.R;
import test.bwie.com.my_todaynews.Utils.Night_styleutils;

public class Shouji_denglu extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageview_;
    private TextView text_zhuce;
    private TextView text_mima;
    private Button button_denglu;
private int theme=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouji_denglu);
        initview();  //初始化
        setjianting();//设置监听
    }

    private void setjianting() {
        imageview_.setOnClickListener((View.OnClickListener) this);
    }

    private void initview() {
        imageview_ = (ImageView) findViewById(R.id.image06);
        EditText edie_phone = (EditText) findViewById(R.id.edie_phone);
        EditText edie_duanixn = (EditText) findViewById(R.id.edie_duanixn);
        text_zhuce = (TextView) findViewById(R.id.text_zhuce);
        text_mima = (TextView) findViewById(R.id.text_mima);
        button_denglu = (Button) findViewById(R.id.button_denglu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){   //返回
            case R.id.image06:
                finish();
                break;
            case R.id.text_zhuce:
                Intent intent=new Intent(Shouji_denglu.this,Shoujihao_zhuce.class);
                startActivity(intent);
                break;
            case R.id.text_mima:
                Intent intent2=new Intent(Shouji_denglu.this,Zhaohui_mima.class);
                startActivity(intent2);
                break;
        }

        }
    }
