package example.codeclan.com.fruitmachine.controls;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import example.codeclan.com.fruitmachine.viewmodels.SymbolItemViewModel;

/**
 * Created by user on 11/07/2017.
 */

//Code lifted verbatim from: http://danosipov.com/?p=604

public class ScrollDisabledListView extends ListView
{

    private int mPosition;
    ObservableField<SymbolItemViewModel> symbolItem = new ObservableField<SymbolItemViewModel>();

    public ScrollDisabledListView(Context context) {
        super(context);
    }

    public ScrollDisabledListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollDisabledListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int actionMasked = ev.getActionMasked() & MotionEvent.ACTION_MASK;

        if (actionMasked == MotionEvent.ACTION_DOWN) {
            // Record the position the list the touch landed on
            mPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
            return super.dispatchTouchEvent(ev);
        }

        if (actionMasked == MotionEvent.ACTION_MOVE) {
            // Ignore move events
            return true;
        }

        if (actionMasked == MotionEvent.ACTION_UP) {
            // Check if we are still within the same view
            if (pointToPosition((int) ev.getX(), (int) ev.getY()) == mPosition) {
                super.dispatchTouchEvent(ev);
            } else {
                // Clear pressed state, cancel the action
                setPressed(false);
                invalidate();
                return true;
            }
        }

        return super.dispatchTouchEvent(ev);
    }
}
