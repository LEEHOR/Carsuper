package com.carsuper.coahr.mvp.view.myData;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.OpinionContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.presenter.myData.OpinionPresenter;
import com.carsuper.coahr.mvp.view.adapter.OpinionIMGAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.utils.ImageUtil;
import com.carsuper.coahr.utils.Permission.OnRequestPermissionListener;
import com.carsuper.coahr.utils.Permission.RequestPermissionUtils;
import com.carsuper.coahr.widgets.AnFQNumEditText;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.socks.library.KLog;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class OpinionFragment extends BaseFragment<OpinionContract.Presenter> implements OpinionContract.View {

    @Inject
    OpinionPresenter opinionPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.et_opinion)
    AnFQNumEditText etOpinion;
    @BindView(R.id.rv_opinion_img)
    RecyclerView rvOpinionImg;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.iv_go_photo)
    ImageView ivGoPhoto;


    PhotoSelectDialogFragment dialogFragment = PhotoSelectDialogFragment.newInstance();
    private LinearLayoutManager linearLayoutManager;
    private OpinionIMGAdapter opinionIMGAdapter;
    private static final int maxImg = 3;//最多图片数量

    private List<Uri> uris = new ArrayList<>();//图片地址数组

    public static OpinionFragment newInstance() {
        return new OpinionFragment();
    }

    @Override
    public OpinionContract.Presenter getPresenter() {
        return opinionPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_opinion;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = new HashMap();
                map.put("token", Constants.token);
                map.put("suggest", etOpinion.getText());
                getPresenter().saveSuggest(map, uris);
            }
        });
        ivGoPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.show(_mActivity.getSupportFragmentManager(), TAG);
            }
        });
        dialogFragment.setOnPhotoItemSelectListener(new PhotoSelectDialogFragment.ItemSelectListener() {
            @Override
            public void onItemSelct(String selectStyle) {
                if (selectStyle.equals("album")) {
                    getAlbumPermission();
                 //   Intent intent = new Intent(Intent.ACTION_PICK);
                  //  intent.setType("image/*");// 相片类型
                  //  startActivityForResult(intent, Constants.PHOTO_ALBUM);
                } else if (selectStyle.equals("takephoto")) {
                    getPhotoPermission();
                  //  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                   // startActivityForResult(intent, Constants.PHOTO_CAMERA);
                }
            }
        });
        etOpinion.setLength(240)//设置总字数
                //TextView显示类型(SINGULAR单数类型)(PERCENTAGE百分比类型)
                .setType(AnFQNumEditText.PERCENTAGE)
                .show();
        etOpinion.setOnTextInputListener(new AnFQNumEditText.OnTextInputListener() {
            @Override
            public void onInput(String s) {
                if (s != null && !s.equals("") ) {
                    btSubmit.setClickable(true);
                    btSubmit.setBackgroundColor(getResources().getColor(R.color.material_blue_500));
                } else {
                    btSubmit.setClickable(false);
                    btSubmit.setBackgroundColor(getResources().getColor(R.color.material_grey_500));
                }
            }
        });

    }

    @Override
    public void initData() {
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext, LinearLayoutManager.HORIZONTAL, false);
        opinionIMGAdapter = new OpinionIMGAdapter();
        rvOpinionImg.setLayoutManager(linearLayoutManager);
        rvOpinionImg.setAdapter(opinionIMGAdapter);

    }


    @Override
    public void onSaveSuccess(ResultBean resultBean) {
        if (resultBean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext, resultBean.getJdata().getJmsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BaseApplication.mContext, resultBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == Constants.PHOTO_ALBUM) {
                Uri uri = data.getData(); //得到图片 uri
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(_mActivity.getContentResolver(), uri);
                    ImageUtil.ImageCompress(bitmap);
                    uri = Uri.parse(MediaStore.Images.Media.insertImage(_mActivity.getContentResolver(), bitmap, null, null));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (uris.size() < 3) {
                    uris.add(uri);
                    opinionIMGAdapter.setNewData(uris);
                } else {
                    Toast.makeText(BaseApplication.mContext, "图片不能超过3张", Toast.LENGTH_LONG).show();
                }
            } else if (requestCode == Constants.PHOTO_CAMERA) {
                // 判断内存卡是否可用
                String sdStatus = Environment.getExternalStorageState();
                if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
                    KLog.e("SD卡不可用");
                    Toast.makeText(BaseApplication.mContext, "SD卡不可用！", Toast.LENGTH_LONG).show();
                    return;
                }
                //对 返回的 bitmap 进行对应的保存操作
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                ImageUtil.ImageCompress(bitmap);
                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(_mActivity.getContentResolver(), bitmap, null, null));
                if (uris.size() < 3) {
                    uris.add(uri);
                    opinionIMGAdapter.setNewData(uris);
                } else {
                    Toast.makeText(BaseApplication.mContext, "图片不能超过3张", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
    }

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
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, Constants.PHOTO_CAMERA);
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
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");// 相片类型
        startActivityForResult(intent, Constants.PHOTO_ALBUM);
    }
}
