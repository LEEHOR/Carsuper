package com.carsuper.coahr.widgets.Banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.socks.library.KLog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;


public class ArcBannerView extends FrameLayout {

    private static final int MSG_LOOP = 1000;
    private static long LOOP_INTERVAL = 5000;
    private LinearLayout mLinearPosition = null;
    private ViewPager mViewPager = null;
    private BannerHandler mBannerHandler = null;
    private ArcView mArcview = null;
    private List<String> imglist;
    private int[] imgArray;
    private int viewSize;
    private ItemClickListener itemClickListener;
    private static final int RESOURSE_LOCAL = 0;
    private static final int RESOURSE_INTERNET = 1;
    private int resourseType;


    private static class BannerHandler extends Handler {
        private WeakReference<ArcBannerView> weakReference = null;

        public BannerHandler(ArcBannerView bannerView) {
            super(Looper.getMainLooper());
            this.weakReference = new WeakReference<ArcBannerView>(bannerView);//弱引用，不影响acitivity被回收时，Bannerview被回收（避免内存泄漏）
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.weakReference == null) {
                return;
            }
            ArcBannerView bannerView = this.weakReference.get();
            if (bannerView == null || bannerView.mViewPager == null || bannerView.mViewPager.getAdapter() == null || bannerView.mViewPager.getAdapter().getCount() <= 0) {
                sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
                return;
            }
            int curPos = bannerView.mViewPager.getCurrentItem();
            curPos = (curPos + 1) % bannerView.mViewPager.getAdapter().getCount();
            bannerView.mViewPager.setCurrentItem(curPos);
            sendEmptyMessageDelayed(MSG_LOOP, LOOP_INTERVAL);
        }
    }

    public ArcBannerView(Context context) {
        super(context);
        init();
    }

    public ArcBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public void startLoop(boolean flag) {
        if (flag) {
            if (mBannerHandler == null) {
                mBannerHandler = new BannerHandler(this);
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
        initLinearPosition();
        initArcView();
        this.addView(mViewPager);
        this.addView(mLinearPosition);
        this.addView(mArcview);
    }

    private void initArcView() {
        mArcview = new ArcView(BaseApplication.mContext);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT);
        mArcview.setLayoutParams(layoutParams);
    }

    private void initViewPager() {
        mViewPager = new ViewPager(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        mViewPager.setLayoutParams(layoutParams);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                updateLinearPosition();
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

    private void initLinearPosition() {
        mLinearPosition = new LinearLayout(getContext());
        mLinearPosition.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        layoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.dp_10);
        mLinearPosition.setPadding(getResources().getDimensionPixelSize(R.dimen.dp_10), 0, 0, 0);
        mLinearPosition.setLayoutParams(layoutParams);
    }

    public void setAdapter(PagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        adapter.registerDataSetObserver(mDataObserver);
        updateLinearPosition();
    }

    private DataSetObserver mDataObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            KLog.e("onChanged");
            updateLinearPosition();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    };

    private void updateLinearPosition() {
        if (imglist != null && imglist.size() != 0) {
            if (mLinearPosition.getChildCount() != viewSize) {
                int diffCnt = mLinearPosition.getChildCount() - viewSize;
                boolean needAdd = diffCnt < 0;
                diffCnt = abs(diffCnt);
                for (int i = 0; i < diffCnt; i++) {
                    if (needAdd) {
                        ImageView img = new ImageView(getContext());
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.rightMargin = getResources().getDimensionPixelOffset(R.dimen.dp_10);
                        img.setLayoutParams(layoutParams);
                        img.setBackgroundResource(R.mipmap.banner_point);
                        mLinearPosition.addView(img);
                    } else {
                        mLinearPosition.removeViewAt(0);
                    }
                }
            }
            int curPos = mViewPager.getCurrentItem();
            for (int i = 0; i < mLinearPosition.getChildCount(); i++) {
                if (i == (curPos % viewSize)) {
                    mLinearPosition.getChildAt(i).setBackgroundResource(R.mipmap.banner_point_select);
                } else {
                    mLinearPosition.getChildAt(i).setBackgroundResource(R.mipmap.banner_point);
                }
            }
        }
    }


    public ArcBannerView setImgList(List<String> imglist) {
        this.imglist = imglist;
        resourseType = RESOURSE_INTERNET;
        viewSize = this.imglist.size();
        BannerAdapter bannerAdapter = new BannerAdapter(this.imglist);
        setAdapter(bannerAdapter);
        return this;
    }


    public void setTransformAnim(boolean flag) {
        if (flag) {
            mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
                private static final float MIN_SCALE = 0.75f;

                @Override
                public void transformPage(View view, float position) {
                    int pageWidth = view.getWidth();
                    if (position < -1) { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        view.setRotation(0);

                    } else if (position <= 1) { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        if (position < 0) {

                            float mRot = (20f * position);
                            view.setPivotX(view.getMeasuredWidth() * 0.5f);
                            view.setPivotY(view.getMeasuredHeight());
                            view.setRotation(mRot);
                        } else {

                            float mRot = (20f * position);
                            view.setPivotX(view.getMeasuredWidth() * 0.5f);
                            view.setPivotY(view.getMeasuredHeight());
                            view.setRotation(mRot);
                        }

                        // Scale the page down (between MIN_SCALE and 1)

                        // Fade the page relative to its size.

                    } else { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        view.setRotation(0);
                    }
                }
            });
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public static void setLoopInterval(long loopInterval) {
        LOOP_INTERVAL = loopInterval;
    }


    public class BannerAdapter extends PagerAdapter {
        private List<String> imgList1;
        private int[] imgArray;
        private int size;
        private final int cacheCount = 3;//内存中缓存多少个Imageview
        private List<ImageView> imageViews = new ArrayList<ImageView>();


        public BannerAdapter(List<String> viewList) {
            imgList1 = viewList;
            size = imgList1.size();
            for (int i = 0; i < size; i++) {
                ImageView image = new ImageView(BaseApplication.mContext);
                image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                //设置显示格式
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                Imageloader.loadImage(imgList1.get(i), image);
                final int finalI = i;
                image.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onClick(finalI);
                    }
                });

                imageViews.add(image);
            }
        }



        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (size> cacheCount){
                container.removeView(imageViews.get(position%size));
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            KLog.e("instantiateItem", position + "");
            ViewGroup parent = (ViewGroup) imageViews.get(position % size).getParent();
            if (parent != null) {
                parent.removeView(imageViews.get(position % size));
            }
            container.addView(imageViews.get(position% size));
            return imageViews.get(position% size);

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


    public class ArcView extends View {
        private Paint mpaint;
        private final int DEFAULT_BOTTOM_COLOR = Color.WHITE;
        private final int DEFAULT_TOP_COLOR = Color.TRANSPARENT;
        private int topColor;
        private int bottomColor;

        public ArcView(Context context) {
            this(context, null);
            initArc();
        }

        public ArcView(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs, 0);
            initArc();
        }

        public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BannerView, defStyleAttr, 0);
            topColor = a.getColor(R.styleable.BannerView_topcolor, DEFAULT_TOP_COLOR);
            bottomColor = a.getColor(R.styleable.BannerView_bottomcolor, DEFAULT_BOTTOM_COLOR);
            a.recycle();
            initArc();
        }


        private void initArc() {
            mpaint = new Paint();
            mpaint.setColor(bottomColor);
            mpaint.setStyle(Paint.Style.FILL);
            mpaint.setAntiAlias(true);//抗锯齿
            setWillNotDraw(false);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            KLog.e("ondraw");
            int left = getLeft() + getPaddingLeft();
            int top = getTop() + getPaddingTop();
            int right = getRight() - getPaddingRight();
            int bottom = getBottom() - getPaddingBottom();
            int width = right - left;
            int height = bottom - top;
            RectF rectF = new RectF(left, top, right, bottom);
            int layerId = canvas.saveLayer(rectF, null, Canvas.ALL_SAVE_FLAG);//建一个新的图层
            mpaint.setColor(bottomColor);
            canvas.drawRect(rectF, mpaint);
            mpaint.setColor(topColor);
            mpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            canvas.drawCircle(left + (float) (0.5 * width), top - 2 * height, 3 * height, mpaint);
            mpaint.setXfermode(null);
            canvas.restoreToCount(layerId);
        }
    }

    private interface ItemClickListener {
        void onClick(int position);
    }
}
