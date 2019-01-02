package com.carsuper.coahr.mvp.view.myData.setting;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.setting.UserInfoContract;
import com.carsuper.coahr.mvp.model.bean.LoginBean;
import com.carsuper.coahr.mvp.model.bean.PersonInfoBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.presenter.myData.setting.UserInfoPresenter;
import com.carsuper.coahr.mvp.view.LaunchActivity;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.myData.PhotoSelectDialogFragment;
import com.carsuper.coahr.utils.Permission.OnRequestPermissionListener;
import com.carsuper.coahr.utils.Permission.RequestPermissionUtils;
import com.carsuper.coahr.utils.PreferenceUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.CircleImageView;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.socks.library.KLog;
import com.umeng.message.PushAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.carsuper.coahr.mvp.view.myData.setting.ClipHeaderImgFragment.IMAGE_PATH;

/**
 * Author： hengzwd on 2018/8/17.
 * Email：hengzwdhengzwd@qq.com
 */
public class UserInfoFragment extends BaseFragment<UserInfoContract.Presenter> implements UserInfoContract.View, View.OnClickListener {

    @Inject
    UserInfoPresenter userInfoPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.iv_head_pic)
    ImageView ivHeadPic;
    @BindView(R.id.rl_headpic)
    RelativeLayout rlHeadpic;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv_mobile_number)
    TextView tvMobileNumber;
    @BindView(R.id.rl_mobile)
    RelativeLayout rlMobile;
    @BindView(R.id.tv_weixin)
    TextView tvWeixin;
    @BindView(R.id.rl_weixin)
    RelativeLayout rlWeixin;
    Unbinder unbinder;
    //图片来源

    public static final int PHOTO_CAMERA = 0;
    public static final int PHOTO_ALBUM = 1;

    public static final int SELECT_COMPELET = 2;

    private String bind_wx_status;
    private String wxid;
    private String openid;

    private Uri photoUri;

    public static UserInfoFragment newInstance() {

        return new UserInfoFragment();
    }

    @Override
    public UserInfoContract.Presenter getPresenter() {
        return userInfoPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_userinfo;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(this);
        rlHeadpic.setOnClickListener(this);
        rlMobile.setOnClickListener(this);
        rlName.setOnClickListener(this);
        rlWeixin.setOnClickListener(this);

    }

    private void getUserInfo() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("uid", Constants.uid + "");
        getPresenter().getUserInfo(map);
    }

    @Override
    public void initData() {
        getUserInfo();
    }


    @Override
    public void onGetInfoSuccess(PersonInfoBean personInfoBean) {
        tvName.setText(personInfoBean.getJdata().getUser().getNickname());
        tvMobileNumber.setText(personInfoBean.getJdata().getUser().getPhone());
        Constants.touxiang=personInfoBean.getJdata().getUser().getUserheadimg();
        Constants.nickname=personInfoBean.getJdata().getUser().getNickname();
        Imageloader.loadCircularImage(personInfoBean.getJdata().getUser().getUserheadimg(), ivHeadPic);
       // Imageloader.loadImage(personInfoBean.getJdata().getUser().getUserheadimg(), ivHeadPic);
        bind_wx_status = personInfoBean.getJdata().getUser().getStatus();
        wxid = personInfoBean.getJdata().getUser().getWxid();
        openid = personInfoBean.getJdata().getUser().getOpenid();
        if ((wxid != null && !wxid.equals("")) && (openid != null && !openid.equals(""))) {
            tvWeixin.setText("已绑定");
        } else {
            tvWeixin.setText("未绑定");
        }
    }

    @Override
    public void onGetInfoFailure(String throwle) {
        Toast.makeText(_mActivity, "获取个人信息失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUnsetWxSuccess(ResultBean resultBean) {
        if (resultBean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext, resultBean.getJdata().getJmsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BaseApplication.mContext, resultBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
        getUserInfo();

    }

    @Override
    public void onUnsetWxFailure(String failure) {

        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBindWxSuccess(ResultBean bean) {
       /* PreferenceUtils.setPrefString(_mActivity, "phone", bean.getJdata().getPhone());
        PreferenceUtils.setPrefString(_mActivity, "token", bean.getJdata().getToken());
        PreferenceUtils.setPrefString(_mActivity, "uid", bean.getJdata().getUid() + "");
        Constants.phone = bean.getJdata().getPhone();
        Constants.token = bean.getJdata().getToken();
        Constants.uid = bean.getJdata().getUid() + "";*/
           Toast.makeText(_mActivity, bean.getMsg(), Toast.LENGTH_LONG).show();
        Toast.makeText(_mActivity, bean.getJdata().getJmsg(), Toast.LENGTH_LONG).show();
        getUserInfo();

    }

    @Override
    public void onBindWxFailure(String failure) {
        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();

    }

    private void showPick() {
        PhotoSelectDialogFragment fragment = new PhotoSelectDialogFragment();
        fragment.setOnPhotoItemSelectListener(new PhotoSelectDialogFragment.ItemSelectListener() {
            @Override
            public void onItemSelct(String selectStyle) {
                if (selectStyle.equals("album")) {
                        getAlbumPermission();
                } else if (selectStyle.equals("takephoto")) {
                    getPhotoPermission();
                }
            }
        });
        fragment.show(_mActivity.getSupportFragmentManager(), TAG);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getUserInfo();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        KLog.e("onActivityResult");
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case PHOTO_CAMERA:
            case PHOTO_ALBUM:
                if (requestCode == PHOTO_ALBUM && data != null) {
                    photoUri = data.getData();
                }
                startForResult(ClipHeaderImgFragment.newInstance(photoUri), SELECT_COMPELET);
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                _mActivity.onBackPressed();
                break;
            case R.id.rl_headpic:
                showPick();
                break;
            case R.id.rl_name:
                start(NameFragment.newInstance());
                break;
            case R.id.rl_mobile:
                start(PhoneNumberFragment.newInstance("我的设置"));
                break;
            case R.id.rl_weixin:
                if ((wxid != null && !wxid.equals("")) && (openid != null && !openid.equals(""))) {
                    new MaterialDialog.Builder(_mActivity)
                            .title("提示")
                            .content("您确认解除当前微信绑定吗")
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
                            map.put("uid", Constants.uid);
                            //getPresenter().unSetWx(map);
                        }
                    }).build();
                } else {
                    UMShareAPI mShareAPI = UMShareAPI.get(_mActivity);
                    mShareAPI.getPlatformInfo(_mActivity, SHARE_MEDIA.WEIXIN, umAuthListener);
                }
                break;
        }
    }


    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            KLog.e("onstart");
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, final Map<String, String> data) {
            for (Entry<String, String> entry : data.entrySet()) {

                KLog.d("微信登录","key= " + entry.getKey() + " and value= " + entry.getValue());
                       }
            KLog.e("onComplete");
            if (SHARE_MEDIA.WEIXIN.equals(platform)) {
                if (TextUtils.isEmpty(Constants.devicestoken)) {
                    Constants.devicestoken = PushAgent.getInstance(_mActivity).getRegistrationId();
                }
                PreferenceUtils.setPrefString(_mActivity, "devicetoken", Constants.devicestoken);
                new MaterialDialog.Builder(_mActivity)
                        .title("提示")
                        .content("您确定使用当前的微信登录吗")
                        .iconRes(R.mipmap.wxzf)
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
                        map.put("openid", data.get("openid"));
                        map.put("uid", Constants.uid);
                        map.put("unionid",data.get("unionid"));
                        map.put("nickname", data.get("screen_name"));
                        map.put("headimgurl", data.get("profile_image_url"));
                        map.put("token", Constants.token);
                        KLog.d("微信",map.get("openid"),map.get("uid"),map.get("unionid"),map.get("nickname"),map.get("headimgurl"),map.get("token"));
                        getPresenter().bindWx(map);
                    }
                }).build().show();


            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            KLog.e("shareonError:" + t.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            KLog.e("shareononCancel");

        }
    };
    /**
     * 动态获取相机权限
     */
    private void getPhotoPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RequestPermissionUtils.requestPermission(getActivity(), new OnRequestPermissionListener() {
                @Override
                public void PermissionSuccess(List<String> permissions) {
                    TakePhotos();
                }

                @Override
                public void PermissionFail(List<String> permissions) {
                    Toast.makeText(getActivity(), "获取权限失败", Toast.LENGTH_LONG).show();
                }

                @Override
                public void PermissionHave() {
                    TakePhotos();
                }
            }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA);

        } else {
            TakePhotos();
        }
    }

    private void TakePhotos(){
        // 执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// "android.media.action.IMAGE_CAPTURE"
            /**
             * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
             * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
             */
            ContentValues values = new ContentValues();
            photoUri = _mActivity.getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, PHOTO_CAMERA);
        } else {
            Toast.makeText(_mActivity, "内存卡不存在", Toast.LENGTH_LONG).show();
        }
    }
    /**
     * 动态获取相册权限
     */
    private void getAlbumPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RequestPermissionUtils.requestPermission(getActivity(), new OnRequestPermissionListener() {
                @Override
                public void PermissionSuccess(List<String> permissions) {
                    TakePic();
                }

                @Override
                public void PermissionFail(List<String> permissions) {
                    Toast.makeText(getActivity(), "获取权限失败", Toast.LENGTH_LONG).show();
                }

                @Override
                public void PermissionHave() {
                    TakePic();
                }
            }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA);

        } else {
            TakePic();
        }
    }
    private void TakePic(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), PHOTO_ALBUM);
    }
}
