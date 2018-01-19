package frlgrd.animatededittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AnimatedEditText extends LinearLayout {

	private TextView hintText;
	private EditText editText;

	private String hint;
	private int icon;

	private boolean isCollapsed = true;
	private Animation expandInputZoneAnimation, collapseInputZoneAnimation;

	public AnimatedEditText(Context context) {
		super(context);
		init(null);
	}

	public AnimatedEditText(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public AnimatedEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	private void init(@Nullable AttributeSet attrs) {
		initView();
		initAttributes(attrs);
		initAnimations();
	}

	private void initView() {
		inflate(getContext(), R.layout.animated_edit_text, this);
		editText = findViewById(R.id.editText);
		hintText = findViewById(R.id.hintText);
		ViewCompat.setElevation(editText, getResources().getDimension(R.dimen.editTextElevation));
	}

	private void initAttributes(@Nullable AttributeSet attrs) {
		if (attrs != null) {
			TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.AnimatedEditText);
			hint = array.getString(R.styleable.AnimatedEditText_hintText);
			icon = array.getResourceId(R.styleable.AnimatedEditText_editTextIcon, 0);
			array.recycle();
		}

		updateAttribute();
	}

	private void initAnimations() {
		expandInputZoneAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.expand);
		collapseInputZoneAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.collapse);
		collapse();
		editText.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean hasFocus) {
				onEditTextFocusChanged(hasFocus);
			}
		});
	}

	public void onEditTextFocusChanged(boolean hasFocus) {
		if (hasFocus) {
			expand();
		} else if (editText.getText().toString().isEmpty()) {
			collapse();
		}
	}

	private void updateAttribute() {
		hintText.setText(hint);
		editText.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
	}

	public void expand() {
		if (!isCollapsed) {
			return;
		}
		editText.startAnimation(expandInputZoneAnimation);
		isCollapsed = false;
	}

	public void collapse() {
		if (isCollapsed) {
			return;
		}
		editText.startAnimation(collapseInputZoneAnimation);
		isCollapsed = true;
	}

	@SuppressWarnings("unused")
	public EditText getEditText() {
		return editText;
	}
}
