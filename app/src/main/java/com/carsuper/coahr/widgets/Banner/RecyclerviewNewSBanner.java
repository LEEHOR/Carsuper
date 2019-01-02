package com.carsuper.coahr.widgets.Banner;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.socks.library.KLog;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Author： hengzwd on 2018/8/30.
 * Email：hengzwdhengzwd@qq.com
 */
public class RecyclerviewNewSBanner extends RecyclerView {
    private Context context;
    private SmoothScrollLayoutManager linearLayoutManager;
    private NewsAdapter newsAdapter;

    private BannerHandler mBannerHandler = null;
    private List<String>  news;


    private static final int MSG_LOOP = 1000;
    private static long LOOP_INTERVAL = 5000;

    public RecyclerviewNewSBanner(Context context) {
        this(context,null);
    }

    public RecyclerviewNewSBanner(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RecyclerviewNewSBanner(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    private void init(){
        linearLayoutManager  = new SmoothScrollLayoutManager(context);

    }


    public int getCount(){
        if (news != null) {
            return news.size();
        }else {
            return 0;
        }
    }


    public void setNewsList(List<String> news){
        this.news= news;
        newsAdapter = new NewsAdapter(context,news);
        this.setLayoutManager(linearLayoutManager);
        this.setAdapter(newsAdapter);

    }

    private static class BannerHandler extends android.os.Handler {
        private WeakReference<RecyclerviewNewSBanner> weakReference = null;

        public  int currentItem = 0;//当前显示的item

        public BannerHandler(RecyclerviewNewSBanner recyclerviewNewSBanner) {
            super(Looper.getMainLooper());
            this.weakReference = new WeakReference<RecyclerviewNewSBanner>(recyclerviewNewSBanner);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.weakReference == null) {
                return;
            }
            RecyclerviewNewSBanner recyclerviewNewSBanner = this.weakReference.get();
            if (recyclerviewNewSBanner == null ||  recyclerviewNewSBanner.getAdapter() == null || recyclerviewNewSBanner.getCount()<= 0) {
                sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
                return;
            }


//            int a =  ((LinearLayoutManager)recyclerviewNewSBanner.getLayoutManager()).findFirstVisibleItemPosition();
//            ((LinearLayoutManager)recyclerviewNewSBanner.getLayoutManager()).scrollToPositionWithOffset();
            recyclerviewNewSBanner.stopScroll();
            recyclerviewNewSBanner.smoothScrollToPosition(++currentItem);
            sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
        }
    }


    public void startLoop(boolean flag) {
        if (flag) {
            if (mBannerHandler == null) {
                mBannerHandler = new BannerHandler(this);
            }
            mBannerHandler.currentItem = 0;
            if (mBannerHandler.hasMessages(MSG_LOOP)) {
                mBannerHandler.removeMessages(MSG_LOOP);
            }
            mBannerHandler.sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
        } else {
            if (mBannerHandler != null) {
                if (mBannerHandler.hasMessages(MSG_LOOP)) {
                    mBannerHandler.removeMessages(MSG_LOOP);
                }
            }
        }
    }

    public static class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

        private List<String> news;
        private Context context;
        private int size;

        public NewsAdapter(Context context,List<String> news) {
            this.context = context;
            this.news = news;
            size = news.size();
        }

        @NonNull
        @Override
        public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_news,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
                holder.tv_news.setText(news.get(position%size));
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        public  class NewsViewHolder extends RecyclerView.ViewHolder{

            public  TextView tv_news;
            public NewsViewHolder(View itemView) {
                super(itemView);
                tv_news = itemView.findViewById(R.id.tv_news);
            }

        }
    }

    private   class SmoothScrollLayoutManager extends LinearLayoutManager {

        public SmoothScrollLayoutManager(Context context) {
            super(context);
        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView,
                                           RecyclerView.State state, final int position) {

            LinearSmoothScroller smoothScroller =
                    new LinearSmoothScroller(recyclerView.getContext()) {
                        // 返回：滑过1px时经历的时间(ms)。
                        @Override
                        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                            return 1000f / displayMetrics.densityDpi;
                        }
                    };

            smoothScroller.setTargetPosition(position);
            startSmoothScroll(smoothScroller);
        }
    }


}
