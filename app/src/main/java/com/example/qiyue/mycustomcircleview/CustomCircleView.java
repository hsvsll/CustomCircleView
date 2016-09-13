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
import android.support.v4.content.Loader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by qiyue on 16/9/10.
 */
public class CustomCircleView extends View {
    private Paint mPaint;
    private Paint mInsidePaint;
    private RectF oval;
    private float customCircleRadius;
    private float mStrokeWidth;
    private float sweepAngle ;
    private int viewWidth;
    private int viewHeight;
    private float customCirclePointX;
    private float customCirclePointY;
    private MaskFilter maskFilter;

    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
        initRectF();
    }

    public void setStrokeWidth(float strokeWidth) {
        this.mStrokeWidth = strokeWidth;
        initPaint();
    }

    public CustomCircleView(Context context) {
        this(context,null);

    }

    public CustomCircleView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        mPaint = new Paint();

        maskFilter = new BlurMaskFilter(20, BlurMaskFilter.Blur.INNER);

        mInsidePaint = new Paint();

        oval =  new RectF();

        initPaint();

        initRectF();

        Log.i("TAG","initView");
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setMaskFilter(maskFilter);

        mInsidePaint.setStyle(Paint.Style.STROKE);
        mInsidePaint.setAntiAlias(true);
        mInsidePaint.setStrokeCap(Paint.Cap.ROUND);
        mInsidePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));


        mPaint.setStrokeWidth(mStrokeWidth);
        mInsidePaint.setStrokeWidth(mStrokeWidth);

    }

    private void initRectF() {
        oval.left =  mStrokeWidth;
        oval.top =  mStrokeWidth;
        oval.right = viewWidth - mStrokeWidth;
        oval.bottom = viewHeight - mStrokeWidth;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        customCircleRadius = w / 2 - mStrokeWidth;

        customCirclePointX = w / 2;
        customCirclePointY = h / 2;
        viewWidth = w;
        viewHeight = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(customCirclePointX, customCirclePointY, customCircleRadius, mPaint);
        canvas.drawArc(oval,-90,sweepAngle,false,mInsidePaint);
    }


//    public void setCirclePoint(float curPointStartAngle){
//        this.lastPointStartAngle = -90 + curPointStartAngle;
//        if(sweepAngle >= 0 && sweepAngle <= 90){
//            endPoint.setX ((float) (customCirclePoint.getX() + customCircleRadius * Math.sin(Math.toRadians(sweepAngle))));
//            endPoint.setY((float) (customCirclePoint.getY() - customCircleRadius * Math.cos(Math.toRadians(sweepAngle))));
//        }
//        else if(sweepAngle > 90 && sweepAngle <= 180){
//            endPoint.setX ((float) (customCirclePoint.getX() + customCircleRadius * Math.sin(Math.toRadians(180 - sweepAngle))));
//            endPoint.setY((float) (customCirclePoint.getY() + customCircleRadius * Math.cos(Math.toRadians(180 - sweepAngle))));
//        }
//        else if(sweepAngle > 180 && sweepAngle <= 270){
//            endPoint.setX ((float) (customCirclePoint.getX() - customCircleRadius * Math.cos(Math.toRadians(270 - sweepAngle))));
//            endPoint.setY((float) (customCirclePoint.getY() + customCircleRadius * Math.sin(Math.toRadians(270 - sweepAngle))));
//        }
//        else if(sweepAngle > 270 && sweepAngle <= 360){
//            endPoint.setX ((float) (customCirclePoint.getX() - customCircleRadius * Math.sin(Math.toRadians(360 - sweepAngle))));
//            endPoint.setY((float) (customCirclePoint.getY() - customCircleRadius * Math.cos(Math.toRadians(360 - sweepAngle))));
//        }
//    }

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
