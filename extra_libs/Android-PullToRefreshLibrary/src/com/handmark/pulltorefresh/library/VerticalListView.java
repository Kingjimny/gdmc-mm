/**
 *
 * project: OPlay
 *
 *
 * ========================================================================
 * amend date			amend user			amend reason
 * 2013-3-11			    CsHeng		
 *
 */

package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 只拦截Vertical Scroll ^^，不阻拦Horizontal Scroll
 * 适用于内部有viewpager的情况
 *
 * @author CsHeng
 * @date 2013-6-8
 */
public class VerticalListView extends ListView {

	private float xDistance, yDistance, lastX, lastY;

	public VerticalListView(Context context) {
		super(context);
	}

	public VerticalListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				xDistance = yDistance = 0f;
				lastX = ev.getX();
				lastY = ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				final float curX = ev.getX();
				final float curY = ev.getY();
				xDistance += Math.abs(curX - lastX);
				yDistance += Math.abs(curY - lastY);
				lastX = curX;
				lastY = curY;
				if (xDistance > yDistance)
					return false;
		}
		return super.onInterceptTouchEvent(ev);
	}

}