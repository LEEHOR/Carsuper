package com.carsuper.coahr.mvp.view.myData.integralCenter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.integralCenter.PointContract;
import com.carsuper.coahr.mvp.model.bean.IntegralCenterBean;
import com.carsuper.coahr.mvp.presenter.myData.integralCenter.PointPresenter;
import com.carsuper.coahr.mvp.view.adapter.integralCenter.PointsAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/8/24.
 * Email：hengzwdhengzwd@qq.com
 */
public class PointFragment extends BaseFragment<PointContract.Presenter> implements PointContract.View {

    @Inject
    PointPresenter pointPresenter;
    @BindView(R.id.rv_points)
    RecyclerView rvPoints;


    private int currentPage = 0;

    private int pageLength = 10;

    private String filter;
    private LinearLayoutManager linearLayoutManager;
    private PointsAdapter pointsAdapter;

    public static PointFragment newInstance(String filter) {
        PointFragment fragment = new PointFragment();
        Bundle arg = new Bundle();
        arg.putString("filter", filter);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public PointContract.Presenter getPresenter() {
        return pointPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_points;
    }

    @Override
    public void initView() {
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        pointsAdapter = new PointsAdapter();
        rvPoints.setLayoutManager(linearLayoutManager);
        rvPoints.setAdapter(pointsAdapter);

    }

    @Override
    public void initData() {
        filter = getArguments().getString("filter");
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("page", currentPage + "");
        map.put("length", pageLength + "");
        map.put("filter", filter);
        getPresenter().getPointList(map);


    }


    @Override
    public void onGetPointListSuccess(IntegralCenterBean bean) {
        pointsAdapter.setNewData(bean.getJdata().getList());
    }

    @Override
    public void onGetPointListFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

}
