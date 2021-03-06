package com.pili.sample.ypermission;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pili.plpermission.MobileSettingUtil;
import com.pili.plpermission.PermissionManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permissionCheck();
            }
        });
    }

    private void permissionCheck() {
        final long t1 = System.currentTimeMillis();
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO};
        PermissionManager.requestPermission(this, permissions, new PermissionManager.PermissionsListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "获取了所有权限，耗时：" +
                        (System.currentTimeMillis() - t1), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionDenied(@NonNull List<PermissionManager.NoPermission> noPermissionsList) {
                super.onPermissionDenied(noPermissionsList);
                StringBuilder stringBuilder = new StringBuilder();
                for (PermissionManager.NoPermission noPermission : noPermissionsList) {
                    stringBuilder.append(noPermission.permission).append("\n");
                }
                Toast.makeText(MainActivity.this, "被拒绝的权限：\n" +
                        stringBuilder.toString(), Toast.LENGTH_LONG).show();
                MobileSettingUtil.gotoPermissionSettings(MainActivity.this, 123);
            }
        });
    }
}
