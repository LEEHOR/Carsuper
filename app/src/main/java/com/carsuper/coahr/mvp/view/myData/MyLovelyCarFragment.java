package com.carsuper.coahr.mvp.view.myData;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.MyLovelyCarContract;
import com.carsuper.coahr.mvp.model.bean.MyLovelyCarBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;
import com.carsuper.coahr.mvp.presenter.myData.MyLoveLyCarPresenter;
import com.carsuper.coahr.mvp.view.adapter.MyLovelyCarAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.maintenance.CarBrandFragment;
import com.carsuper.coahr.mvp.view.maintenance.OrderToMaintenanceFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
public class MyLovelyCarFragment extends BaseFragment<MyLovelyCarContract.Presenter> implements MyLovelyCarContract.View {

    @Inject
    MyLoveLyCarPresenter myLoveLyCarPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.rv_mylovely_car)
    RecyclerView rvMylovelyCar;

    private LinearLayoutManager linearLayoutManager;
    private MyLovelyCarAdapter myLovelyCarAdapter;
    private String car_id;
    private int form; //来自哪个页面
    private List<MyLovelyCarBean.JdataBean.MycarBean> mycarEntities = new ArrayList<>();

    public static MyLovelyCarFragment newInstance(int forFragment) {
        MyLovelyCarFragment fragment = new MyLovelyCarFragment();
        Bundle arg = new Bundle();
        arg.putInt("form", forFragment);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public MyLovelyCarContract.Presenter getPresenter() {
        return myLoveLyCarPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_mylovelycar;
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
        tbTittle.getRightTitle().setText("+新增车型");
        tbTittle.getRightTitle().setBackground(getResources().getDrawable(R.drawable.bg_white_frame_righttitle_background));
        tbTittle.getRightTitle().setTextColor(getResources().getColor(R.color.material_white));
        tbTittle.getRightTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(CarBrandFragment.newInstance(Constants.MYLOVELYCARFRAGMENT));
            }
        });

    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            form = getArguments().getInt("form");
        }
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        myLovelyCarAdapter = new MyLovelyCarAdapter();
        rvMylovelyCar.setLayoutManager(linearLayoutManager);
        rvMylovelyCar.setAdapter(myLovelyCarAdapter);
        myLovelyCarAdapter.setOnLovelyCarHandleListener(new MyLovelyCarAdapter.OnLovelyCarHandleListener() {
            @Override
            public void onDeleteCar(MyLovelyCarBean.JdataBean.MycarBean item) {
                Map map = new HashMap();
                map.put("token", Constants.token);
                map.put("car_id", item.getCar_id());
                getPresenter().deleteLovelyCar(map, item);
            }

            @Override
            public void onSetPrimary(MyLovelyCarBean.JdataBean.MycarBean item) {
                Map map = new HashMap();
                map.put("token", Constants.token);
                map.put("car_id", item.getCar_id());
                getPresenter().setPrimary(map, item);
            }

            @Override
            public void onSelectMyOrderCar(MyLovelyCarBean.JdataBean.MycarBean item) {
          /*      if (form==Constants.ORDERTOMAINTENANCEFRAGMENT){
                    Map map = new HashMap();
                    map.put("token", Constants.token);
                    map.put("cd_id", item.getCar_id());
                    map.put("motor", item.getMotor());
                    map.put("horsepower",item.getHorsepower());
                    getPresenter().saveUserCarInfo(map);
                }*/
                if (form == Constants.ORDERTOMAINTENANCEFRAGMENT) {
                    Map map = new HashMap();
                    car_id=item.getCar_id();
                    map.put("token", Constants.token);
                    map.put("car_id", item.getCar_id());
                    getPresenter().setPrimary(map, item);
                }
            }
        });

        Map map = new HashMap();
        map.put("token", Constants.token);
        getPresenter().getLovelyCarList(map);

    }

    @Override
    public void recieveData(Bundle bundle) {
        super.recieveData(bundle);
        Map map = new HashMap();
        map.put("token", Constants.token);
        getPresenter().getLovelyCarList(map);

    }

    @Override
    public void onGetLovelyCarListSuccess(MyLovelyCarBean bean) {
        mycarEntities = bean.getJdata().getMycar();
        myLovelyCarAdapter.setNewData(mycarEntities);
    }

    @Override
    public void onGetLovelyCarListFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteSuccess(MyLovelyCarBean.JdataBean.MycarBean mycarEntity, ResultBean bean) {
        if (bean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext,bean.getJdata().getJmsg(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(BaseApplication.mContext,bean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDeleteFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSetPrimarySuccess(MyLovelyCarBean.JdataBean.MycarBean mycarEntity, ResultBean bean) {
        if (form == Constants.ORDERTOMAINTENANCEFRAGMENT) {
            Bundle bundle = new Bundle();
            bundle.putString("cs_id", car_id);
            KLog.d("选择爱车", car_id);
            MyLovelyCarFragment.this.setFragmentResult(OrderToMaintenanceFragment.RESULT_MYLOVELYCAR, bundle);
            _mActivity.onBackPressed();
        } else {
            if (bean.getJdata().getJmsg() != null) {
                Toast.makeText(BaseApplication.mContext, bean.getJdata().getJmsg(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(BaseApplication.mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onSetPrimaryFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSaveUserCarInfoSuccess(SaveUserCarBean bean) {

    }

    @Override
    public void onSaveUserCarInfoFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvMylovelyCar.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 2), getResources().getColor(R.color.material_grey_300)));

    }
}
