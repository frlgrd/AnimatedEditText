package frlgrd.animatededittext;

import android.view.View;
import android.widget.LinearLayout;

class ViewUtils {

	static int getVerticalMargin(View view) {
		LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
		return layoutParams.topMargin + layoutParams.bottomMargin;
	}

	static void setViewHeight(View view, int viewHeight) {
		LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
		layoutParams.height = viewHeight;
		view.setLayoutParams(layoutParams);
	}

	static void doAfterLayout(final View view, final Runnable runnable) {
		view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
			@Override
			public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
				view.removeOnLayoutChangeListener(this);
				runnable.run();
			}
		});
	}
}
