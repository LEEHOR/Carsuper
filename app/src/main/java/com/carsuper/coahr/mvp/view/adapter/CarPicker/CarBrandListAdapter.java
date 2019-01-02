package com.carsuper.coahr.mvp.view.adapter.CarPicker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.CarBrandBean;
import com.carsuper.coahr.mvp.view.adapter.CarPicker.decoration.CarBrandGridItemDecoration;
import com.carsuper.coahr.mvp.view.adapter.citypicker.CityListAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Author： hengzwd on 2018/8/6.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarBrandListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CarBrandBean.JdataEntity.BrandEntityX.BrandEntity> mdata =new ArrayList<>();
    private List<CarBrandBean.JdataEntity.HotEntity>  hotbrandEntities = new ArrayList<>();
    private Context context;
    private OnCarBrandItemClickListener onCarBrandItemClickListener;
    public static final int  TYPE_NORMAL = 0;

    public static final int TYPE_HOT = 1;

    public  boolean search=false; //是否搜索


    public CarBrandListAdapter(Context context, List<CarBrandBean.JdataEntity.BrandEntityX.BrandEntity> mdata, List<CarBrandBean.JdataEntity.HotEntity> hotbrandEntities){
        this.context = context;
        this.mdata=mdata;
        this.hotbrandEntities=hotbrandEntities;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * 搜索用
     */
    public void setTypeNormal(){
        search=true;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HOT) {
            return new HotBrandViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_carbrand_header,parent,false));
        }else {
            return new NormalBrandViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_carbrand_list,parent,false));
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof NormalBrandViewHolder) {
          Imageloader.loadImage(mdata.get(position).getPic(),((NormalBrandViewHolder) holder).brandIMG);
          ((NormalBrandViewHolder) holder).brandName.setText(mdata.get(position).getB_name());
            ((NormalBrandViewHolder) holder).relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCarBrandItemClickListener.onItemClick(mdata.get(position));
                }
            });
        }

        if (holder instanceof HotBrandViewHolder) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,4);
            HotCarBrandAdapter adapter = new HotCarBrandAdapter(hotbrandEntities);
            adapter.setBrandItemClicklistener(new OnCarBrandItemClickListener() {
                @Override
                public void onItemClick(CarBrandBean.JdataEntity.BrandEntityX.BrandEntity item) {
                }

                @Override
                public void onHotItemClick(CarBrandBean.JdataEntity.HotEntity item) {
                    onCarBrandItemClickListener.onHotItemClick(item);
                }
            });
            ((HotBrandViewHolder) holder).recyclerView.setHasFixedSize(true);
            ((HotBrandViewHolder) holder).recyclerView.setLayoutManager(gridLayoutManager);
            ((HotBrandViewHolder) holder).recyclerView.setAdapter(adapter);

        }

    }

    public void setBrandItemClicklistener(OnCarBrandItemClickListener onCarBrandItemClickListener){
        this.onCarBrandItemClickListener=onCarBrandItemClickListener;
    }
    @Override
    public int getItemViewType(int position) {
        if (mdata.size()>0&&mdata!=null) {
            if (position==0) {
                return TYPE_HOT;
            }else {
                return TYPE_NORMAL;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (mdata != null) {
            return mdata.size();
        }
        return 0;
    }


    public class NormalBrandViewHolder extends RecyclerView.ViewHolder{
        public RelativeLayout relativeLayout;

        public ImageView brandIMG;
        public TextView brandName;
        public NormalBrandViewHolder(View itemView) {
            super(itemView);
            relativeLayout= itemView.findViewById(R.id.rl_car_brand);
            brandIMG = itemView.findViewById(R.id.iv_car_brand);
            brandName = itemView.findViewById(R.id.tv_carbrand_name);
        }

    }

    public class HotBrandViewHolder extends RecyclerView.ViewHolder{
        public RecyclerView recyclerView;

        public HotBrandViewHolder(View itemView) {
            super(itemView);
            recyclerView= itemView.findViewById(R.id.rv_hotbrand);
           //recyclerView.addItemDecoration(new CarBrandGridItemDecoration(4, DensityUtils.dp2px(context,12)));
            recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext,10),DensityUtils.dp2px(BaseApplication.mContext,10)));
        }
    }

}
