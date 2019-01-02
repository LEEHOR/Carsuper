package com.carsuper.coahr.mvp.view.myData;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.ToEvaluateContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveCommentBean;
import com.carsuper.coahr.mvp.presenter.myData.ToEvaluatePresenter;
import com.carsuper.coahr.mvp.view.adapter.OpinionIMGAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.utils.Permission.OnRequestPermissionListener;
import com.carsuper.coahr.utils.Permission.RequestPermissionUtils;
import com.carsuper.coahr.widgets.AnFQNumEditText;
import com.carsuper.coahr.widgets.StarBar;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ToEvaluateFragment extends BaseFragment<ToEvaluateContract.Presenter> implements ToEvaluateContract.View {

    @Inject
    ToEvaluatePresenter toEvaluatePresenter;


    PhotoSelectDialogFragment dialogFragment = PhotoSelectDialogFragment.newInstance();
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.sb_evaluate)
    StarBar sbEvaluate;
    @BindView(R.id.tv_feel)
    TextView tvFeel;
    @BindView(R.id.et_opinion)
    AnFQNumEditText etOpinion;
    @BindView(R.id.iv_go_photo)
    ImageView ivGoPhoto;
    @BindView(R.id.rv_opinion_img)
    RecyclerView rvOpinionImg;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.ck_is_anonymous)
    CheckBox ckIsAnonymous;


    private LinearLayoutManager linearLayoutManager;
    private OpinionIMGAdapter opinionIMGAdapter;
    private static final int maxImg = 3;//最多图片数量

    public static  final String TYPE_COMMODITY =  "1";
    public static  final String TYPE_SERVICE =  "2";

    private String type;//commodity  1；staition  2
    private String order_id;
    private List<Uri> uris = new ArrayList<>();//图片地址数组

    public static ToEvaluateFragment newInstance(String order_id,String type) {
        ToEvaluateFragment fragment = new ToEvaluateFragment();
        Bundle arg = new Bundle();
        arg.putString("order_id", order_id);
        arg.putString("type",type);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public ToEvaluateContract.Presenter getPresenter() {
        return toEvaluatePresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_to_evaluate;
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
                map.put("token", RequestBody.create(null,Constants.token));
                map.put("comment", RequestBody.create(null,etOpinion.getText()));
                map.put("order_id",RequestBody.create(null,order_id));
                map.put("level_score",RequestBody.create(null,sbEvaluate.getStarMark()>0?sbEvaluate.getStarMark()+"":"1"));
                map.put("is_anonymous",RequestBody.create(null,ckIsAnonymous.isChecked()?"1":"0"));
                map.put("type",RequestBody.create(null,type));
                getPresenter().saveCommodityComment(map, uris);

            }
        });
        ivGoPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.show(_mActivity.getSupportFragmentManager(),TAG);
            }
        });
        dialogFragment.setOnPhotoItemSelectListener(new PhotoSelectDialogFragment.ItemSelectListener() {
            @Override
            public void onItemSelct(String selectStyle) {
                if (selectStyle.equals("album")) {
                    getAlbumPermission();
                   // Intent intent = new Intent(Intent.ACTION_PICK);
                   // intent.setType("image/*");// 相片类型
                  //  startActivityForResult(intent, Constants.PHOTO_ALBUM);
                } else if (selectStyle.equals("takephoto")) {
                    getPhotoPermission();
                   // Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                   // startActivityForResult(intent, Constants.PHOTO_CAMERA);
                }
            }
        });

        //文字评论
        etOpinion.setLength(240)//设置总字数
                //TextView显示类型(SINGULAR单数类型)(PERCENTAGE百分比类型)
                .setType(AnFQNumEditText.PERCENTAGE)
                .show();
        etOpinion.setOnTextInputListener(new AnFQNumEditText.OnTextInputListener() {
            @Override
            public void onInput(String s) {
                if (!TextUtils.isEmpty(s)) {
                   btSubmit.setEnabled(true);
                   btSubmit.setBackgroundColor(getResources().getColor(R.color.material_blue_500));
                } else {
                    btSubmit.setEnabled(false);
                    btSubmit.setBackgroundColor(getResources().getColor(R.color.material_grey_500));
                }
            }
        });


        //点星
        sbEvaluate.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                int intMark = (int) mark;
                switch (intMark){
                    case 1:
                        tvFeel.setText("极差");
                        break;
                    case 2:
                        tvFeel.setText("差");
                        break;
                    case 3:
                        tvFeel.setText("一般");
                        break;
                    case 4:
                        tvFeel.setText("满意");
                        break;
                    case 5:
                        tvFeel.setText("非常满意");
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        order_id =getArguments().getString("order_id");
        type = getArguments().getString("type");
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext,LinearLayoutManager.HORIZONTAL,false);
        opinionIMGAdapter = new OpinionIMGAdapter();
        rvOpinionImg.setLayoutManager(linearLayoutManager);
        rvOpinionImg.setAdapter(opinionIMGAdapter);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == Constants.PHOTO_ALBUM) {
                Uri uri = data.getData(); //得到图片 uri
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
    public void onSaveCommentSuccess(SaveCommentBean resultBean) {

        start(ThankForEvaluateFragment.newInstance(order_id,type,resultBean.getJdata().getId()));
    }

    @Override
    public void onSaveCommentFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

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
