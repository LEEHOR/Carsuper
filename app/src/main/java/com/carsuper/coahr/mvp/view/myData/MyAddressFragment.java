package com.carsuper.coahr.mvp.view.myData;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.MyAddressContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.UserAddressBean;
import com.carsuper.coahr.mvp.presenter.myData.MyAddressPresenter;
import com.carsuper.coahr.mvp.view.adapter.MyAddressAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.carsuper.coahr.widgets.myTittleBar.TextViewTittleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/8/15.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyAddressFragment extends BaseFragment<MyAddressContract.Presenter> implements MyAddressContract.View {

    @Inject
    MyAddressPresenter myAddressPresenter;
    @BindView(R.id.toolbar_title)
    NormalTittleBar  toolbar_title;
    @BindView(R.id.rv_myaddress)
    RecyclerView rvMyaddress;
    Unbinder unbinder;

    private MyAddressAdapter myAddressAdapter;
    private LinearLayoutManager linearLayoutManager;

    private int fromF;

    private List<UserAddressBean.JdataEntity.AddressEntity> addressEntities = new ArrayList<>();
    public static MyAddressFragment newInstance(int fromF){
        MyAddressFragment fragment  = new MyAddressFragment();
        Bundle arg = new Bundle();
        arg.putInt("fromF",fromF);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public MyAddressContract.Presenter getPresenter() {
        return myAddressPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_myaddress;
    }

    @Override
    public void initView() {
        toolbar_title.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        toolbar_title.getRightTitle().setVisibility(View.VISIBLE);
        toolbar_title.getRightTitle().setText("+新增收货地址");
        toolbar_title.getRightTitle().setBackground(getResources().getDrawable(R.drawable.bg_white_frame_righttitle_background));
        toolbar_title.getRightTitle().setTextColor(getResources().getColor(R.color.material_white));
        toolbar_title.getRightTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startForResult(EditAddressFragment.newInstance(null,null,null,null),1);

            }
        });
        /**
         * 新增地址
         */

    }

    @Override
    public void initData() {
        fromF= getArguments().getInt("fromF");
        myAddressAdapter = new MyAddressAdapter();
        linearLayoutManager =new LinearLayoutManager(BaseApplication.mContext);
        rvMyaddress.setLayoutManager(linearLayoutManager);
        rvMyaddress.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 2), getResources().getColor(R.color.material_grey_300)));
        rvMyaddress.setAdapter(myAddressAdapter);
        myAddressAdapter.setOnAddressHandleListener(new MyAddressAdapter.onAddressHandleListener() {
            @Override
            public void onItemClick(UserAddressBean.JdataEntity.AddressEntity item) {
                if (fromF== Constants.COMMODITYDETAILFRAGMENT) {
                    Bundle bundle = new Bundle();
                    bundle.putString("ua_id",item.getId());
                    bundle.putString("address",item.getAddress());
                    setFragmentResult(1,bundle);
                    _mActivity.onBackPressed();
                }else  if (fromF == Constants.CONFIRMCOMMODITYORDERFRAGMENT){
                    Bundle bundle = new Bundle();
                    bundle.putString("ua_id",item.getId());
                    bundle.putString("address",item.getAddress());
                    bundle.putString("username",item.getUsername());
                    bundle.putString("telephone",item.getTelephone());
                    setFragmentResult(2,bundle);
                    _mActivity.onBackPressed();
                }
            }

            @Override
            public void edit(UserAddressBean.JdataEntity.AddressEntity item) {
                startForResult(EditAddressFragment.newInstance(item.getId(),item.getAddress(),item.getTelephone(),item.getUsername()),1);
            }

            @Override
            public void delete(UserAddressBean.JdataEntity.AddressEntity item) {
                Map map = new HashMap();
                map.put("token",Constants.token);
                map.put("id",item.getId());
                getPresenter().deleteUserAddress(map);
            }

            @Override
            public void onSetPrimary(UserAddressBean.JdataEntity.AddressEntity item) {
                        Map map = new HashMap();
                        map.put("token",Constants.token);
                        map.put("id",item.getId());
                        getPresenter().setPrimaryAddress(map);
            }
        });
        Map map = new HashMap();
        map.put("token", Constants.token);
        getPresenter().getUserAddressList(map);
    }

    @Override
    public void onSaveUserAddressSuccess(ResultBean bean) {
        Toast.makeText(BaseApplication.mContext,"保存地址成功",Toast.LENGTH_SHORT).show();
        Map map = new HashMap();
        map.put("token", Constants.token);
        getPresenter().getUserAddressList(map);
    }

    @Override
    public void onSaveUserAddressFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ongetUserAddressListSuccess(UserAddressBean bean) {
        addressEntities.clear();
        addressEntities.addAll(bean.getJdata().getAddress());
        myAddressAdapter.setNewData(addressEntities);
    }

    @Override
    public void onGetUserAddressListFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDeleteUserAddressSuccess(ResultBean bean) {
        Map map = new HashMap();
        map.put("token", Constants.token);
        getPresenter().getUserAddressList(map);
    }


    @Override
    public void onDeleteUserAddressFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSetPrimarySuccess(ResultBean bean) {
        Map map = new HashMap();
        map.put("token", Constants.token);
        getPresenter().getUserAddressList(map);
    }

    @Override
    public void onSetPrimaryFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode==1) {
                Map map = new HashMap();
                map.put("token",Constants.token);
                map.put("username",data.get("username"));
                map.put("address",data.get("address"));
                map.put("telephone",data.get("telephone"));
                map.put("id",data.get("id"));
                getPresenter().saveUserAddress(map);
            }
        }
    }
}
