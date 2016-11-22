package com.example.jason.gpstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class logActivity extends AppCompatActivity {
    String bmobID="1a0be343c7ba406c76aaaa2dabcfb598";
    List<String> nameList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bmob.initialize(this, bmobID);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private List getNameList(final String[] name){
        nameList.clear();

        BmobQuery<positionUpdater> query = new BmobQuery<positionUpdater>();
        query.addWhereEqualTo("name", name[0]);
        query.setLimit(50);
        query.findObjects(new FindListener<positionUpdater>() {
            @Override
            public void done(List<positionUpdater> list, BmobException e) {
                if (e==null){
                    if (list.isEmpty()){
                        showToast("用户名不存在");
                    }
                    for (positionUpdater pu:list){

                        if (pu.getPwd().toString().equals(name[1])){
                            showToast("用户名密码正确");
                        }
                        else {
                            showToast("密码错误");
                        }
                    }
               }
                else {
                    showToast("查询错误");
                }
            }
        });

        return nameList;
    }

    private List searchNameList(final String[] name){
        nameList.clear();

        BmobQuery<positionUpdater> query = new BmobQuery<positionUpdater>();
        query.addWhereEqualTo("name", name[0]);
        query.setLimit(50);
        query.findObjects(new FindListener<positionUpdater>() {
            @Override
            public void done(List<positionUpdater> list, BmobException e) {
                if (e==null){
                    if (list.isEmpty()){
                        positionUpdater puu=new positionUpdater();
                        puu.setName(name[0]);
                        puu.setPwd(name[1]);
                        puu.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    showToast("注册成功，请点击登陆");
                                } else {
                                    showToast("注册失败");
                                }
                            }
                        });
                    }
                   else{
                        showToast("用户名已存在");
                    }
                }
                else {
                    showToast("查询错误");
                }
            }
        });

        return nameList;
    }

    public void onLogin(View view) {
        AutoCompleteTextView autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.userName);
        EditText editText=(EditText)findViewById(R.id.pwd);
        if(autoCompleteTextView.getText().length()!=0&&editText.getText().length()!=0){
            getNameList(new String[]{autoCompleteTextView.getText().toString(),editText.getText().toString()});
        }
        else {
            showToast("请输入用户名密码");
        }

    }
    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void onReg(View view) {
        AutoCompleteTextView autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.userName);
        EditText editText=(EditText)findViewById(R.id.pwd);
        if(autoCompleteTextView.getText().length()!=0&&editText.getText().length()!=0){
            searchNameList(new String[]{autoCompleteTextView.getText().toString(),editText.getText().toString()});
        }
        else {
            showToast("请输入用户名密码");
        }
    }
}
