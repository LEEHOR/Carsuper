package com.carsuper.coahr.mvp.view.myData.returnorchange;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/8/29.
 * Email：hengzwdhengzwd@qq.com
 */
public class ReasonFragment extends BaseFragment {


    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.rv_reason)
    RecyclerView rvReason;


    private int type;

    private String[] reasons = {"不喜欢","未按约定时间发货","货物破损已拒签","其他原因"};
    private LinearLayoutManager linearLayoutManager;
    private ReasonAdapter reasonAdapter;
    public static ReasonFragment newInstance(int type){
        ReasonFragment fragment = new ReasonFragment();
        Bundle arg = new Bundle() ;
        arg.putInt("type",type);
        fragment.setArguments(arg);
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
        switch (type) {
            case 1:
                tbTittle.getTvTittle().setText("退款理由");
                break;
            case 2:
                tbTittle.getTvTittle().setText("退货理由");
                break;
            case 3:
                tbTittle.getTvTittle().setText("换货理由");
                break;
        }
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        reasonAdapter = new ReasonAdapter(Arrays.asList(reasons));
        rvReason.setLayoutManager(linearLayoutManager);
        rvReason.setAdapter(reasonAdapter);

        reasonAdapter.setOnitemClickListener(new ReasonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String reason) {
                Bundle bundle = new Bundle();
                bundle.putString("reason",reason);
                setFragmentResult(1,bundle);
                _mActivity.onBackPressed();
            }
        });


    }



    public static class ReasonAdapter extends BaseQuickAdapter<String,BaseViewHolder>{


        private OnItemClickListener onItemClickListener;
        public ReasonAdapter( List<String> data) {
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
            void onItemClick(String reason);
        }
    }
}
