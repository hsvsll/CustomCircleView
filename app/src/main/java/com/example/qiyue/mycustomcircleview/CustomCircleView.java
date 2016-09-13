package com.example.qiyue.mycustomcircleview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiyue on 16/9/10.
 */
public class CustomCircleView extends View {
    private Paint mPaint;
    private Paint mInsidePaint;
    private RectF oval;
    private RectF startOval;
    private RectF endOval;
    private float customCircleRadius;
    private float mStrokeWidth;
    private MyPoint endPoint;
    private MyPoint customCirclePoint;
    private float swwpAngle ;
    private float lastPointStartAngle;
    private int viewWidth;
    private int viewHeight;
    private float customCirclePointX;
    private float customCirclePointY;
    private MaskFilter maskFilter;

    public void setSwwpAngle(float swwpAngle) {
        this.swwpAngle = swwpAngle;
        initRectF();
    }

    public void setmStrokeWidth(float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        initPaint();
    }

//    public void setCustomCirclePoint(float x, float y) {
//        this.customCirclePoint = new MyPoint(x ,y);
//        endPoint = new MyPoint();
//    }

//    public void setCustomCircleRadius(float customCircleRadius) {
//        this.customCircleRadius = customCircleRadius;
//    }

    public CustomCircleView(Context context) {
        this(context, null);
    }

    public CustomCircleView(Context context, AttributeSet attrs) {
        this(context,null,0);
    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
//        startOval = new RectF();
//        startOval.left = customCirclePoint.getX() - mStrokeWidth/2;
//        startOval.top = customCirclePoint.getY() - customCircleRadius - mStrokeWidth/2;
//        startOval.right = customCirclePoint.getX() + mStrokeWidth/2;
//        startOval.bottom = customCirclePoint.getY() - customCircleRadius + mStrokeWidth/2;
//        endOval = new RectF();

        mPaint = new Paint();
        maskFilter = new BlurMaskFilter(20, BlurMaskFilter.Blur.INNER);

        mInsidePaint = new Paint();
        oval =  new RectF();

        initPaint();


        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setMaskFilter(maskFilter);

        mInsidePaint.setStyle(Paint.Style.STROKE);
        mInsidePaint.setAntiAlias(true);
        mInsidePaint.setStrokeCap(Paint.Cap.ROUND);
        mInsidePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
    }

    private void initPaint() {
        mPaint.setStrokeWidth(mStrokeWidth);

        mInsidePaint.setStrokeWidth(mStrokeWidth);

        initRectF();
    }

    private void initRectF() {
        oval.left = 0 ;
        oval.top = 0;
        oval.right = viewWidth + mStrokeWidth;
        oval.bottom = viewHeight + mStrokeWidth;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        customCircleRadius = w / 2;

        customCirclePointX = w / 2;
        customCirclePointY = h / 2;
        viewWidth = w;
        viewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(customCirclePointX, customCirclePointY, customCircleRadius, mPaint);
        canvas.drawArc(oval,-90,swwpAngle,false,mInsidePaint);
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
