package com.example.qiyue.mycustomcircleview;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private float mMaxAngle = 200;
    private ValueAnimator valueAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCircle();
        initView();
    }


    private void initView(){
        Button button = (Button) findViewById(R.id.bt_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueAnimator.start();
            }
        });
    }
    private void initCircle() {

        final CustomCircleView circleView = (CustomCircleView) findViewById(R.id.my_custom_view);
        circleView.setCustomCirclePoint(460,440);
        circleView.setCustomCircleRadius(400);
        circleView.setmStrokeWidth(40);
        valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(5000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fraction = valueAnimator.getAnimatedFraction();
                float curAngle = mMaxAngle * fraction;
                circleView.setSwwpAngle(curAngle);
                circleView.setCirclePoint(mMaxAngle , curAngle);
                circleView.postInvalidate();
            }
        });

    }
}