package com.example.xloger.animationdemoa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lf on 16/5/7.
 */
public class ValueCircle extends View {
    //
    private float rad = 0;
    private float paintWidth = 40f;
    // 1.创建一个画笔
    private Paint mPaint = new Paint();
    private Path path = new Path();
    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLUE);            //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);    //设置画笔模式为填充
        mPaint.setStrokeWidth(paintWidth);      //设置画笔宽度为10px
    }

    public ValueCircle(Context context) {
        super(context);
    }

    public ValueCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF mRectF = new RectF(paintWidth/2, paintWidth/2,
                canvas.getWidth()-paintWidth/2, canvas.getHeight()-paintWidth/2);
        path.moveTo(canvas.getWidth()/2,canvas.getHeight());
        path.arcTo(mRectF, 90, rad);
        canvas.drawPath(path, mPaint);
        invalidate();
    }

    public float getRad() {
        return this.rad;
    }

    public void setRad(float rad) {
        this.rad = rad;
    }
}
