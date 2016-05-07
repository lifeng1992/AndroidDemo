package com.example.xloger.animationdemoa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lf on 16/5/7.
 */
public class RunBall extends View {
    //
    private float scale = 0;
    private float ballSize = 20f;
    private int count = 0;
    private RectF rect_2 = new RectF(0, 0, 1, 1);
    // 1.创建一个画笔
    private Paint mPaint = new Paint();
    private Paint mPaint_2 = new Paint();
    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.WHITE);        //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(4f);          //设置画笔宽度为10px

        mPaint_2.setColor(Color.BLUE);
        mPaint_2.setStyle(Paint.Style.FILL);
        mPaint_2.setStrokeWidth(4f);
    }

    public RunBall(Context context) {
        super(context);
    }

    public RunBall(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = canvas.getWidth();
        float height = canvas.getHeight();

        float position_x = scale * width;
        float posttion_y = height / 2;
        RectF rect = new RectF(position_x,
                posttion_y-ballSize/2, position_x+ballSize, posttion_y+ballSize/2);
        canvas.drawArc(rect, 0, 360, true, mPaint);

        count = (int)(scale*6);
        for (int i=0; i<=count; i++) {
            System.out.println(i);
            rect_2.set(i * width / 6, posttion_y - ballSize / 2,
                    i * width / 6 + ballSize, posttion_y + ballSize / 2);
            canvas.drawArc(rect_2, 0, 360, true, mPaint_2);
        }
        invalidate();
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
