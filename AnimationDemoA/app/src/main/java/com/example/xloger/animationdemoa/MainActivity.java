package com.example.xloger.animationdemoa;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView() {
        ValueCircle circle_1 = (ValueCircle)findViewById(R.id.circle_1);
        ValueLine line_1 = (ValueLine)findViewById(R.id.line_1);
        ValueLine line_2 = (ValueLine)findViewById(R.id.line_2);

        ObjectAnimator animator_1 = ObjectAnimator.ofFloat(line_1, "scale", 0, 1);
        ObjectAnimator animator_2 = ObjectAnimator.ofFloat(circle_1, "rad", 0, 355);
        ObjectAnimator animator_3 = ObjectAnimator.ofFloat(line_2, "scale", 0, 1);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animator_1).before(animator_2);
        animSet.play(animator_2).before(animator_3);
        animSet.setDuration(1000);
        animSet.start();

    }
}
