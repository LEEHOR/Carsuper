package com.carsuper.coahr.utils;

import com.carsuper.coahr.mvp.model.bean.SearchBean.JdataEntity.SearchEntity;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Author： hengzwd on 2018/7/23.
 * Email：hengzwdhengzwd@qq.com
 */
public class SearchHistoryStackUtils {


    private String TAG = SearchHistoryStackUtils.class.getSimpleName();
    private static SearchHistoryStackUtils INSTANCE;
    private static Stack<String> stack = new Stack<String>();
    private static Stack<SearchEntity> searchStackEntites = new Stack<SearchEntity>();
    private List<SearchEntity> searchEntities = new ArrayList<SearchEntity>();

    private static final int TOTAL_HISTORY_COUNT = 8;   //搜索历史存储数量

    private SearchHistoryStackUtils() {
        initStack();
    }

    public static SearchHistoryStackUtils getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            INSTANCE = new SearchHistoryStackUtils();
        }
        return INSTANCE;
    }

    private void initStack() {
        for (int i = TOTAL_HISTORY_COUNT - 1; i >= 0; i--) {
            if (PreferenceUtils.contains(BaseApplication.mContext, i + "")&&PreferenceUtils.contains(BaseApplication.mContext,"name"+i)) {
                stack.push(PreferenceUtils.getPrefString(BaseApplication.mContext, i + "", ""));
                searchStackEntites.push(new SearchEntity(PreferenceUtils.getPrefString(BaseApplication.mContext, i + "", ""),PreferenceUtils.getPrefString(BaseApplication.mContext, "name"+i, ""),PreferenceUtils.getPrefString(BaseApplication.mContext,"type"+i,"")));
            } else {
                continue;
            }
        }
    }


    public void clearstack() {
        stack.clear();
        searchStackEntites.clear();
        int i = 0;
        while (i < TOTAL_HISTORY_COUNT) {
            PreferenceUtils.remove(BaseApplication.mContext, (i++) + "");
            PreferenceUtils.remove(BaseApplication.mContext, "name"+(i++));
            PreferenceUtils.remove(BaseApplication.mContext, "type"+(i++));

        }
    }

    public void save() {
        int i = 0;
        Stack<String> mstack = new Stack<String>();
        mstack.addAll(stack);
        Stack<SearchEntity> msearchStackEntites = new Stack<SearchEntity>();
        msearchStackEntites.addAll(searchStackEntites);
        String id;
        while (!mstack.empty()&&!msearchStackEntites.empty() && i < TOTAL_HISTORY_COUNT) {
            KLog.e("save"+i);
            id=mstack.pop();
            SearchEntity searchEntity=msearchStackEntites.pop();
            PreferenceUtils.setPrefString(BaseApplication.mContext, i + "", id);
            PreferenceUtils.setPrefString(BaseApplication.mContext, "name"+i , searchEntity.getName());
            PreferenceUtils.setPrefString(BaseApplication.mContext, "type"+i , searchEntity.getType());
            i++;
        }
    }


    public void add(SearchEntity searchEntity) {
        if (!stack.contains(searchEntity.getId())) {
            stack.push(searchEntity.getId());
            searchStackEntites.push(searchEntity);
        }
        save();
    }

    public List<SearchEntity> getsearchEntities() {
        searchEntities.clear();
        Stack<SearchEntity> mstack = new Stack<SearchEntity>();
        mstack.addAll(searchStackEntites);
        int i = 0;
        while (!mstack.empty() && i < 10) {
            searchEntities.add(mstack.pop());
            i++;
        }
        return searchEntities;
    }
}
