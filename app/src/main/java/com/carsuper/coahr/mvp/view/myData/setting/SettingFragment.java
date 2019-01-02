package com.carsuper.coahr.mvp.view.myData.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.setting.SettingContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.presenter.myData.setting.SettingPresenter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.utils.PreferenceUtils;
import com.carsuper.coahr.widgets.FileUtils;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.umeng.message.common.Const;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class SettingFragment extends BaseFragment<SettingContract.Presenter> implements SettingContract.View {
    @Inject
    SettingPresenter settingPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.rl_personal_setting)
    RelativeLayout rlPersonalSetting;
    @BindView(R.id.switch_box)
    Switch switchBox;
    @BindView(R.id.rl_push_toggle)
    RelativeLayout rlPushToggle;
    @BindView(R.id.rl_score)
    RelativeLayout rlScore;
    @BindView(R.id.rl_clearcache)
    RelativeLayout rlClearcache;
    @BindView(R.id.rl_joinus)
    RelativeLayout rlJoinus;
    @BindView(R.id.rl_about_ksuper)
    RelativeLayout rlAboutKsuper;
    @BindView(R.id.tv_loginout)
    TextView tvLoginout;
    @BindView(R.id.tv_cache)
    TextView tvCache;

    public static SettingFragment newInstance(){
        return new SettingFragment();
    }

    @Override
    public SettingContract.Presenter getPresenter() {
        return settingPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initView() {
        switchBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void initData() {
        setCache();

    }




    @OnClick({R.id.tb_tittle, R.id.rl_personal_setting, R.id.rl_push_toggle, R.id.rl_score, R.id.rl_clearcache, R.id.rl_joinus, R.id.rl_about_ksuper, R.id.tv_loginout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                _mActivity.onBackPressed();
                break;
            case R.id.rl_personal_setting:
                if (haslogin()){
                    start(UserInfoFragment.newInstance());
                } else {
                    Toast.makeText(_mActivity, "当前未登录", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.rl_push_toggle:
                break;
            case R.id.rl_score:
                break;
            case R.id.rl_clearcache:
                if (FileUtils.deleteDir(new File(Constants.SAVE_DIR_BASE))) {
                    Toast.makeText(BaseApplication.mContext, getResources().getString(R.string.appset_toast_clear_cache_success), Toast.LENGTH_SHORT).show();
                    setCache();
                }
                break;
            case R.id.rl_joinus:
                //地址链接
                start(JoinUsFragment.newInstance(Constants.jionUsUrl,2,"招商加盟"));
                break;
            case R.id.rl_about_ksuper:
                start(AboutFragment.newInstance());

                break;
            case R.id.tv_loginout:
                if (haslogin()) {
                    new MaterialDialog.Builder(_mActivity)
                            .title("提示")
                            .content("您确认退出当前用户吗")
                            .negativeText("取消")
                            .positiveText("确认")
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                }
                            }).onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            Map map = new HashMap();
                            map.put("token", Constants.token);
                            getPresenter().loginOut(map);
                        }
                    }).build().show();
                }  else {
                    Toast.makeText(_mActivity, "当前未登录", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void setCache() {
        int dirSize = (int) FileUtils.getFileDirSize(new File(
                Constants.SAVE_DIR_BASE));
        String fileSize = FileUtils.getFilesize(dirSize + "");
        tvCache.setText("当前缓存 " + fileSize);
    }
    @Override
    public void onLoginOutSuccess(ResultBean bean) {
        if (bean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext,bean.getJdata().getJmsg(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(BaseApplication.mContext,"退出登录",Toast.LENGTH_SHORT).show();
        }
        Constants.token = "";
        Constants.uid = "";
        PreferenceUtils.remove(BaseApplication.mContext,"token");
        PreferenceUtils.remove(BaseApplication.mContext,"uuid");

    }

    @Override
    public void onLoginOutFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();
    }
}
