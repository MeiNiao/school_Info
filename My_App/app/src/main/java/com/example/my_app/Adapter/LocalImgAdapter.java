package com.example.my_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.adapter.LBaseAdapter;
import com.example.my_app.R;

public class LocalImgAdapter implements LBaseAdapter<Integer> {
    private Context mContext;
    public LocalImgAdapter(Context context) {
        mContext=context;
    }

    @Override
    public View getView(LMBanners lBanners, Context context, int position, Integer data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_image);
        imageView.setImageResource(data);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return view;
    }
}
