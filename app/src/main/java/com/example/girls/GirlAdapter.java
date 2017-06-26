package com.example.girls;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by 媚敏 on 2017/6/16.
 */

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.ViewHolder> {
    private static final String TAG = "GirlAdapter";
    
    private Context mContext;
    private List<Results> mResultList;

    /**
     * 构造方法
     * @param context
     * @param resultList
     */
    public GirlAdapter(Context context, List<Results> resultList) {
        mContext = context;
        mResultList = resultList;
    }


    /**
     *必须的一个内部类
     * 所有的findViewById都在这个内部类的方法里实现
     */
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView resultImage;
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView) itemView;
            resultImage= (ImageView) itemView.findViewById(R.id.girls_image);
            description= (TextView) itemView.findViewById(R.id.description);
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }

        /**
         * 使用LayoutInflater为这个子项加载我们传入的布局
         * 参数一:要加载的子项布局的id
         * 参数二：默认parent
         * 参数三：默认false
         */
        View view= LayoutInflater.from(mContext).inflate(R.layout.girl_item,parent,false);
        return new ViewHolder(view);
    }



    @Override//当每个子项被滚到屏幕内时调用，position是每张图片的位置
    public void onBindViewHolder(ViewHolder holder, int position) {
        Results results = mResultList.get(position);//获取当前项的Results实例
        holder.description.setText(results.getDesc());
        Glide.with(mContext).load(results.getUrl()).into(holder.resultImage);

    }

    @Override//一定不能return 0；
    public int getItemCount() {
        return mResultList.size();
    }
}