package com.carsuper.coahr.mvp.view.base;

import com.baidu.location.BDLocation;

/**
 * Date：2018/10/12
 * Time：11:50
 * Created by Leehor
 */
public interface FragmentBaiduListener {
    void baiduLocationSuccess(BDLocation location);
    void baiduLocationFail(int locType);
}
