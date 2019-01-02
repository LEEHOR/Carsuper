package com.carsuper.coahr.mvp.view.myData;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.ThankForEvaluateContract;
import com.carsuper.coahr.mvp.model.bean.RecommendEntity;
import com.carsuper.coahr.mvp.model.bean.TrankForEvaluateBean;
import com.carsuper.coahr.mvp.presenter.myData.ThankForEvaluatePresenter;
import com.carsuper.coahr.mvp.view.adapter.ContinueEvaluateAdapter;
import com.carsuper.coahr.mvp.view.adapter.shoppingCart.GuessYouLoveAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.shoppingMall.CommodityEvaluateDetailFragment;
import com.carsuper.coahr.mvp.view.store.StoreEvaluateDetailFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/8/27.
 * Email：hengzwdhengzwd@qq.com
 */
public class ThankForEvaluateFragment extends BaseFragment<ThankForEvaluateContract.Presenter> implements ThankForEvaluateContract.View {


    @Inject
    ThankForEvaluatePresenter thankForEvaluatePresenter;

    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tv_seecomment)
    TextView tvSeecomment;
    @BindView(R.id.rv_to_evaluate)
    RecyclerView rvToEvaluate;
    @BindView(R.id.rv_guess_youlove)
    RecyclerView rvGuessYoulove;


    private String type;//commodity  1；staition  2
    private String order_id;
    private String comment_id; //评论的id

    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    private ContinueEvaluateAdapter continueEvaluateAdapter;
    private GuessYouLoveAdapter guessYouLoveAdapter;
    public static ThankForEvaluateFragment newInstance(String order_id,String type,String comment_id){
        ThankForEvaluateFragment fragment = new ThankForEvaluateFragment();
        Bundle arg = new Bundle();
        arg.putString("order_id",order_id);
        arg.putString("type",type);
        arg.putString("comment_id",comment_id);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public ThankForEvaluateContract.Presenter getPresenter() {
        return thankForEvaluatePresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_thanksforevaluate;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

        tvSeecomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("1")) {
                    start(CommodityEvaluateDetailFragment.newInstance(comment_id,CommodityEvaluateDetailFragment.ACTION_SHOW));
                }else if (type.equals("2")){
                    start(StoreEvaluateDetailFragment.newInstance(comment_id,StoreEvaluateDetailFragment.ACTION_SHOW));
                }
            }
        });

    }

    @Override
    public void initData() {

        order_id =getArguments().getString("order_id");
        type = getArguments().getString("type");
        comment_id = getArguments().getString("comment_id");
        linearLayoutManager  = new LinearLayoutManager(BaseApplication.mContext);
        continueEvaluateAdapter = new ContinueEvaluateAdapter();
        rvToEvaluate.setLayoutManager(linearLayoutManager);
        rvToEvaluate.setAdapter(continueEvaluateAdapter);
        continueEvaluateAdapter.setOnGoToEvaluateListener(new ContinueEvaluateAdapter.OnGoToEvaluateListener() {
            @Override
            public void gotoEvaluate(TrankForEvaluateBean.JdataEntity.OrderEntity item) {
                start(ToEvaluateFragment.newInstance(item.getOrder_id(),item.getType()));
            }
        });

        gridLayoutManager = new GridLayoutManager(BaseApplication.mContext,2);
        guessYouLoveAdapter = new GuessYouLoveAdapter();
        rvGuessYoulove.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext, 4), DensityUtils.dp2px(BaseApplication.mContext, 4), getResources().getColor(R.color.material_grey_300)));
        rvGuessYoulove.setLayoutManager(gridLayoutManager);
        rvGuessYoulove.setAdapter(guessYouLoveAdapter);
        guessYouLoveAdapter.setItemClickListener(new GuessYouLoveAdapter.OnGuessYouLoveItemClickListener() {
            @Override
            public void onItemClick(RecommendEntity entity) {
//                start();
            }
        });
        Map map  = new HashMap();
        map.put("token", Constants.token);
        map.put("order_id",order_id);
        map.put("type",type);
        getPresenter().getCommodityList(map);

    }


    @Override
    public void onGetCommodityListSuccess(TrankForEvaluateBean bean) {


        continueEvaluateAdapter.setNewData(bean.getJdata().getOrder());

        guessYouLoveAdapter.setNewData(bean.getJdata().getCommodity());

    }

    @Override
    public void onGetCommodityListFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

}
