package ru.nikitazhelonkin.buttoncompat;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by nikita on 11.05.17.
 */

class ButtonCompatLollipop extends ButtonCompatImpl {

    public ButtonCompatLollipop(View view) {
        super(view);
    }

    @Override
    void init(AttributeSet attrs, int defStyleAttr) {
        //do nothing
    }

    @Override
    void onDrawBehind(Canvas canvas) {
        //do nothing
    }

    @Override
    void onDrawOver(Canvas canvas) {
        //do nothing
    }

    @Override
    boolean verifyDrawable(Drawable drawable) {
        return false;
    }

    @Override
    void jumpDrawablesToCurrentState() {
        //do nothing
    }

    @Override
    void drawableStateChanged() {
        //do nothing
    }

    @Override
    void onSizeChanged() {
        //do nothing
    }
}
