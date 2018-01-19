package frlgrd.richedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RichEditText extends LinearLayout {

	private TextView hintText;
	private EditText editText;

	private String hint;
	private int icon;

	private boolean isExpanded = false;

	private Animation expandInputZoneAnimation;

	public RichEditText(Context context) {
		super(context);
		init(null);
	}

	public RichEditText(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public RichEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	private void init(@Nullable AttributeSet attrs) {
		view();
		attributes(attrs);
		expandInputZoneAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.collapse);
	}

	private void view() {
		inflate(getContext(), R.layout.rich_edit_text, this);
		editText = findViewById(R.id.editText);
		hintText = findViewById(R.id.hintText);
		ViewCompat.setElevation(editText, getResources().getDimension(R.dimen.editTextElevation));
	}

	private void attributes(@Nullable AttributeSet attrs) {
		if (attrs != null) {
			TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.RichEditText);
			hint = array.getString(R.styleable.RichEditText_hintText);
			icon = array.getResourceId(R.styleable.RichEditText_editTextIcon, 0);
			array.recycle();
		}

		updateAttribute();
	}

	private void updateAttribute() {
		hintText.setText(hint);
		editText.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		if (gainFocus) {
			expand();
		}
	}

	private void expand() {
		if (isExpanded) {
			return;
		}
		editText.startAnimation(expandInputZoneAnimation);
		isExpanded = false;
	}


	private void setText(CharSequence text) {
		editText.setText(text);
	}
}
