package frlgrd.animatededittext;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class InputTypes {

	static final int TEXT = 0;
	static final int NAME = 1;
	static final int EMAIL = 2;
	static final int PASSWORD = 3;
	static final int NON_KEYBOARD = 4;/* Useful for date filed */
	static final int NUMBER = 5;
	static final int PHONE = 6;

	@Retention(RetentionPolicy.SOURCE)
	@IntDef({TEXT, NAME, EMAIL, PASSWORD, NON_KEYBOARD, NUMBER, PHONE}) @interface Type {
	}
}
