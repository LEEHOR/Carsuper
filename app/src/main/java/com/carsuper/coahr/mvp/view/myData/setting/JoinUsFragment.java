package com.carsuper.coahr.mvp.view.myData.setting;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.contract.myData.setting.JoinUsContract;
import com.carsuper.coahr.mvp.presenter.myData.setting.JoinUsPresenter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.utils.NavigationBarUtils;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.carsuper.coahr.widgets.x5web.SimpleWebView;
import com.carsuper.coahr.widgets.x5web.X5WebViewByMyShelf;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 * 加入我们和首页新闻跳转
 *
 */
public class JoinUsFragment extends BaseFragment<JoinUsContract.Presenter> implements JoinUsContract.View {
    @Inject
    JoinUsPresenter joinUsPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.webView)
    SimpleWebView webView;

    private  String url;
    private int type;
    private  String title;

    public static JoinUsFragment newInstance(String url,int type,String title) {
        JoinUsFragment joinUsFragment=new JoinUsFragment();
        Bundle bundle=new Bundle();
        bundle.putString("url",url);
        bundle.putInt("type",type);
        bundle.putString("title",title);
        joinUsFragment.setArguments(bundle);
        return joinUsFragment;
    }

    @Override
    public JoinUsContract.Presenter getPresenter() {
        return joinUsPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_joinus;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _mActivity.onBackPressed();
            }
        });
        initHardwareAccelerate();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  if (NavigationBarUtils.hasNavigationBarFun(_mActivity)){
            if (NavigationBarUtils.isNavigationBarShow(_mActivity)){
                NavigationBarUtils.hideBottomUIMenu(_mActivity);
            }
        }*/
    }

    @Override
    public void initData() {

        if (getArguments() != null) {
             url = getArguments().getString("url");
             type = getArguments().getInt("type");
             title = getArguments().getString("title");
        }


        if (type == 1){
            tbTittle.getTvTittle().setText("最新新闻");
        }
        if (type == 2){
            tbTittle.getTvTittle().setText(title);
        }
        if (type == 3){
            tbTittle.getTvTittle().setText(title);
        }
        if (type==4){
            tbTittle.getTvTittle().setText(title);
        }
        initDetailsH5(url);
    }

    /**
     * 启用硬件加速
     */
    private void initHardwareAccelerate() {
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                getActivity().getWindow().setFlags(
                        android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }
    }

    private void initX5WebView(String url) {

        webView.getSettings().setDisplayZoomControls(false);
            webView.setScrollbarFadingEnabled(false);
        webView.loadUrl(url);
    }
    /**
     * 初始化webView
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void initDetailsH5(String url) {

        webView.getSettings().setDisplayZoomControls(false);
        webView.setWebViewClient(new SimpleWebView.SimpleWebViewClient(){
            @Override
            public void onPageFinished(com.tencent.smtt.sdk.WebView webView, String url) {
                super.onPageFinished(webView, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView webView, String url) {
                webView.loadUrl(url);
               return true;
            }

        });

      /*  webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
                return super.onJsAlert(webView, s, s1, jsResult);
            }

            @Override
            public boolean onJsConfirm(WebView webView, String s, String s1, JsResult jsResult) {
                return super.onJsConfirm(webView, s, s1, jsResult);
            }
        });*/
        webView.loadUrl(url);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (webView != null) {
            webView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放资源
        if (webView != null)
            webView.clearCache(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (webView !=null)
           webView.onResume();
    }

    @Override
    public boolean onBackPressedSupport() {
        if (webView.canGoBack()){
            webView.goBack();
            return true;
        } else {
            if (webView != null) {
                webView.clearCache(true);
            }
            return super.onBackPressedSupport();
        }
    }
}
