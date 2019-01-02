package com.carsuper.coahr.mvp.view.myData;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.ShoppingCartContract;
import com.carsuper.coahr.mvp.model.bean.AlyPayResult;
import com.carsuper.coahr.mvp.model.bean.ConfirmOrderBean;
import com.carsuper.coahr.mvp.model.bean.DeleteCarBean;
import com.carsuper.coahr.mvp.model.bean.RecommendEntity;
import com.carsuper.coahr.mvp.model.bean.ShoppingCartBean;
import com.carsuper.coahr.mvp.presenter.myData.ShoppingCartPresenter;

import com.carsuper.coahr.mvp.view.adapter.shoppingCart.CommodityInCartAdapter;
import com.carsuper.coahr.mvp.view.adapter.shoppingCart.GuessYouLoveAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.shoppingMall.CommodityDetailFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.ConfirmCommodityOrderFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.PayTypeSelectDialogFragment;
import com.carsuper.coahr.utils.DensityUtils;
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

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ShoppingCartFragment extends BaseFragment<ShoppingCartContract.Presenter> implements ShoppingCartContract.View {


    @Inject
    ShoppingCartPresenter shoppingCartPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.rv_commodity)
    RecyclerView rvCommodity;
    @BindView(R.id.rv_guess_youlove)
    RecyclerView rvGuessYoulove;
    @BindView(R.id.tv_check)
    CheckBox tvCheck;
    @BindView(R.id.tv_settlement)
    TextView tvSettlement;
    @BindView(R.id.tv_totalprice)
    TextView tvTotalprice;
    @BindView(R.id.text0_he)
    TextView text0;
    @BindView(R.id.text1_push_money)
    TextView text1;

    private int currentPage = 0;
    private int pagelength = Integer.MAX_VALUE;

    private float totalPrice;
    private CommodityInCartAdapter commodityInCartAdapter;
    private LinearLayoutManager linearLayoutManager;

    private GuessYouLoveAdapter guessYouLoveAdapter;
    private GridLayoutManager gridLayoutManager;
    private int tvSettlement_type = 1;  //判断提交键是什么状态


    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
    }

    @Override
    public ShoppingCartContract.Presenter getPresenter() {
        return shoppingCartPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_shoppingcart;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        tbTittle.getRightTitle().setVisibility(View.VISIBLE);
        tbTittle.getRightTitle().setText("管理");
        tbTittle.getRightTitle().setTextColor(getResources().getColor(R.color.cp_color_gray_deep));
        tbTittle.getRightTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tbTittle.getRightTitle().getText().equals("管理")) {
                    tvSettlement_type = 2;
                    tvTotalprice.setText("");
                    tvTotalprice.setVisibility(View.GONE);
                    text0.setVisibility(View.GONE);
                    text1.setVisibility(View.GONE);
                    tvSettlement.setText("删除(0)");
                    tbTittle.getRightTitle().setText("取消");
                    tbTittle.getRightTitle().setTextColor(getResources().getColor(R.color.red_ea3340));
                    //commodityInCartAdapter.unCheckAll();
                } else if (tbTittle.getRightTitle().getText().equals("取消")) {
                    tvSettlement_type = 1;
                    tvTotalprice.setVisibility(View.VISIBLE);
                    text0.setVisibility(View.VISIBLE);
                    text1.setVisibility(View.VISIBLE);
                    tvSettlement.setText("结算(0)");
                    tbTittle.getRightTitle().setText("管理");
                    tbTittle.getRightTitle().setTextColor(getResources().getColor(R.color.cp_color_gray_deep));
                }
                commodityInCartAdapter.unCheckAll();

            }
        });


        tvSettlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderConfirm = "";
                if (tvSettlement_type == 1) {  //提交状态

                    if (commodityInCartAdapter.getmSelectedPositions().size() == 0) {
                        Toast.makeText(BaseApplication.mContext, "请选择要结算商品", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for (int i = 0; i < commodityInCartAdapter.getmSelectedPositions().size(); i++) {
                        if (i == 0) {
                            orderConfirm = String.format("cid=%s&num=%s", commodityInCartAdapter.getmSelectedPositions().get(i).getC_id(), commodityInCartAdapter.getmSelectedPositions().get(i).getC_num());
                        } else {
                            orderConfirm += String.format(",cid=%s&num=%s", commodityInCartAdapter.getmSelectedPositions().get(i).getC_id(), commodityInCartAdapter.getmSelectedPositions().get(i).getC_num());
                        }
                    }
                    start(ConfirmCommodityOrderFragment.newInstance(orderConfirm, "", "购物车"));
                    //重置结算和选中条目
                    tvSettlement.setText("结算(" + 0 + ")");
                    commodityInCartAdapter.unCheckAll();
                }
                if (tvSettlement_type == 2) {   //删除状态
                    if (commodityInCartAdapter.getmSelectedPositions().size() == 0) {
                        Toast.makeText(BaseApplication.mContext, "请选择要删除商品", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for (int i = 0; i < commodityInCartAdapter.getmSelectedPositions().size(); i++) {
                        if (i == 0) {
                            orderConfirm = String.format("%s", commodityInCartAdapter.getmSelectedPositions().get(i).getC_id());
                        } else {
                            orderConfirm += String.format(",%s", commodityInCartAdapter.getmSelectedPositions().get(i).getC_id());
                        }
                    }

                    //此处删除操作
                    Map map = new HashMap();
                    map.put("token", Constants.token);
                    map.put("c_id", orderConfirm);
                    KLog.d("删除购物车",orderConfirm);
                    getPresenter().onDeleteCar(map);
                }
            }
        });
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvCheck.isChecked()) {
                    commodityInCartAdapter.checkAll();
                } else {
                    commodityInCartAdapter.unCheckAll();
                }
            }
        });

    }

    @Override
    public void initData() {
        commodityInCartAdapter = new CommodityInCartAdapter();
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        rvCommodity.setLayoutManager(linearLayoutManager);
        rvCommodity.setAdapter(commodityInCartAdapter);
        rvCommodity.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 1), getResources().getColor(R.color.material_grey_300)));
        commodityInCartAdapter.setOnSelectChangeListener(new CommodityInCartAdapter.onSelectChangeListener() {
            @Override
            public void onChange() {
                totalPrice = 0.0f;
                for (ShoppingCartBean.JdataEntity.CommodityEntity entity : commodityInCartAdapter.getmSelectedPositions()) {
                    totalPrice += Float.parseFloat(entity.getC_price()) * Float.parseFloat(entity.getC_num());
                }
                tvTotalprice.setText("¥" + totalPrice);
                if (commodityInCartAdapter.isAllSelected()) {
                    tvCheck.setChecked(true);
                } else {
                    tvCheck.setChecked(false);
                }
                if (tvSettlement_type == 1) {
                    tvSettlement.setText("结算(" + commodityInCartAdapter.getmSelectedPositions().size() + ")");
                }
                if (tvSettlement_type == 2) {
                    tvSettlement.setText("删除(" + commodityInCartAdapter.getmSelectedPositions().size() + ")");
                }
            }
        });
        guessYouLoveAdapter = new GuessYouLoveAdapter();
        gridLayoutManager = new GridLayoutManager(BaseApplication.mContext, 2);
        rvGuessYoulove.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext, 2), DensityUtils.dp2px(BaseApplication.mContext, 2), getResources().getColor(R.color.material_grey_300)));
        rvGuessYoulove.setLayoutManager(gridLayoutManager);
        rvGuessYoulove.setAdapter(guessYouLoveAdapter);
        guessYouLoveAdapter.setItemClickListener(new GuessYouLoveAdapter.OnGuessYouLoveItemClickListener() {
            @Override
            public void onItemClick(RecommendEntity entity) {
                start(CommodityDetailFragment.newInstance(entity.getC_id()));
            }
        });
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("page", currentPage + "");
        map.put("length", pagelength + "");
        getPresenter().getShoppingCart(map);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            Map map = new HashMap();
            map.put("token", Constants.token);
            map.put("page", currentPage + "");
            map.put("length", pagelength + "");
            getPresenter().getShoppingCart(map);
        }
    }


    @Override
    public void onGetShoppingCartSuccess(ShoppingCartBean bean) {

        commodityInCartAdapter.setNewData(bean.getJdata().getCommodity());
        guessYouLoveAdapter.setNewData(bean.getJdata().getRecommend());
    }

    @Override
    public void onGetShoppingCartFailure(String failure) {

        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nDeleteCarSuccess(DeleteCarBean bean) {

        Toast.makeText(BaseApplication.mContext, bean.getJdata().getJmsg(), Toast.LENGTH_LONG).show();
        //重置结算和选中条目
        tvSettlement.setText("删除(" + 0 + ")");
        commodityInCartAdapter.unCheckAll();
        currentPage = 0;
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("page", String.valueOf(currentPage));
        map.put("length", pagelength + "");
        getPresenter().getShoppingCart(map);
    }

    @Override
    public void nDeleteCarFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }

}
