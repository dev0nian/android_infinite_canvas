package stuff.dev.infinitecanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawingPad extends View {
    public enum Mode {
        MODE_PEN,
        MODE_MOVE
    }

    private int mWidth;
    private int mHeight;
    private Vector2D mPosition = new Vector2D();
    private float mScale = 1.0f;
    private float mStrokeWidth = 10.0f;
    private Vector2D mPreviousTouch = new Vector2D();

    private ArrayList<Path> mPaths = new ArrayList<>();
    private Paint mPaint = new Paint();
    private Mode mMode = Mode.MODE_PEN;

    public DrawingPad(Context context) {
        super(context);
        init(context);
    }

    public DrawingPad(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DrawingPad(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context inContext) {
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mPosition.set(w * 0.5f, h * 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mPosition.x(), mPosition.y());
        canvas.scale(mScale, mScale);
        for(Path path : mPaths) {
            canvas.drawPath(path, mPaint);
        }
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                fingerDown(event.getX(), event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                if(event.getHistorySize() > 0) {
                    for(int i = 0; i < event.getHistorySize(); ++i) {
                        float x = event.getHistoricalX(i);
                        float y = event.getHistoricalY(i);
                        fingerUpdate(x, y);
                    }
                }
                else {
                    fingerUpdate(event.getX(), event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                fingerUp(event.getX(), event.getY());
                return true;
        }
        return super.onTouchEvent(event);
    }

    public void setMode(Mode inMode) {
        mMode = inMode;
    }

    private void fingerDown(float inX, float inY) {
        mPreviousTouch.set(inX, inY);
        switch (mMode) {
            case MODE_MOVE:
                break;
            case MODE_PEN:
                Vector2D convertedTouch = new Vector2D(inX, inY).subtract(mPosition);
                Path path = new Path();
                convertedTouch.scale(1.0f / mScale);
                path.moveTo(convertedTouch.x(), convertedTouch.y());
                mPaths.add(path);
                break;
        }
        invalidate();
    }

    private void fingerUpdate(float inX, float inY) {
        switch (mMode) {
            case MODE_MOVE:
                Vector2D diffVec = new Vector2D(inX, inY).subtract(mPreviousTouch);
                mPosition.add(diffVec);
                break;
            case MODE_PEN:
                Vector2D convertedTouch = new Vector2D(inX, inY).subtract(mPosition);
                convertedTouch.scale(1.0f / mScale);
                Path path = mPaths.get(mPaths.size() - 1);
                path.lineTo(convertedTouch.x(), convertedTouch.y());
                break;
        }
        invalidate();
        mPreviousTouch.set(inX, inY);
    }

    private void fingerUp(float inX, float inY) {
        switch (mMode) {
            case MODE_MOVE:
                break;
            case MODE_PEN:
                Vector2D convertedTouch = new Vector2D(inX, inY).subtract(mPosition);
                convertedTouch.scale(1.0f / mScale);
                Path path = mPaths.get(mPaths.size() - 1);
                path.lineTo(convertedTouch.x(), convertedTouch.y());
                break;
        }
        invalidate();
    }

    public void setScaleFactor(float inScale) {
        mScale = inScale;
        invalidate();
    }
}
