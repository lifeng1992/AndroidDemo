package com.example.xloger.animationdemoa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lf on 16/5/6.
 */
public class ValueLine extends View {
    //
    private float scale = 0;
    // 1.创建一个画笔
    private Paint mPaint = new Paint();
    private Path path = new Path();
    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLUE);        //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(4f);          //设置画笔宽度为10px
    }

    public ValueLine(Context context) {
        super(context);
    }

    public ValueLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine((float)canvas.getWidth()/2, 0, (float)canvas.getWidth()/2,
                (float)(scale*canvas.getHeight()), mPaint);
        invalidate();
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
