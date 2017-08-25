package com.example.yuzelli.bookkeepmananger.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;


import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.utils.DensityUtils;

import java.util.Random;

/**
 * Created by Administrator on 2017/1/12.
 * 刮刮乐效果图
 * @author 李秉龙
 */

public class MyPorter extends ImageView {
    private Bitmap mBitmap;
    private Bitmap mOut;
    private Paint mPaint;
    private Path mPath;
    private Canvas mCanvas;
    private Context context;
    private static int[] verificationImg = {
            R.drawable.timg1,R.drawable.timg2,R.drawable.timg3,R.drawable.timg4,
            R.drawable.timg5,R.drawable.timg6,R.drawable.timg7 ,R.drawable.timg8,
            R.drawable.timg9,R.drawable.timg10,R.drawable.timg11,R.drawable.timg12,
            R.drawable.timg13,R.drawable.timg14,R.drawable.timg15,R.drawable.timg16,
            R.drawable.timg17 ,R.drawable.timg18,R.drawable.timg19,R.drawable.timg20,
            R.drawable.timg21,R.drawable.timg22,R.drawable.timg23,R.drawable.timg24};
    private static String[] verificationString = {
            "26c8","8p34","86n5","464x","ne58","3nwd",
            "828f","pdmn","ptd3","d34x","pe5x","n3n2",
            "pm85","x4ne","w7bm","464x","ne58","3nwd",
            "26c8","8p34","86n5","464x","ne58","3nwd"
     };
    private static int verificationIndex;
    public MyPorter(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public void refreshCode(){
        Random rand = new Random();
        verificationIndex = rand.nextInt(24);
        mBitmap = BitmapFactory.decodeResource(getResources(), verificationImg[verificationIndex]);
        mBitmap = zoomImage(mBitmap, DensityUtils.dp2px(context,90f),DensityUtils.dp2px(context,35f));
        mOut= Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mOut);
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(50);
        mPaint.setStyle(Paint.Style.STROKE);
//        让笔触和连接处更加圆滑一些
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mPath = new Path();

        Random rand = new Random();
        verificationIndex = rand.nextInt(24);
        mBitmap = BitmapFactory.decodeResource(getResources(), verificationImg[verificationIndex]);
        mBitmap = zoomImage(mBitmap, DensityUtils.dp2px(context,90f),DensityUtils.dp2px(context,35f));
        mOut= Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mOut);
        mCanvas.drawColor(Color.GRAY);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());

                break;
        }
        mCanvas.drawPath(mPath,mPaint);
        invalidate();
        return true;
    }
    /***
     * 图片的缩放方法
     *
     * @param bgimage
     *            ：源图片资源
     * @param newWidth
     *            ：缩放后宽度
     * @param newHeight
     *            ：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,0,0,null);
        canvas.drawBitmap(mOut,0,0,null);
    }

    public static String verification(){
        return verificationString[verificationIndex];
    }
}
