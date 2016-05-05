package com.example.xloger.androidanimatedemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TextView)findViewById(R.id.button_1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rectangleToCircle();
                v.setOnClickListener(null);
            }
        });
    }

    private void rectangleToCircle() {
        TextView vi = (TextView)findViewById(R.id.button_1);

        // 属性动画,可行,但是要对View进行一次包装
        final ImageView snail = (ImageView)findViewById(R.id.ic_snail);
        // 获取 snail ico的位置
        int snail_y = snail.getTop();
        int vi_y = vi.getTop();

        int length = snail.getHeight();
        int height = vi.getLayoutParams().height;
        int width = vi.getLayoutParams().width;
        ViewWrapper wrapper = new ViewWrapper(vi);

        ObjectAnimator reduce = ObjectAnimator.ofInt(wrapper, "width", width, length);
        ObjectAnimator increase = ObjectAnimator.ofInt(wrapper, "height", height, length);
        ObjectAnimator move = ObjectAnimator.ofFloat(wrapper, "translationY", 0f, snail_y-vi_y);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(increase).with(reduce).with(move);
        animSet.setDuration(1000);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(getApplicationContext(), "动画结束", Toast.LENGTH_SHORT).show();
                rotateSnail();
            }
        });
        Log.d("Eve", "Animation Start");
        animSet.start();
    }

    private void rotateSnail() {
        RotateAnimation animationA = new RotateAnimation(0, 1770,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//        ScaleAnimation animationB = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
//                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        //设置动画持续时间
        animationA.setDuration(2000);
        //设置动画结束后效果保留
        animationA.setFillAfter(true);
        //找到对象，开启动画
        ImageView snail = (ImageView) findViewById(R.id.ic_snail);
        snail.setVisibility(View.VISIBLE);
        snail.startAnimation(animationA);
    }

    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }

        public int getHeight() {
            return mTarget.getLayoutParams().height;
        }

        public void setHeight(int Height) {
            mTarget.getLayoutParams().height = Height;
            mTarget.requestLayout();
        }

        public float getTranslationY() {
            return mTarget.getTranslationY();
        }

        public void setTranslationY(float y) {
            mTarget.setTranslationY(y);
        }
    }

}
