package com.carsuper.coahr.widgets.Banner;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.socks.library.KLog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Author： hengzwd on 2018/6/11.
 * Email：hengzwdhengzwd@qq.com
 */
public class NewsBannerView extends FrameLayout {

    private static final int MSG_LOOP = 1000;
    private static long LOOP_INTERVAL = 5000;
    private VerticalViewPager2 mViewPager = null;
    private NewsBannerView.BannerHandler mBannerHandler = null;
    private List<String> newsList;
    private int[] imgArray;
    private int viewSize;
    private NewsBannerView.ItemClickListener itemClickListener;


    private static class BannerHandler extends Handler {
        private WeakReference<NewsBannerView> weakReference = null;

        public BannerHandler(NewsBannerView NewsBannerView) {
            super(Looper.getMainLooper());
            this.weakReference = new WeakReference<NewsBannerView>(NewsBannerView);//弱引用，不影响acitivity被回收时，NewsBannerView被回收（避免内存泄漏）
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.weakReference == null) {
                return;
            }
            NewsBannerView NewsBannerView = this.weakReference.get();
            if (NewsBannerView == null || NewsBannerView.mViewPager == null || NewsBannerView.mViewPager.getAdapter() == null || NewsBannerView.mViewPager.getAdapter().getCount() <= 0) {
                sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
                return;
            }
            int curPos = NewsBannerView.mViewPager.getCurrentItem();
            curPos = (curPos + 1) % NewsBannerView.mViewPager.getAdapter().getCount();
            NewsBannerView.mViewPager.setCurrentItem(curPos);
            sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
        }
    }

    public NewsBannerView(Context context) {
        super(context);
        init();
    }

    public NewsBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public void startLoop(boolean flag) {
        if (flag) {
            if (mBannerHandler == null) {
                mBannerHandler = new NewsBannerView.BannerHandler(this);
            }
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

    private void init() {
        initViewPager();
        this.addView(mViewPager);
    }

    private void initViewPager() {
        mViewPager = new VerticalViewPager2(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        mViewPager.setLayoutParams(layoutParams);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        if (mBannerHandler != null) {
                            if (mBannerHandler.hasMessages(MSG_LOOP)) {
                                mBannerHandler.removeMessages(MSG_LOOP);
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mBannerHandler != null) {
                            mBannerHandler.sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }



    public void setAdapter(PagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        adapter.registerDataSetObserver(mDataObserver);

    }

    private DataSetObserver mDataObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            KLog.e("onChanged");

        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    };


    public NewsBannerView setNewsList(List<String> newsList) {
        this.newsList = newsList;
        viewSize = this.newsList.size();
        NewsBannerView.BannerAdapter bannerAdapter = new NewsBannerView.BannerAdapter(this.newsList);
        setAdapter(bannerAdapter);
        return this;
    }


    public void setItemClickListener(NewsBannerView.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public static void setLoopInterval(long loopInterval) {
        LOOP_INTERVAL = loopInterval;
    }


    public class BannerAdapter extends PagerAdapter {
        private List<String> newsList;
        private int[] imgArray;
        private int size;
        private final int cacheCount = 3;//内存中缓存多少个Imageview
        private List<TextView> textViews = new ArrayList<TextView>();


        public BannerAdapter(List<String> newsList) {
            this.newsList = newsList;
            size = this.newsList.size();
            for (int i = 0; i < size; i++) {
                TextView textView = new TextView(BaseApplication.mContext);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                textView.setText(this.newsList.get(i));
                final int finalI = i;
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onClick(finalI);
                    }
                });
                textViews.add(textView);
            }
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (size> cacheCount){
                container.removeView(textViews.get(position%size));
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ViewGroup parent = (ViewGroup) textViews.get(position % size).getParent();
            if (parent != null) {
                parent.removeView(textViews.get(position % size));
            }
            container.addView(textViews.get(position% size));
            return textViews.get(position% size);

        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


    private interface ItemClickListener {
        void onClick(int position);
    }
}
