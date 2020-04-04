package com.qibu.sdk.myapplication.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BottomView extends View {

    Paint paint = new Paint();
    String color ;

    public BottomView(Context context) {
        super(context);
        init();
    }

    public BottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        paint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle((float) getMeasuredWidth() / 2, (float) getMeasuredHeight() / 2,
                (float) getMeasuredWidth()/2, paint);

    }

    public void setColor(String color){
        this.color = color ;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }

}
