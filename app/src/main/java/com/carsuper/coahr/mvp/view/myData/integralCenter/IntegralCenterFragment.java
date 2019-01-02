package com.carsuper.coahr.mvp.view.myData.integralCenter;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.integralCenter.IntegralCenterContract;
import com.carsuper.coahr.mvp.contract.myData.integralCenter.PointContract;
import com.carsuper.coahr.mvp.model.bean.IntegralCenterBean;
import com.carsuper.coahr.mvp.presenter.myData.integralCenter.IntegralCenterPresenter;
import com.carsuper.coahr.mvp.presenter.myData.integralCenter.PointPresenter;
import com.carsuper.coahr.mvp.view.adapter.integralCenter.IntegralCenterVpAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/8/23.
 * Email：hengzwdhengzwd@qq.com
 * 积分中心
 */
public class IntegralCenterFragment extends BaseFragment<IntegralCenterContract.Presenter> implements IntegralCenterContract.View {

    @Inject
    IntegralCenterPresenter integralCenterPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tv_integral)
    TextView tvIntegral;
    @BindView(R.id.tbl_my_integral)
    TabLayout tblMyIntegral;
    @BindView(R.id.vp_my_integral)
    ViewPager vpMyIntegral;


    private IntegralCenterVpAdapter integralCenterVpAdapter;

    private int currentPage = 0;

    private int pageLength = 10;

    private String myPoints;


    public static IntegralCenterFragment newInstance() {
        return new IntegralCenterFragment();
    }

    @Override
    public IntegralCenterContract.Presenter getPresenter() {
        return integralCenterPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_integralcenter;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

        integralCenterVpAdapter = new IntegralCenterVpAdapter(getChildFragmentManager());
        vpMyIntegral.setAdapter(integralCenterVpAdapter);
        tblMyIntegral.setupWithViewPager(vpMyIntegral);
        vpMyIntegral.setCurrentItem(0);
    }

    @Override
    public void initData() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("page", currentPage + "");
        map.put("length", pageLength + "");
        map.put("filter", "default");
        getPresenter().getPointList(map);


    }


    @Override
    public void onGetPointListSuccess(IntegralCenterBean bean) {
        myPoints = bean.getJdata().getPoints();
        tvIntegral.setText(myPoints);
    }

    @Override
    public void onGetPointListFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

}
