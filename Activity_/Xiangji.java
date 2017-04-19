package test.bwie.com.my_todaynews.Activity_;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import test.bwie.com.my_todaynews.R;
import test.bwie.com.my_todaynews.Utils.Night_styleutils;

/**
 * date: 2017/4/14
 * author:陈茹
 * 类的用途:
 */

public class Xiangji extends AppCompatActivity {
    private EditText yanzhengma;
    private TextView wancheng;
    private ImageView touxiang;
    private int theme=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geren);
        initView();
        initData();


    }

    private void initView() {
        touxiang = (ImageView) findViewById(R.id.touxiang);
        yanzhengma = (EditText) findViewById(R.id.yanzhengma);
        wancheng = (TextView) findViewById(R.id.wancheng);
    }

    private void initData() {
        touxiang.setOnClickListener(new View.OnClickListener() {
            private AlertDialog.Builder builder;

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =  new AlertDialog.Builder(Xiangji.this);
                builder.setItems(new String[]{"拍照", "相册", "取消"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 1:
                                getLocalImage(102);
                                break;
                            case 0:
                                readXiangji(103);
                                break;
                            case 3:
                                break;
                        }
                    }
                });
                builder.create().show();
            }

        });

    }
    private void readXiangji(int code) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,code);
    }

    private void getLocalImage(int code) {
        Intent intent = new Intent(Intent.ACTION_PICK,null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,code);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null&&requestCode==101&&resultCode==201){
            String name = data.getStringExtra("name");
            yanzhengma.setText(name);
        }
        if(requestCode==102 && resultCode==RESULT_OK ){
            //读取本地相册
            readLocalImage(data);
        }
        if(requestCode==103 && resultCode==RESULT_OK ){
            //获取拍照返回的数据
            getPaizhaoData(data);
        }
    }
    private void getPaizhaoData(Intent data) {
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");
        touxiang.setImageBitmap(bitmap);

    }

    private void readLocalImage(Intent data) {
        if(data==null){
            return;
        }
        Uri uri = data.getData();
        //转化成bitmap
        Bitmap bitmap = getBitmapFromUri(uri);
        //显示图片
        touxiang.setImageBitmap(bitmap);
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        Bitmap bitmap = null;
        try {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
