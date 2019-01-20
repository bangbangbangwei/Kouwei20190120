package com.dc.kouwei20190120.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dc.kouwei20190120.R;

public class ReginActivity extends AppCompatActivity {

    private EditText ed_name;
    private EditText ed_pwd;
    private EditText ed_cpwd;
    private Button regin;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regin);
        initView();
        initData();
    }

    private void initData() {
        //注册
        regin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_name.getText().toString();
                String pwd = ed_pwd.getText().toString();
                String cpwd = ed_cpwd.getText().toString();
                if (cpwd.equals(pwd)){
                    if (name.equals("") && pwd.equals("") && cpwd.equals("")){
                        Toast.makeText(ReginActivity.this, "请输入密码或账号", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        edit.putString("name",name);
                        edit.putString("pwd",pwd);
                        edit.commit();
                        Intent intent = new Intent(ReginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Toast.makeText(ReginActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    /**
     * 获取资源Id
     */
    private void initView() {
        ed_name = findViewById(R.id.ed_name);
        ed_pwd = findViewById(R.id.ed_pwd);
        ed_cpwd = findViewById(R.id.ed_cpwd);
        regin = findViewById(R.id.regin);
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        edit = sharedPreferences.edit();
    }
}
