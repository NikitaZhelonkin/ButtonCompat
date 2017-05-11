package ru.nikitazhelonkin.buttoncompat;

import android.graphics.Canvas;
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
    void init(AttributeSet attrs) {
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
    void drawableStateChanged() {
        //do nothing
    }
}
