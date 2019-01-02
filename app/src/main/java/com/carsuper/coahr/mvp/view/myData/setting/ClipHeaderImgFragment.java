package com.carsuper.coahr.mvp.view.myData.setting;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.setting.ClipHeaderImgContract;
import com.carsuper.coahr.mvp.model.bean.SaveUserInfoBean;
import com.carsuper.coahr.mvp.presenter.myData.setting.ClipHeaderImgPresenter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.utils.BitmapUtils;
import com.carsuper.coahr.utils.ImageUtil;
import com.carsuper.coahr.utils.PreferenceUtils;
import com.carsuper.coahr.widgets.ClipImage.ClipViewLayout;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.socks.library.KLog;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ClipHeaderImgFragment extends BaseFragment<ClipHeaderImgContract.Presenter> implements ClipHeaderImgContract.View,View.OnClickListener {
    @Inject
    ClipHeaderImgPresenter clipHeaderImgPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.clip_layout)
    ClipViewLayout clipLayout;
    @BindView(R.id.clip_btn_cancel)
    TextView clipBtnCancel;
    @BindView(R.id.clip_btn_ok)
    TextView clipBtnOk;


    private Bitmap bitmap;

    public static final String IMAGE_PATH = "path";


    public static ClipHeaderImgFragment newInstance(Uri uri){
        ClipHeaderImgFragment fragment = new ClipHeaderImgFragment();
        Bundle arg = new Bundle();
        arg.putParcelable("photoUri",uri);
        fragment.setArguments(arg);
        return fragment;
    }
    @Override
    public ClipHeaderImgContract.Presenter getPresenter() {
        return clipHeaderImgPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_clip_image;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        clipBtnCancel.setOnClickListener(this);
        clipBtnOk.setOnClickListener(this);

    }

    @Override
    public void initData() {

       clipLayout.setImageSrc((Uri) getArguments().get("photoUri"));

    }


    @Override
    public void onUpdateSuccess(SaveUserInfoBean bean) {
        Bundle data = new Bundle();
        Constants.touxiang = bean.getJdata().getUserheadimg();
        KLog.e("touxiang:" + Constants.touxiang);
        PreferenceUtils.setPrefString(BaseApplication.mContext, "touxiang", Constants.touxiang);
        _mActivity.onBackPressed();
    }

    @Override
    public void onUpdateFailue(String throwable) {
        Toast.makeText(_mActivity, throwable, Toast.LENGTH_SHORT).show();

    }

    /**
     * 生成Uri并且通过setResult返回给打开的activity
     */
    private void generateUriAndUpload() {
        //调用返回剪切图
        bitmap = clipLayout.clip();
        if (bitmap == null) {
            Toast.makeText(_mActivity, "图片剪切失败", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(Constants.SAVE_DIR_ICON);
        if (!file.exists())
            file.mkdirs();

        int size = bitmap.getByteCount()/1024;
        bitmap = BitmapUtils.ImageCompress(bitmap,128);
        String imgName = "header" + System.currentTimeMillis() + ".jpg";
        ImageUtil.saveBitmapToSDCard(bitmap, Constants.SAVE_DIR_ICON, imgName);
        File file1 = new File(Constants.SAVE_DIR_ICON.concat(imgName));
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("pic1", file1.getName(), requestFile);

        Map map = new HashMap();
        map.put("token",RequestBody.create(null,Constants.token));
        getPresenter().updateHeadPic(map,body);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clip_btn_cancel:
                _mActivity.onBackPressed();
                break;
            case R.id.clip_btn_ok:
                generateUriAndUpload();
                break;
        }
    }

}
