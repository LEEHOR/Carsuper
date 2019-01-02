package com.carsuper.coahr.mvp.view.myData.returnorchange;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/8/30.
 * Email：hengzwdhengzwd@qq.com
 */
public class GoodsStatusFragment extends BaseFragment {


    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.rv_reason)
    RecyclerView rvStatus;


    private int type;

    private String[] status = {"未收到货","已收到货"};
    private LinearLayoutManager linearLayoutManager;
    private StatusAdapter statusAdapter;
    public static GoodsStatusFragment newInstance(){
        GoodsStatusFragment fragment = new GoodsStatusFragment();
        return fragment;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_reason;
    }

    @Override
    public void initView() {
        tbTittle.getTvTittle().setText("货物状态");
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

    }

    @Override
    public void initData() {
        type = getArguments().getInt("type");

        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        statusAdapter = new StatusAdapter(Arrays.asList(status));
        rvStatus.setLayoutManager(linearLayoutManager);
        rvStatus.setAdapter(statusAdapter);

        statusAdapter.setOnitemClickListener(new StatusAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String status) {
                Bundle bundle = new Bundle();
                bundle.putString("status",status);
                setFragmentResult(2,bundle);
                _mActivity.onBackPressed();
            }
        });


    }




    public static class StatusAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


        private OnItemClickListener onItemClickListener;
        public StatusAdapter( List<String> data) {
            super(R.layout.recyclerview_item_reason, data);
        }


        @Override
        protected void convert(BaseViewHolder helper, final String item) {

            helper.setText(R.id.tv_reason,item);
            helper.getView(R.id.tv_reason).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(item);
                }
            });
        }


        public void setOnitemClickListener(OnItemClickListener onitemClickListener){
            this.onItemClickListener = onitemClickListener;
        }

        public   interface OnItemClickListener{
            void onItemClick(String status);
        }
    }
}
