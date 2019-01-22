package com.xinshen.dynamtheme;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

import permissioin.Permissions;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private String TAG = getClass().getSimpleName();
    private Button btnDefault;

    private Button btnBlue;

    private Button btn_test1;

    private String skinPath1;    //皮肤包路径
    private String skinPath2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Permissions.requestPermissionAll(this);
        btnDefault = findViewById(R.id.btn_default);
        btnBlue = findViewById(R.id.btn_blue);
        btn_test1 =  findViewById(R.id.btn_test1);
        btnDefault.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
        btn_test1.setOnClickListener(this);

        skinPath1 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "blue-skin.skin";
        skinPath2 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test.sink";
        Log.e(TAG,"path::"+skinPath2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_default:
                SkinManager.getInstance().restoreDefaultTheme();
                break;
            case R.id.btn_blue:
                SkinManager.getInstance().loadSkin(skinPath1);
                break;
            case R.id.btn_test1:
                SkinManager.getInstance().loadSkin(skinPath2);
                break;
        }
    }

    /**
     * 动态权限获取
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Permissions.changePermissionState(this,permissions[0],true);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
