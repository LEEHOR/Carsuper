package com.carsuper.coahr.mvp.view.myData.returnorchange;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.returnorchange.ApplyReturnChangeContract;
import com.carsuper.coahr.mvp.model.bean.RefundFormBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.presenter.myData.returnorchange.ApplyReturnChangePresenter;
import com.carsuper.coahr.mvp.view.adapter.OpinionIMGAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.myData.PhotoSelectDialogFragment;
import com.carsuper.coahr.utils.ImageUtil;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.AnFQNumEditText;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.socks.library.KLog;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import anet.channel.util.StringUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/8/28.
 * Email：hengzwdhengzwd@qq.com
 */
public class ApplyReturnChangeFragment extends BaseFragment<ApplyReturnChangeContract.Presenter> implements ApplyReturnChangeContract.View {


    @Inject
    ApplyReturnChangePresenter applyReturnChangePresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.iv_commodity_img)
    ImageView ivCommodityImg;
    @BindView(R.id.tv_commodity_info)
    TextView tvCommodityInfo;
    @BindView(R.id.tv_commodity_price)
    TextView tvCommodityPrice;
    @BindView(R.id.tv_payment_count)
    TextView tvPaymentCount;
    @BindView(R.id.rl_commodity)
    RelativeLayout rlCommodity;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_goods_status)
    EditText tvGoodsStatus;
    @BindView(R.id.iv_go_edit_status)
    ImageView ivGoEditStatus;
    @BindView(R.id.ll_goods_status)
    LinearLayout llGoodsStatus;
    @BindView(R.id.tv_reason_tittle)
    TextView tvReasonTittle;
    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.iv_go_edit_reason)
    ImageView ivGoEditReason;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.ll_money)
    LinearLayout llMoney;
    @BindView(R.id.tv_receiver)
    TextView tvReceiver;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_recieve_address)
    TextView tvRecieveAddress;
    @BindView(R.id.et_discription)
    AnFQNumEditText etDiscription;
    @BindView(R.id.iv_go_photo)
    ImageView ivGoPhoto;
    @BindView(R.id.rv_opinion_img)
    RecyclerView rvOpinionImg;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.rl_reciever)
    RelativeLayout rlReciever;

    private String order_id ="";

    private int refund_type;

    private String ua_id = "";
    private String order_status ="";



    PhotoSelectDialogFragment dialogFragment = PhotoSelectDialogFragment.newInstance();
    private LinearLayoutManager linearLayoutManager;
    private OpinionIMGAdapter opinionIMGAdapter;
    private static final int maxImg = 3;//最多图片数量

    private List<Uri> uris = new ArrayList<>();//图片地址数组

    public static ApplyReturnChangeFragment newInstance(String order_id,int type,String order_status){
        ApplyReturnChangeFragment fragment  = new ApplyReturnChangeFragment();
        Bundle arg = new Bundle();
        arg.putString("order_id",order_id);
        arg.putInt("type",type);
        arg.putString("order_status",order_status);
        fragment.setArguments(arg);
        return  fragment;
    }

    @Override
    public ApplyReturnChangeContract.Presenter getPresenter() {
        return applyReturnChangePresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_apply_returnchange;
    }

    @Override
    public void initView() {
        refund_type=getArguments().getInt("type");
        switch (refund_type) {
            case 1:
                tbTittle.getTvTittle().setText("申请退款");
                rlReciever.setVisibility(View.GONE);
                tvReasonTittle.setText("退款原因");
                etDiscription.setEtHint("请输入退款说明");
                break;
            case 2:
                tbTittle.getTvTittle().setText("申请退货");
                rlReciever.setVisibility(View.GONE);
                tvReasonTittle.setText("退货原因");
                llGoodsStatus.setVisibility(View.GONE);
                etDiscription.setEtHint("请输入退货说明");

                break;
            case 3:
                tbTittle.getTvTittle().setText("申请换货");
                llGoodsStatus.setVisibility(View.GONE);
                tvReasonTittle.setText("换货原因");
                llMoney.setVisibility(View.GONE);
                etDiscription.setEtHint("请输入换货说明");
                break;
        }
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = new HashMap();
                map.put("token", RequestBody.create(null,Constants.token));
                map.put("order_id",RequestBody.create(null,order_id));
                map.put("refund_type",RequestBody.create(null,refund_type+""));
                map.put("order_status", RequestBody.create(null,order_status));

                map.put("ua_id", RequestBody.create(null, TextUtils.isEmpty(ua_id)?"":ua_id));
                map.put("reason", RequestBody.create(null,tvReason.getText().toString()));
                map.put("description", RequestBody.create(null, etDiscription.getText()));

                getPresenter().saveRefund(map,uris);
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
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");// 相片类型
                    startActivityForResult(intent, Constants.PHOTO_ALBUM);
                } else if (selectStyle.equals("takephoto")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, Constants.PHOTO_CAMERA);
                }
            }
        });
        etDiscription.setLength(240)//设置总字数
                //TextView显示类型(SINGULAR单数类型)(PERCENTAGE百分比类型)
                .setType(AnFQNumEditText.PERCENTAGE)
                .show();

        ivGoEditReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startForResult(ReasonFragment.newInstance(refund_type),1);
            }
        });
        ivGoEditStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startForResult(GoodsStatusFragment.newInstance(),2);
            }
        });
    }

    @Override
    public void initData() {

        order_id = getArguments().getString("order_id");
//        order_status = getArguments().getString("order_status");
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext,LinearLayoutManager.HORIZONTAL,false);
        opinionIMGAdapter = new OpinionIMGAdapter();
        rvOpinionImg.setLayoutManager(linearLayoutManager);
        rvOpinionImg.setAdapter(opinionIMGAdapter);
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_id",order_id);
//        map.put("order_id","2018082118154533914378");
        map.put("refund_type",refund_type+"");
        getPresenter().refundForm(map);

    }



    @Override
    public void onRefundFormSuccess(RefundFormBean bean) {
        tvCommodityInfo.setText(bean.getJdata().getCommodity().getC_name());
        tvCommodityPrice.setText("￥"+bean.getJdata().getCommodity().getC_price());
        tvPaymentCount.setText("x"+bean.getJdata().getCommodity().getC_num());
        Imageloader.loadImage(bean.getJdata().getCommodity().getC_thumbnail(),ivCommodityImg);

        tvReceiver.setText(bean.getJdata().getAddress().getUsername());
        tvRecieveAddress.setText(bean.getJdata().getAddress().getAddress());
        tvPhoneNumber.setText(bean.getJdata().getAddress().getTelephone());

        tvOrderNumber.setText(bean.getJdata().getCommodity().getO_id());
        if (bean.getJdata().getRefund() != null) {
            tvMoney.setText("￥"+bean.getJdata().getRefund());
        }
        refund_type = Integer.parseInt(bean.getJdata().getRefund_type());
        ua_id = bean.getJdata().getAddress().getId();
    }

    @Override
    public void onRefundFormFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSaveRefundSuccess(ResultBean resultBean) {
        if (resultBean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext,resultBean.getJdata().getJmsg(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(BaseApplication.mContext,resultBean.getMsg(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSaveRefundFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == 1&&requestCode==1) {
                tvReason.setText(data.getString("reason"));
            }

            if (resultCode ==2&&requestCode==2) {
                String status = data.getString("status");
                if (status.equals("货已收到")) {
                    order_status = "2";
                }else if (status.equals("货未收到")){
                    order_status = "1";
                }
                tvGoodsStatus.setText(data.getString("status"));
            }
        }
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
                if (uris.size()<3) {
                    uris.add(uri);
                    opinionIMGAdapter.setNewData(uris);
                }else {
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
                if (uris.size()<3) {
                    uris.add(uri);
                    opinionIMGAdapter.setNewData(uris);
                }else {
                    Toast.makeText(BaseApplication.mContext, "图片不能超过3张", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
