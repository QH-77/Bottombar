package com.example.a15927.bottombardemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a15927.bottombardemo.R;
import com.example.a15927.bottombardemo.functiontools.ItemGoods;
import com.example.a15927.bottombardemo.item.ItemBuyActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/6/29.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private Context mContext;
    private List<ItemGoods> mgoodsList;

    //内部类ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView user_img;
        TextView goodsName;
        TextView goodsType;
        TextView user_phone;
        TextView description;
        TextView user;

        //获取布局中的实例，  这里的view是指RecyclerView的子项的最外层布局
        public ViewHolder(View view) {
            super( view );
            user = (TextView) view.findViewById( R.id.user );
            user_img = (ImageView) view.findViewById( R.id.user_ima );
            goodsName = (TextView) view.findViewById( R.id.shop_name );
            goodsType = (TextView) view.findViewById( R.id.diff );
            user_phone = (TextView) view.findViewById( R.id.buy_phone );
            description = (TextView) view.findViewById( R.id.buy_des );
        }
    }

    //构造函数
    //这个方法用于把眼展示的数据源传进来，并赋值给一份全局变量mgoodsList,后续的操作都基于这个数据源
    public ShopAdapter(Context context,List<ItemGoods> goodsList) {
        mContext = context;
        mgoodsList = goodsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //动态加载布局 ，   首先将item_buy布局加载进来，
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_buy, parent, false );
        //再创建ViewHolder的实例，并将加载的布局传入到构造函数中，
        final ShopAdapter.ViewHolder holder = new ShopAdapter.ViewHolder( view );
        //点击事件
        view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent( mContext, ItemBuyActivity.class );
                ItemGoods itemBuy = mgoodsList.get( position );
                Bundle data = new Bundle(  );
                data.putSerializable( "itemBuy",itemBuy );
                intent.putExtras( data );
                mContext.startActivity( intent );
            }
        } );
        //最后将ViewHolder的实例返回
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //通过position参数得到当前的子项实例
        ItemGoods goods = mgoodsList.get( position );
        holder.user.setText( goods.getUname() );
        //设置数据
        String img = goods.getGoodsImg();
        if(img == null){
            holder.user_img.setImageResource( R.drawable.kimg );
        }else{
            Glide.with( mContext ).load( goods.getGoodsImg() ).centerCrop().error( R.drawable.ic_launcher ).into( holder.user_img );
        }
        holder.goodsName.setText( goods.getGoodsName() );
        holder.goodsType.setText( goods.getGoodsTypeName() );
        holder.user_phone.setText( goods.getUphone() );
        if(goods.getDescription().length() == 0){
            holder.description.setText( "暂时没有任何备注哦！" );
        }else{
            holder.description.setText( goods.getDescription() );
        }
    }

    @Override
    public int getItemCount() {
        return mgoodsList.size();
    }
}
