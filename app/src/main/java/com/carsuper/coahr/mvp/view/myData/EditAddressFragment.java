package com.carsuper.coahr.mvp.view.myData;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.view.base.BaseActivity;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/8/16.
 * Email：hengzwdhengzwd@qq.com
 */
public class EditAddressFragment extends BaseFragment {


    @BindView(R.id.et_reciever)
    EditText etReciever;
    @BindView(R.id.et_contact)
    EditText etContact;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.bt_submit)
    Button btSubmit;


    private String id, address,telephone1,username;
    private boolean etReciever_b=false,etContact_b=false,etAddress_b=false;
    public static EditAddressFragment newInstance(String id,String address,String telephone,String username) {
        EditAddressFragment fragment = new EditAddressFragment();
        Bundle arg = new Bundle();
        arg.putString("id", id);
        arg.putString("address",address);
        arg.putString("telephone",telephone);
        arg.putString("username",username);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_editaddress;
    }

    @Override
    public void initView() {
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etContact.getText().length() ==11 && !TextUtils.isEmpty(etReciever.getText()) && !TextUtils.isEmpty(etAddress.getText())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("username", etReciever.getText().toString());
                    bundle.putString("address", etAddress.getText().toString());
                    bundle.putString("telephone", etContact.getText().toString());
                    bundle.putString("id", id);
                    setFragmentResult(1, bundle);
                    _mActivity.onBackPressed();
                }
            }
        });
        etReciever.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s !=null && !s.toString().equals("")) {
                    etReciever_b=true;
                    if (etAddress_b && etContact_b && etReciever_b){
                        btSubmit.setClickable(true);
                        btSubmit.setBackgroundResource(R.color.prominent_text_color);
                    }
                } else {
                    etReciever_b=false;
                    btSubmit.setClickable(false);
                    btSubmit.setBackgroundColor(getResources().getColor(R.color.material_grey_500));
                    Toast.makeText(BaseApplication.mContext,"收货人不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });

        etContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==11) {
                    etContact_b=true;
                    if (etAddress_b && etContact_b && etReciever_b){
                        btSubmit.setClickable(true);
                        btSubmit.setBackgroundResource(R.color.prominent_text_color);
                    }
                } else {
                    etContact_b=false;
                    btSubmit.setClickable(false);
                    btSubmit.setBackgroundColor(getResources().getColor(R.color.material_grey_500));
                    Toast.makeText(BaseApplication.mContext,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                }
            }
        });
        etAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s  !=null && !s.toString().equals("")) {
                    etAddress_b=true;
                    if (etAddress_b && etContact_b && etReciever_b){
                        btSubmit.setClickable(true);
                        btSubmit.setBackgroundResource(R.color.prominent_text_color);
                    }
                } else {
                    etAddress_b=false;
                    btSubmit.setClickable(false);
                    btSubmit.setBackgroundColor(getResources().getColor(R.color.material_grey_500));
                    Toast.makeText(BaseApplication.mContext,"地址不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            id = getArguments().getString("id");
            address = getArguments().getString("address");
            telephone1 = getArguments().getString("telephone");
            username = getArguments().getString("username");

        }
        if (username != null) {
            etReciever.setText(username);
        }
        if (address != null) {
            etAddress.setText(address);
        }
        if (telephone1 != null) {
            etContact.setText(telephone1);
            if (telephone1.length()==11){
                btSubmit.setClickable(true);
                btSubmit.setBackgroundResource(R.color.prominent_text_color);
            } else {
                btSubmit.setClickable(false);
                btSubmit.setBackgroundColor(getResources().getColor(R.color.material_grey_500));
            }
        } else {
            btSubmit.setClickable(false);
            btSubmit.setBackgroundColor(getResources().getColor(R.color.material_grey_500));
        }

    }



}
