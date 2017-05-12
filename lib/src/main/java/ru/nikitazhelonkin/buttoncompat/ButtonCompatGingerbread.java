package ru.nikitazhelonkin.buttoncompat;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by nikita on 11.05.17.
 */

class ButtonCompatGingerbread extends ButtonCompatImpl {

    private Drawable mForeground;

    private ShadowDrawable mShadowDrawable;

    private int mElevation;
    private int mRadius;
    private float mInset;

    public ButtonCompatGingerbread(View view) {
        super(view);
    }

    @Override
    void init(AttributeSet attrs, int defStyleAttr) {

        Context context = getView().getContext();

        TypedArray a = getView().getContext().obtainStyledAttributes(attrs, R.styleable.ButtonCompat, defStyleAttr, 0);


        mElevation = a.getDimensionPixelSize(R.styleable.ButtonCompat_compatElevation,
                context.getResources().getDimensionPixelSize(R.dimen.button_compat_default_elevation));
        mRadius = a.getDimensionPixelSize(R.styleable.ButtonCompat_compatElevationRadius,
                context.getResources().getDimensionPixelSize(R.dimen.button_compat_corner_material));
        mInset = a.getDimensionPixelSize(R.styleable.ButtonCompat_compatElevationInset,
                context.getResources().getDimensionPixelSize(R.dimen.button_compat_inset_horizontal_material));

        final Drawable d = a.getDrawable(R.styleable.ButtonCompat_foreground);
        if (d != null) {
            setForeground(d);
        }
        a.recycle();
    }

    private void setForeground(Drawable d) {
        if (mForeground != d) {
            if (mForeground != null) {
                mForeground.setCallback(null);
                getView().unscheduleDrawable(mForeground);
            }

            mForeground = d;

            if (d != null) {
                d.setCallback(getView());
                if (d.isStateful()) {
                    d.setState(getView().getDrawableState());
                }
            }
            getView().invalidate();
        }
    }

    @Override
    void onDrawBehind(Canvas canvas) {
        if (mShadowDrawable != null) {
            mShadowDrawable.draw(canvas);
        }
    }

    @Override
    void onDrawOver(Canvas canvas) {
        if (mForeground != null) {
            mForeground.draw(canvas);
        }
    }


    @Override
    void drawableStateChanged() {
        StateListDrawable stateListDrawable;
        if (mForeground != null && mForeground.isStateful()) {
            mForeground.setState(getView().getDrawableState());
        }
    }

    @Override
    void onSizeChanged() {
        mShadowDrawable = new ShadowDrawable(getView().getContext().getResources(),
                mInset,
                mRadius,
                mElevation,
                mElevation);
        mShadowDrawable.setBounds(0, 0, getView().getWidth(), getView().getHeight());

        if (mForeground != null) {
            mForeground.setBounds(0, 0, getView().getWidth(), getView().getHeight());
        }
    }
}
