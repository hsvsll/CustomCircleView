package com.example.qiyue.mycustomcircleview;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private float mMaxAngle = 90;
    private ValueAnimator valueAnimator;
    private CustomCircleView circleView;
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
                mMaxAngle += 45;
                valueAnimator.start();
            }
        });
    }
    private void initCircle() {
        circleView = (CustomCircleView) findViewById(R.id.my_custom_view);
        circleView.setStrokeWidth(40);
        valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(5000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fraction = valueAnimator.getAnimatedFraction();
                float curAngle = mMaxAngle * fraction;
                circleView.setSweepAngle(curAngle);
                circleView.postInvalidate();
            }
        });

    }
}
