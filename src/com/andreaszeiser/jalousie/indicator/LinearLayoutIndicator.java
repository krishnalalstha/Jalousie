package com.andreaszeiser.jalousie.indicator;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class LinearLayoutIndicator extends LinearLayout implements
		IndicatorElement {

	private int mState = IndicatorElement.STATE_COLLAPSED;

	private ArrayList<IndicatorElement> mIndicatorElements = new ArrayList<IndicatorElement>();

	public LinearLayoutIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}

	public LinearLayoutIndicator(Context context) {
		super(context);

		init();
	}

	private void init() {
		setClickable(true);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		final int childCount = getChildCount();
		View view;
		for (int i = 0; i < childCount; i++) {
			view = getChildAt(i);
			if (view instanceof IndicatorElement) {
				mIndicatorElements.add((IndicatorElement) view);
			}
		}
	}

	@Override
	public int getState() {
		return mState;
	}

	@Override
	public void setState(int indicatorState) {
		switch (indicatorState) {
		case IndicatorElement.STATE_COLLAPSED: // valid states
		case IndicatorElement.STATE_EXPANDED:

			mState = indicatorState;

			for (IndicatorElement el : mIndicatorElements) {
				el.setState(mState);
			}

			break;

		default: // do nothing
			break;
		}
	}

	@Override
	public void show() {
		setVisibility(View.VISIBLE);
	}

	@Override
	public void hide() {
		setVisibility(View.GONE);
	}

}
