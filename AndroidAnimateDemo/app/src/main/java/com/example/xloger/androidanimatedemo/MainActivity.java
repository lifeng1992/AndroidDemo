package com.example.xloger.androidanimatedemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        ((TextView)findViewById(R.id.button_1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rectangleToCircle();
                v.setOnClickListener(null);
            }
        });
    }

    private void initView() {
        DisplayMetrics  dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenHeight = dm.heightPixels;
        screenWidth = dm.widthPixels;
    }

    private void rectangleToCircle() {
        TextView vi = (TextView)findViewById(R.id.button_1);

        // 属性动画,可行,但是要对View进行一次包装
        final ImageView snail = (ImageView)findViewById(R.id.ic_snail);
        int length = snail.getHeight();
        int height = vi.getLayoutParams().height;
        int width = vi.getLayoutParams().width;
        ViewWrapper wrapper = new ViewWrapper(vi);
        ObjectAnimator reduce = ObjectAnimator.ofInt(wrapper, "width", width, length);
        ObjectAnimator increase = ObjectAnimator.ofInt(wrapper, "height", height, length);
        ObjectAnimator move = ObjectAnimator.ofFloat(wrapper, "translationY", 0f, -400f);
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
        animSet.start();

        // 按比例缩放,不行
//        vi.setScaleX(0.5f);
//        vi.setScaleY(2.0f);
    }

    private void rotateSnail() {
        RotateAnimation animation = new
                RotateAnimation(0, 720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        //设置动画持续时间
        animation.setDuration(2000);
        //设置动画结束后效果保留
        animation.setFillAfter(true);
        //找到对象，开启动画
        ImageView snail = (ImageView) findViewById(R.id.ic_snail);
        snail.startAnimation(animation);
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
