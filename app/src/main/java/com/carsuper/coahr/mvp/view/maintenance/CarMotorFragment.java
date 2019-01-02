package com.carsuper.coahr.mvp.view.maintenance;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.contract.maintenance.CarMotorContract;
import com.carsuper.coahr.mvp.model.bean.CarMotorBean;
import com.carsuper.coahr.mvp.presenter.maintenance.CarMotorPresenter;
import com.carsuper.coahr.mvp.view.adapter.CarPicker.CarMotorAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 * 发动机排量
 */
public class CarMotorFragment extends BaseFragment<CarMotorContract.Presenter> implements CarMotorContract.View {

    @Inject
    CarMotorPresenter carMotorPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.iv_car_brand)
    ImageView ivCarBrand;
    @BindView(R.id.tv_carbrand_name)
    TextView tvCarbrandName;
    @BindView(R.id.rv_car_displacement)
    RecyclerView rvCarDisplacement;

    private String cs_id;
    private String brandIMG;
    private String brandName;
    private String carSerialName;

    private LinearLayoutManager linearLayoutManager;

    private CarMotorAdapter adapter;


    private int fromF;//来自哪个车型选择模块，服务订单确认  或者是  我的爱车页面  选择完成根据此返回fromF

    public static CarMotorFragment newInstance(int fromF, String cs_id, String brandIMG, String brandName, String carSerialName) {
        CarMotorFragment fragment = new CarMotorFragment();
        Bundle arg = new Bundle();
        arg.putInt("fromF",fromF);
        arg.putString("cs_id", cs_id);
        arg.putString("brandIMG",brandIMG);
        arg.putString("brandName",brandName);
        arg.putString("carSerialName",carSerialName);
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public void onGetCarDisplaceMentSuccess(CarMotorBean bean) {
        KLog.d("发动机",bean.getJdata().getCar_detail().get(0).getMotor());
            adapter.setNewData(bean.getJdata().getCar_detail());
    }

    @Override
    public void onGetCarDisplaceMentFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_LONG).show();
    }

    @Override
    public CarMotorContract.Presenter getPresenter() {
        return carMotorPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_car_displacement;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

    }

    @Override
    public void initData() {
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        adapter = new CarMotorAdapter();
        adapter.setoncarDisplacementClickListener(new CarMotorAdapter.onCarDisplacementItemClickLisenter() {
            @Override
            public void onItemClick(CarMotorBean.JdataBean.CarDetailBean entity) {

                start(CarHorsePowerFragment.newInstance(fromF,cs_id,brandIMG,brandName,entity.getMotor(),carSerialName));

            }
        });
        rvCarDisplacement.setLayoutManager(linearLayoutManager);
        rvCarDisplacement.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.decoration_f5f5f8)));
        rvCarDisplacement.setAdapter(adapter);
        fromF = getArguments().getInt("fromF");

        cs_id = getArguments().getString("cs_id");
        brandIMG = getArguments().getString("brandIMG");
        brandName = getArguments().getString("brandName");
        carSerialName = getArguments().getString("carSerialName");
        Imageloader.loadImage(brandIMG,ivCarBrand);
        tvCarbrandName.setText(brandName);
        Map map = new HashMap();
        map.put("cs_id", cs_id);
        getPresenter().getCarDisplaceMent(map);

    }




}
