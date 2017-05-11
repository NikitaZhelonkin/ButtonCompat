package ru.nikitazhelonkin.buttoncompat;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by nikita on 11.05.17.
 */

class ButtonCompatGingerbread extends ButtonCompatImpl {

    private Drawable mForeground;

    private ShadowDrawable mShadowDrawable;

    public ButtonCompatGingerbread(View view) {
        super(view);
    }

    @Override
    void init(AttributeSet attrs) {

        Context context = getView().getContext();

        TypedArray a = getView().getContext().obtainStyledAttributes(attrs, R.styleable.ButtonCompat);


        int elevation = a.getDimensionPixelSize(R.styleable.ButtonCompat_compat_elevation,
                context.getResources().getDimensionPixelSize(R.dimen.button_compat_default_elevation));
        int radius = a.getDimensionPixelSize(R.styleable.ButtonCompat_compat_elevation_radius,
                context.getResources().getDimensionPixelSize(R.dimen.button_compat_corner_material));
        float inset = a.getDimensionPixelSize(R.styleable.ButtonCompat_compat_elevation_inset,
                context.getResources().getDimensionPixelSize(R.dimen.button_compat_inset_horizontal_material));

        mShadowDrawable = new ShadowDrawable(context.getResources(),
                inset,
                radius,
                elevation,
                elevation);
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
        mShadowDrawable.setBounds(0, 0, getView().getWidth(), getView().getHeight());
        mShadowDrawable.draw(canvas);
    }

    @Override
    void onDrawOver(Canvas canvas) {
        if (mForeground != null) {
            mForeground.setBounds(0, 0, getView().getWidth(), getView().getHeight());
            mForeground.draw(canvas);
        }
    }


    @Override
    void drawableStateChanged() {
        if (mForeground != null && mForeground.isStateful()) {
            mForeground.setState(getView().getDrawableState());
        }
    }
}
