package com.pavlenko.jarvel.game.event.callback;

import com.pavlenko.jarvel.game.event.model.Text;

/**
 * basic abstraction to print event callback
 * 
 * @author Rostyslav P.A.
 *
 */
public interface EventCallback {
	/**
	 * default method to print an array of Text enums
	 * 
	 * @param texts
	 */
	default void callback(Text... texts) {
		for (Text text : texts)
			callback(text.val());
	}

	/**
	 * default method to format and print single Text with an array of Objects
	 * 
	 * @param text
	 * @param objects
	 */
	default void callbackFormat(Text text, Object... objects) {
		callback(text.valFormat(objects));
	}

	/**
	 * default method to print single string
	 * 
	 * @param val
	 */
	default void callback(String val) {
		System.out.println(val);
	}
}
