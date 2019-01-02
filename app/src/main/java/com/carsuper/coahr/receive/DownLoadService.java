package com.carsuper.coahr.receive;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Created by Leehor
 * on 2018/10/31
 * on 11:16
 * APK更新类（完整）
 * 本地下载apk包安装更新
 */
public class DownLoadService extends Service {
    private DownloadFinishReceiver mReceiver;
    public DownLoadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //注册下载完成的广播
        mReceiver = new DownloadFinishReceiver();
        registerReceiver(mReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new DownBinder();
    }

    class DownBinder extends Binder {

        public void startDownload (String downUrl) {
            //删除已经存在的apk包
            File apkFile = new File(DownLoadService.this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "卡速宝.apk");
            if (apkFile.exists()) {
                apkFile.delete();
            }
            //初始化DownloadManager并开始下载
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downUrl));
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"卡速宝.apk");
            request.setTitle("卡速宝更新");
            request.setDescription("下载完成后请点击打开");
            request.setDestinationUri(Uri.fromFile(file));
            DownloadManager mDownloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            mDownloadManager.enqueue(request);
        }
    }

    //下载完成的广播
    private class DownloadFinishReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //Android获取一个用于打开APK文件的intent
            Intent intent1 = new Intent(Intent.ACTION_VIEW);
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if(Build.VERSION.SDK_INT>=24) { //判读版本是否在7.0以上
                //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                Uri apkUri =
                        FileProvider.getUriForFile(DownLoadService.this, "com.carsuper.coahr",
                                new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"卡速宝.apk"));
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent1.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent1.setDataAndType(apkUri, "application/vnd.android.package-archive");
            }else{
                intent1.setDataAndType(Uri.fromFile(new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"卡速宝.apk")),
                        "application/vnd.android.package-archive");
            }
            DownLoadService.this.startActivity(intent1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

}
