package com.carsuper.coahr.mvp.view.myData.InvitesCourtesy;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.InvitesCourtesy.Fragment_InvitesCourtesy_contract;
import com.carsuper.coahr.mvp.presenter.myData.InvitesCourtesy.Fragment_InvitesCourtesy_Presenter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.utils.MD5;
import com.carsuper.coahr.utils.PreferenceUtils;
import com.carsuper.coahr.utils.QRCode.QRCode;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.carsuper.coahr.wxapi.WXShareUtils;
import com.socks.library.KLog;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import javax.inject.Inject;

import butterknife.BindView;

public class Fragment_InvitesCourtesy extends BaseFragment<Fragment_InvitesCourtesy_contract.Presenter> implements Fragment_InvitesCourtesy_contract.View, View.OnClickListener {

    @Inject
    Fragment_InvitesCourtesy_Presenter presenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tittleBar;
    @BindView(R.id.iv_invitation)
    ImageView iv_invitation;
    @BindView(R.id.tv_wechat)
    TextView tv_wechat;
    @BindView(R.id.tv_qr_code)
    TextView tv_qr_code;
    @BindView(R.id.tv_circle_friends)
    TextView tv_circle_friends;
    /**
     * 推广链接/Home/index/shear?inviter_id={$uid}&inviter_token={$inviter_token}
     * $inviter_token=md5('inviter_id={$uid}&app_name=carsuper')
     */
    private String Referral_Link="Home/index/share";


    private String app_name="app_name=carsuper";


    private StringBuilder stringBuilder;
    private int shareType;
    
    ImageView imageView_dialog;
    
    public static Fragment_InvitesCourtesy newInstance() {
        Fragment_InvitesCourtesy fragment = new Fragment_InvitesCourtesy();
        Bundle arg = new Bundle();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public Fragment_InvitesCourtesy_contract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_invitation;
    }

    @Override
    public void initView() {
        tittleBar.setOnClickListener(this);
        tv_wechat.setOnClickListener(this);
        tv_circle_friends.setOnClickListener(this);
        tv_qr_code.setOnClickListener(this);

    }

    @Override
    public void initData() {
        String uid = PreferenceUtils.getPrefString(_mActivity, "uid", "");
        stringBuilder=new StringBuilder();
        String s = MD5.Md5String("inviter_id="+uid + "&" + app_name);
         stringBuilder.append(Constants.BASE_URL).append(Referral_Link).append("?").append("inviter_id=").append(uid).append("&inviter_token=").append(s);
        KLog.d("分享链接",stringBuilder.toString());
    }

    @Override
    public void onClick(View view) {
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.launch_logo);
        switch (view.getId()) {
            case R.id.tv_wechat: //微信好友
                if (WXShareUtils.getInstance(BaseApplication.mContext).isWXAppInstalled()){

                    shareType = SendMessageToWX.Req.WXSceneSession;
                    WXShareUtils.getInstance(BaseApplication.mContext).shareUrl(stringBuilder.toString(), "这个APP的新用户待遇竟然这么棒！", thumb, "您的朋友邀请您去领取优惠券，更多福利等你来拿！", SendMessageToWX.Req.WXSceneSession);
                } else {
                    Toast.makeText(BaseApplication.mContext,"请安装微信后再试",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_qr_code: //二维码
                getDialog(null);
                Bitmap qrCodeAddLogoImage = QRCode.getQRCodeAddLogoImage(stringBuilder.toString(), 500, 500, R.mipmap.launch_logo);
                Imageloader.loadBitMap(qrCodeAddLogoImage, imageView_dialog);
                /**
                 * 也可以用下面的
                 */
                // EwmShareDialog ewmShareDialog=EwmShareDialog.newInstance(null);
                //  ewmShareDialog.show(_mActivity.getSupportFragmentManager(),null);
                break;
            case R.id.tv_circle_friends://朋友圈
                if (WXShareUtils.getInstance(BaseApplication.mContext).isWXAppInstalled()) {
                    shareType = SendMessageToWX.Req.WXSceneTimeline;
                    WXShareUtils.getInstance(BaseApplication.mContext).shareUrl(stringBuilder.toString(), "您的朋友邀请您去领取优惠券，更多福利等你来拿！", thumb, "您的朋友邀请您去领取优惠券，更多福利等你来拿！", SendMessageToWX.Req.WXSceneTimeline);
                } else {

                    Toast.makeText(BaseApplication.mContext, "请安装微信后再试", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tb_tittle:
                _mActivity.onBackPressed();
        }
    }

    @Override
    public void OnRequestMainSuccess() {

    }

    @Override
    public void OnRequestMainFailure(String failure) {

    }

    @Override
    public void OnRequestShareWXDataSuccess() {

    }

    @Override
    public void OnRequestShareWXDataFailure(String failure) {

    }

    @Override
    public void OnRequestEwmSuccess() {

    }

    @Override
    public void OnRequestEwmFailure(String failure) {

    }

    private void getDialog(String path) {
        final Dialog dialog = new Dialog(getActivity(), R.style.edit_AlertDialog_style);
        dialog.setContentView(R.layout.fragment_dialog_item);
        imageView_dialog = dialog.findViewById(R.id.iv_ewm);
        // Imageloader.loadImage(path,imageView_dialog);
        dialog.setCanceledOnTouchOutside(true);
        Window w = dialog.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = 100;
        dialog.onWindowAttributesChanged(lp);
        w.setWindowAnimations(R.style.bottom_in_out_animation);
       /* imageView_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });*/
        dialog.show();
    }

    /**
     * 本地图片地址
     *
     * @param context
     * @param id
     * @return
     */
    public Uri getUriFromDrawableRes(Context context, int id) {
        Resources resources = context.getResources();
        String path = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + resources.getResourcePackageName(id) + "/"
                + resources.getResourceTypeName(id) + "/"
                + resources.getResourceEntryName(id);
        return Uri.parse(path);

    }
}
