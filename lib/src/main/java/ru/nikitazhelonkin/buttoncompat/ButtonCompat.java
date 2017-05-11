package ru.nikitazhelonkin.buttoncompat;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by nikita on 08.02.17.
 */

public class ButtonCompat extends AppCompatButton {

    private ButtonCompatImpl mImpl;

    public ButtonCompat(Context context) {
        super(context);
        init(context, null);
    }

    public ButtonCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ButtonCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        getImpl().init(attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        getImpl().onDrawBehind(canvas);
        super.draw(canvas);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        getImpl().onDrawOver(canvas);
        super.onDraw(canvas);
    }

    private ButtonCompatImpl getImpl() {
        if (mImpl == null) {
            mImpl = createImpl();
        }
        return mImpl;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().drawableStateChanged();
    }

    private ButtonCompatImpl createImpl() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new ButtonCompatLollipop(this);
        } else {
            return new ButtonCompatGingerbread(this);
        }
    }
}
