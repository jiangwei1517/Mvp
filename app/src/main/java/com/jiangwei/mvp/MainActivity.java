package com.jiangwei.mvp;

import com.jiangwei.mvplib.MvpActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView, View.OnClickListener {
    private Button mBtn;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onLoad(String loading) {
        Toast.makeText(MainActivity.this, "onLoading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn) {
            getPresenter().getData();
        }
    }
}
