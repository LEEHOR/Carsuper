package com.carsuper.coahr.mvp.view.myData.setting;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.setting.NameContract;
import com.carsuper.coahr.mvp.model.bean.SaveUserInfoBean;
import com.carsuper.coahr.mvp.presenter.myData.setting.NamePresenter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class NameFragment extends BaseFragment<NameContract.Presenter> implements NameContract.View {
    @Inject
    NamePresenter namePresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.tv_save)
    TextView tvSave;


    public static NameFragment newInstance(){
        return new NameFragment();
    }
    @Override
    public NameContract.Presenter getPresenter() {
        return namePresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_name;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = new HashMap();
                map.put("token", RequestBody.create(null,Constants.token));
                map.put("nickname",RequestBody.create(null,et_name.getText().toString()));
                getPresenter().updateName(map);
            }
        });
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString() !=null && !s.toString().equals("")) {
                    tvSave.setClickable(true);
                    tvSave.setBackgroundResource(R.color.material_blue_500);
                } else {
                    tvSave.setClickable(false);
                    tvSave.setBackgroundResource(R.color.material_grey_500);
                }
            }
        });
    }

    @Override
    public void initData() {

    }



    @Override
    public void onUpdateSuccess(SaveUserInfoBean bean) {
        Toast.makeText(BaseApplication.mContext,"修改成功",Toast.LENGTH_SHORT).show();
        setFragmentResult(RESULT_OK,new Bundle());
        _mActivity.onBackPressed();
    }

    @Override
    public void onUpdateFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

}
