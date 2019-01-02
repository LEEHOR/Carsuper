package com.carsuper.coahr.mvp.view.maintenance;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.maintenance.CarHorsePowerContract;
import com.carsuper.coahr.mvp.model.bean.CarHorsePowerBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;
import com.carsuper.coahr.mvp.presenter.maintenance.CarHorsePowerPresenter;
import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.mvp.view.adapter.CarPicker.CarHorsePowerAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.myData.MyLovelyCarFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 * 车辆马力选择
 */
public class CarHorsePowerFragment extends BaseFragment<CarHorsePowerContract.Presenter> implements CarHorsePowerContract.View {


    @Inject
    CarHorsePowerPresenter carHorsePowerPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.iv_car_brand)
    ImageView ivCarBrand;
    @BindView(R.id.tv_carbrand_name)
    TextView tvCarbrandName;
    @BindView(R.id.rv_car_type)
    RecyclerView rvCarType;

    private String cs_id;
    private String brandIMG;
    private String brandName;
    private String motor;
    private String carSerialName;

    private LinearLayoutManager linearLayoutManager;

    private CarHorsePowerAdapter adapter;


    private int fromF;//来自哪个车型选择模块，服务订单确认  或者是  我的爱车页面  选择完成根据此返回fromF
    public static CarHorsePowerFragment newInstance(int fromF, String cs_id, String brandIMG, String brandName, String motor, String carSerialName) {
        CarHorsePowerFragment fragment = new CarHorsePowerFragment();
        Bundle arg = new Bundle();
        arg.putInt("fromF",fromF);
        arg.putString("cs_id", cs_id);
        arg.putString("brandIMG",brandIMG);
        arg.putString("brandName",brandName);
        arg.putString("motor",motor);
        arg.putString("carSerialName",carSerialName);
        fragment.setArguments(arg);
        return fragment;
    }




    @Override
    public CarHorsePowerContract.Presenter getPresenter() {
        return carHorsePowerPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_cartype;
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
        adapter = new CarHorsePowerAdapter();
        adapter.setOnCarTypeItemClickListener(new CarHorsePowerAdapter.onCarTypeItemClickListener() {
            @Override
            public void onItemClick(CarHorsePowerBean.JdataBean.CarDetailBean entity) {
                Map map = new HashMap();
                map.put("token", Constants.token);
                map.put("cd_id", entity.getId());
                map.put("motor", motor);
                map.put("horsepower",entity.getHorsepower());
                getPresenter().saveUserCarInfo(map);
            }
        });
        rvCarType.setLayoutManager(linearLayoutManager);
        rvCarType.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.decoration_f5f5f8)));
        rvCarType.setAdapter(adapter);
        fromF = getArguments().getInt("fromF");

        cs_id = getArguments().getString("cs_id");
        brandIMG = getArguments().getString("brandIMG");
        brandName = getArguments().getString("brandName");
        motor=getArguments().getString("motor");
        carSerialName = getArguments().getString("carSerialName");
        Imageloader.loadImage(brandIMG,ivCarBrand);
        tvCarbrandName.setText(brandName);
        Map map = new HashMap();
        map.put("cs_id", cs_id);
        map.put("motor",motor);
        getPresenter().getCarType(map);
    }


    @Override
    public void onGetCarTypeSuccess(CarHorsePowerBean bean) {
            adapter.setNewData(bean.getJdata().getCar_detail());
    }

    @Override
    public void onGetCarTypeFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSaveUserCarInfoSuccess(SaveUserCarBean bean) {
        Bundle bundle = new Bundle();
        bundle.putString("cs_id",cs_id);
        switch (fromF){
            case Constants.ORDERTOMAINTENANCEFRAGMENT:
              //  ((ContainerActiivty)_mActivity).jumpToSupportFragment(OrderToMaintenanceFragment.class,bundle);
                break;
            case  Constants.MYLOVELYCARFRAGMENT:
                ((ContainerActiivty)_mActivity).jumpToSupportFragment(MyLovelyCarFragment.class,bundle);
                break;
        }
    }

    @Override
    public void onSaveUserCarInfoFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
