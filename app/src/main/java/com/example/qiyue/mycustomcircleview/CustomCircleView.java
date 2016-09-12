package com.example.qiyue.mycustomcircleview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by qiyue on 16/9/10.
 */
public class CustomCircleView extends View {
    private Paint mPaint;
    private RectF oval;
    private RectF startOval;
    private RectF endOval;
    private float customCircleRadius;
    private float mStrokeWidth;
    private MyPoint endPoint;
    private MyPoint customCirclePoint;
    private float swwpAngle ;
    private float lastPointStartAngle;

    public void setSwwpAngle(float swwpAngle) {
        this.swwpAngle = swwpAngle;
    }

    public void setmStrokeWidth(float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
    }

    public void setCustomCirclePoint(float x, float y) {
        this.customCirclePoint = new MyPoint(x ,y);
        endPoint = new MyPoint();
    }

    public void setCustomCircleRadius(float customCircleRadius) {
        this.customCircleRadius = customCircleRadius;
    }

    public CustomCircleView(Context context) {
        super(context);
    }

    public CustomCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        oval =  new RectF();
        oval.left = customCirclePoint.getX() - customCircleRadius;
        oval.top = customCirclePoint.getY() - customCircleRadius;
        oval.right = customCirclePoint.getX() + customCircleRadius;
        oval.bottom = customCirclePoint.getY() + customCircleRadius;

        startOval = new RectF();
        startOval.left = customCirclePoint.getX() - mStrokeWidth/2;
        startOval.top = customCirclePoint.getY() - customCircleRadius - mStrokeWidth/2;
        startOval.right = customCirclePoint.getX() + mStrokeWidth/2;
        startOval.bottom = customCirclePoint.getY() - customCircleRadius + mStrokeWidth/2;

        endOval = new RectF();

        mPaint = new Paint();
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        MaskFilter maskFilter = new BlurMaskFilter(20, BlurMaskFilter.Blur.INNER);
        mPaint.setMaskFilter(maskFilter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initView();
        canvas.drawCircle(customCirclePoint.getX(),customCirclePoint.getY(),customCircleRadius, mPaint);
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
        canvas.drawArc(oval,-90,swwpAngle,false,mPaint);
//        if(swwpAngle > 0){
//            mPaint.setStyle(Paint.Style.FILL);
//            mPaint.setColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
//            canvas.drawArc(startOval,90,180,true,mPaint);
//        }
//        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawArc(oval,-90,swwpAngle,false,mPaint);
//        mPaint.setStyle(Paint.Style.FILL);
//        endOval.left = endPoint.getX() - mStrokeWidth/2;
//        endOval.top = endPoint.getY() - mStrokeWidth/2;
//        endOval.right = endPoint.getX() + mStrokeWidth/2;
//        endOval.bottom = endPoint.getY() + mStrokeWidth/2;
//        canvas.drawArc(endOval,lastPointStartAngle,180,true,mPaint);
        super.onDraw(canvas);
    }


    public void setCirclePoint(float curPointStartAngle){
        this.lastPointStartAngle = -90 + curPointStartAngle;
        if(swwpAngle >= 0 && swwpAngle <= 90){
            endPoint.setX ((float) (customCirclePoint.getX() + customCircleRadius * Math.sin(Math.toRadians(swwpAngle))));
            endPoint.setY((float) (customCirclePoint.getY() - customCircleRadius * Math.cos(Math.toRadians(swwpAngle))));
        }
        else if(swwpAngle > 90 && swwpAngle <= 180){
            endPoint.setX ((float) (customCirclePoint.getX() + customCircleRadius * Math.sin(Math.toRadians(180 - swwpAngle))));
            endPoint.setY((float) (customCirclePoint.getY() + customCircleRadius * Math.cos(Math.toRadians(180 - swwpAngle))));
        }
        else if(swwpAngle > 180 && swwpAngle <= 270){
            endPoint.setX ((float) (customCirclePoint.getX() - customCircleRadius * Math.cos(Math.toRadians(270 - swwpAngle))));
            endPoint.setY((float) (customCirclePoint.getY() + customCircleRadius * Math.sin(Math.toRadians(270 - swwpAngle))));
        }
        else if(swwpAngle > 270 && swwpAngle <= 360){
            endPoint.setX ((float) (customCirclePoint.getX() - customCircleRadius * Math.sin(Math.toRadians(360 - swwpAngle))));
            endPoint.setY((float) (customCirclePoint.getY() - customCircleRadius * Math.cos(Math.toRadians(360 - swwpAngle))));
        }
    }

    class MyPoint {
        float x;
        float y;

        public MyPoint(){

        }
        public MyPoint(float x , float y){
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }
    }

}
