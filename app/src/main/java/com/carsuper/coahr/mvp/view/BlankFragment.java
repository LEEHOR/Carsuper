package com.carsuper.coahr.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carsuper.coahr.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author： hengzwd on 2018/7/18.
 * Email：hengzwdhengzwd@qq.com
 */
public class BlankFragment extends SupportFragment {
    //由于项目导航栏设计了五个图标，但是其中的预约模块是弹出activity，为了底部导航栏item个数与fragment数目一直，代码简化，设置一个空fragment凑数
    public static BlankFragment newInstance(){
        return new BlankFragment();
    }



}
