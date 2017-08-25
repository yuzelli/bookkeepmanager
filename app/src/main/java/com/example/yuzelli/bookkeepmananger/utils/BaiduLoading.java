package com.example.yuzelli.bookkeepmananger.utils;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.yuzelli.bookkeepmananger.R;


/**
 * Created by 51644 on 2017/5/3.
 */


public class BaiduLoading extends View {
    /**
     * 开始执行的第一个动画的索引，
     * 由于第一个和第二个同时当执行，
     * 当第一遍执行完毕后就让第一个停下来在中间位置，换原来中间位置的第三个开始执行动画，
     * 以此类推，当第二遍执行完毕后第二个停下来，中间位置的开始执行动画。
     */

    private int changeIndex = 0;
    /**
     * 交换执行动画的颜色数组
     */
    private int colors[] = new int[]{Color.parseColor("#2698E8"), Color.parseColor("#46AEF7"),
            Color.parseColor("#A3D9FF")};
    /**
     * 动画所执行的最大偏移量（即中间点和最左边的距离）
     */
    private Float maxWidth;
    /**
     * 三个圆的半径
     */
    private Float radius;
    /**
     * 当前偏移的X坐标
     */
    private Float currentX;
    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 属性动画
     */
    private ValueAnimator valueAnimator;

    private static Dialog dialog;

    public BaiduLoading(Context context) {
        this(context, null);
    }

    public BaiduLoading(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaiduLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        startAnimator(context);
    }

    /**
     * dp转px
     *
     * @param context
     * @return
     */


    public static float dp2px(Context context, float dpVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 用属性动画实现位移动画
     */
    private void startAnimator(Context context) {
        maxWidth = dp2px(context, 40);
        currentX = dp2px(context, 40);
        radius = dp2px(context, 7);
        valueAnimator = ValueAnimator.ofFloat(0f, maxWidth, 0);
        valueAnimator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        currentX = (Float) animation.getAnimatedValue();
                        invalidate();
                    }
                });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                changePoint(changeIndex);
            }
        });

        valueAnimator.setInterpolator(new LinearInterpolator());
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setDuration(800);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        /**画左边的圆**/
        paint.setColor(colors[0]);
        canvas.drawCircle(centerX - currentX, centerY, radius, paint);

        /**画右边的圆**/
        paint.setColor(colors[1]);
        canvas.drawCircle(centerX + currentX, centerY, radius, paint);

        /**画中间的圆**/
        paint.setColor(colors[2]);
        canvas.drawCircle(centerX, centerY, radius, paint);
    }

    private static int creatNum = 0;
    private static Context mContext ;
    public static void onBeiginDialog(Context context) {
        if (context == null) {
            return;
        }
        if (context ==mContext){
            creatNum ++;
        }else{
            creatNum =0;
        }
        mContext = context;

        if (dialog == null || !dialog.isShowing()) {
            dialog = new Dialog(context, R.style.PhotoDialog);
            final View view = LayoutInflater.from(context).inflate(R.layout.view_loading, null);
            dialog.setContentView(view);
            dialog.show();
        }
    }

    public static void onStopDialog() {
        if (creatNum==0) {
            if (dialog!=null||dialog.isShowing()){
                dialog.dismiss();
                mContext = null;
            }
        }
        creatNum--;

    }


    /**
     * 每次让先执行动画的目标和中间停止的动画目标交换 * * @param a 最先执行 的动画的索引
     */
    private void changePoint(int a) {
        int temp = colors[2];
        colors[2] = colors[a];
        colors[a] = temp;
        if (a == 0) {
            changeIndex = 1;
        } else {
            changeIndex = 0;
        }
    }

    /**
     * 在View销毁时停止动画
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        valueAnimator.cancel();
    }
}
