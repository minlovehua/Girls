package com.example.girls;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 媚敏 on 2017/6/16.
 */

public class GirlAdapter  extends RecyclerView.Adapter<GirlAdapter.ViewHolder>{

    private Context mContext;
    private List<Results> mResultList;

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



    public GirlAdapter(List<Results> resultList) {
        mResultList = resultList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.girl_item,parent,false);
            return new ViewHolder(view);
        }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Results results = mResultList.get(position);
        holder.description.setText(results.getDesc());
        Glide.with(mContext).load(results.getUrl()).into(holder.resultImage);

    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }
}
