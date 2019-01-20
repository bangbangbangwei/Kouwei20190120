package com.dc.kouwei20190120.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.dc.kouwei20190120.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText ed_name,ed_pwd;
    private CheckBox ck;
    private Button login,regin,qq;
    private SharedPreferences.Editor edit;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        //登陆
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_name.getText().toString();
                String pwd = ed_pwd.getText().toString();
                if (name.equals(sharedPreferences.getString("name",""))&&pwd.equals(sharedPreferences.getString("pwd",""))){
                    Intent intent = new Intent(MainActivity.this, BasesActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //注册
        regin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReginActivity.class);
                startActivity(intent);
            }
        });
        //第三方登录
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
            }
        });
    }
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    /**
     * 获取资源Id
     */
    private void initView() {

        ed_name = findViewById(R.id.ed_name);
        ed_pwd = findViewById(R.id.ed_pwd);
        ck = findViewById(R.id.ck);
        login = findViewById(R.id.login);
        regin = findViewById(R.id.regin);
        qq = findViewById(R.id.qq);
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        edit = sharedPreferences.edit();
    }

    /**
     * 第三方登录回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
