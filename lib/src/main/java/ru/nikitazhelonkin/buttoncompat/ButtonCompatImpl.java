package ru.nikitazhelonkin.buttoncompat;

import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by nikita on 11.05.17.
 */

abstract class ButtonCompatImpl {

    private View mView;

    public ButtonCompatImpl(View view){
        mView = view;
    }

    public View getView() {
        return mView;
    }

    abstract void init(AttributeSet attrs);

    abstract void onDrawBehind(Canvas canvas);

    abstract void onDrawOver(Canvas canvas);

    abstract void drawableStateChanged() ;
}
