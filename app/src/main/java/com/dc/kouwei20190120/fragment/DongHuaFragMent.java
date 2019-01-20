package com.dc.kouwei20190120.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.dc.kouwei20190120.R;

public class DongHuaFragMent extends Fragment {

    private ImageView img;
    private Button setStart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.donghuafrag,container,false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        img = view.findViewById(R.id.img);
        setStart = view.findViewById(R.id.setStart);
        setStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //旋转
                ObjectAnimator rotationY = ObjectAnimator.ofFloat(img, "rotation", 0, 360);
                //缩放
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(img, "scaleX", 1, 2);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(rotationY).with(scaleX);
                animatorSet.setDuration(5000);
                animatorSet.start();
            }
        });
    }


}
