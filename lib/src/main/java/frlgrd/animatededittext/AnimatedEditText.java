package frlgrd.animatededittext;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import static frlgrd.animatededittext.ViewUtils.doAfterLayout;
import static frlgrd.animatededittext.ViewUtils.getVerticalMargin;
import static frlgrd.animatededittext.ViewUtils.setViewHeight;

public class AnimatedEditText extends LinearLayout {
	/**
	 * Attributes
	 */
	private String hint;
	private int icon;

	/**
	 * Internal
	 */
	private TextView hintText;
	private EditText editText;

	private boolean isCollapsed = true;
	private Editable savedText;

	private Animation hintDowAnimation, hintUpAnimation;
	private ValueAnimator expandInputZoneAnimation, collapseInputZoneAnimation;

	private boolean viewReady = false;

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
		removeEditTextAttributes();
	}

	private void initView() {
		inflate(getContext(), R.layout.animated_edit_text, this);
		editText = findViewById(R.id.editText);
		hintText = findViewById(R.id.hintText);
		ViewCompat.setElevation(editText, getResources().getDimension(R.dimen.animatedEditTextElevation));

		editText.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override public void onFocusChange(View v, boolean hasFocus) {
				if (!viewReady) {
					return;
				}
				if (hasFocus && isCollapsed) {
					expand();
				} else if (!hasFocus && isEmpty() && !isCollapsed) {
					collapse();
				}
			}
		});
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
		hintDowAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.hint_down);
		hintUpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.hint_up);
		doAfterLayout(this, new Runnable() {
			@Override public void run() {
				initValueAnimator();
				viewReady = true;
			}
		});
	}

	private void initValueAnimator() {
		int duration = getContext().getResources().getInteger(R.integer.animatedEditTextAnimationDuration);
		float collapsedHeight = getContext().getResources().getDimension(R.dimen.animatedEditTextCollapsedHeight);
		float expandedHeight = getHeight() - (hintText.getHeight() + getVerticalMargin(hintText) + getVerticalMargin(editText));
		expandInputZoneAnimation = buildValueAnimator(collapsedHeight, expandedHeight, duration, new Runnable() {
			@Override public void run() {
				applyEditTextAttributes();
			}
		});
		collapseInputZoneAnimation = buildValueAnimator(expandedHeight, collapsedHeight, duration, new Runnable() {
			@Override public void run() {
				removeEditTextAttributes();
			}
		});
	}


	private ValueAnimator buildValueAnimator(float startValue, float endValue, int duration, final Runnable doOnStart) {
		ValueAnimator valueAnimator = ValueAnimator.ofInt(Math.round(startValue), Math.round(endValue));
		valueAnimator.setDuration(duration);
		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override public void onAnimationUpdate(ValueAnimator animation) {
				setViewHeight(editText, (int) animation.getAnimatedValue());
			}
		});
		valueAnimator.addListener(new AnimatorListenerAdapter() {
			@Override public void onAnimationStart(Animator animation) {
				super.onAnimationStart(animation);
				if (doOnStart != null) {
					doOnStart.run();
				}
			}
		});
		return valueAnimator;
	}

	@SuppressLint("ClickableViewAccessibility") @Override public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN && isCollapsed) {
			expand();
		}
		return super.onTouchEvent(event);
	}

	private void updateAttribute() {
		hintText.setText(hint);
		applyEditTextAttributes();
	}

	private void applyEditTextAttributes() {
		editText.setText(savedText);
		editText.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
		editText.requestFocus();
		editText.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
		editText.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
	}

	private void removeEditTextAttributes() {
		savedText = editText.getText();
		editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		editText.setText(null);
	}

	public void expand() {
		if (!isCollapsed) {
			return;
		}
		expandInputZoneAnimation.start();
		hintText.startAnimation(hintUpAnimation);
		isCollapsed = false;
		applyEditTextAttributes();
	}

	public void collapse() {
		if (isCollapsed) {
			return;
		}
		collapseInputZoneAnimation.start();
		hintText.startAnimation(hintDowAnimation);
		isCollapsed = true;
		removeEditTextAttributes();
	}

	private boolean isEmpty() {
		return editText.getText().length() == 0;
	}
}
