package com.carsuper.coahr.mvp.view.myData.setting;


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
import com.carsuper.coahr.widgets.x5web.X5WebViewByMyShelf;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;

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
    X5WebViewByMyShelf webView;

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
                    if (webView !=null){
                        webView.destroy();
                    }
                _mActivity.onBackPressed();
            }
        });
        initHardwareAccelerate();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (NavigationBarUtils.hasNavigationBarFun(_mActivity)){
            if (NavigationBarUtils.isNavigationBarShow(_mActivity)){
                NavigationBarUtils.hideBottomUIMenu(_mActivity);
            }
        }
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
        initX5WebView(url);
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
        IX5WebViewExtension ix5 = webView.getX5WebViewExtension();
        if (null != ix5) {
            ix5.setScrollBarFadingEnabled(false);
        }
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
            webView.destroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (webView !=null)
           webView.onResume();
    }
}
