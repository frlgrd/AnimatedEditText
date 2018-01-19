package frlgrd.richedittext;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class RichEditText extends LinearLayout {

	public RichEditText(Context context) {
		super(context);
		init();
	}

	public RichEditText(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RichEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		inflate(getContext(), R.layout.rich_edit_text, this);
	}

}
