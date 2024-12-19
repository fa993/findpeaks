package com.fa993.types.supertype;

/**
 * A utility class representing an exclusive choice between two or three possible types.
 * <p>
 * The {@code Either} class contains nested classes to handle either two or three alternatives.
 * The {@link Selector} enum is used to indicate which option is currently selected.
 * </p>
 */
public class Either {

	// Private constructor to prevent instantiation of the top-level class.
	private Either() {}

	/**
	 * Enum representing the possible choices: First, Second, or Third.
	 */
	public enum Selector {
		First, Second, Third;

		/**
		 * Checks if the current selection is {@code First}.
		 *
		 * @return {@code true} if the selection is {@code First}, {@code false} otherwise
		 */
		public boolean isFirst() {
			return this == First;
		}

		/**
		 * Checks if the current selection is {@code Second}.
		 *
		 * @return {@code true} if the selection is {@code Second}, {@code false} otherwise
		 */
		public boolean isSecond() {
			return this == Second;
		}

		/**
		 * Checks if the current selection is {@code Third}.
		 *
		 * @return {@code true} if the selection is {@code Third}, {@code false} otherwise
		 */
		public boolean isThird() {
			return this == Third;
		}

	}

	/**
	 * Represents an exclusive choice between two possible types.
	 *
	 * @param <A> the type of the first possible value
	 * @param <B> the type of the second possible value
	 */
	public static class OfTwo<A, B> {

		private A first;
		private B second;
		private Selector selector;

		// Private constructor to enforce controlled instantiation.
		private OfTwo() {}

		/**
		 * Constructs an {@code OfTwo} object with the specified values and selector.
		 *
		 * @param first    the value of the first option
		 * @param second   the value of the second option
		 * @param selector the selector indicating the current choice
		 */
		protected OfTwo(A first, B second, Selector selector) {
			this.first = first;
			this.second = second;
			this.selector = selector;
		}

		/**
		 * Creates an {@code OfTwo} instance with the first value selected.
		 *
		 * @param first the value of the first option
		 * @param <A>   the type of the first value
		 * @param <B>   the type of the second value
		 * @return an {@code OfTwo} instance with the first value selected
		 */
		public static <A, B> OfTwo<A, B> first(A first) {
			OfTwo<A, B> out = new OfTwo<>();
			out.first = first;
			out.selector = Selector.First;
			return out;
		}

		/**
		 * Creates an {@code OfTwo} instance with the second value selected.
		 *
		 * @param second the value of the second option
		 * @param <A>    the type of the first value
		 * @param <B>    the type of the second value
		 * @return an {@code OfTwo} instance with the second value selected
		 */
		public static <A, B> OfTwo<A, B> second(B second) {
			OfTwo<A, B> out = new OfTwo<>();
			out.second = second;
			out.selector = Selector.Second;
			return out;
		}

		/**
		 * Gets the first value if selected.
		 *
		 * @return the first value
		 * @throws IllegalStateException if the current selection is not {@code First}
		 */
		public A getFirst() {
			if(getSelector() != Selector.First) {
				throw new IllegalStateException("Either isn't of first");
			}
			return first;
		}

		/**
		 * Gets the second value if selected.
		 *
		 * @return the second value
		 * @throws IllegalStateException if the current selection is not {@code Second}
		 */
		public B getSecond() {
			if(getSelector() != Selector.Second) {
				throw new IllegalStateException("Either isn't of second");
			}
			return second;
		}

		/**
		 * Gets the current selector.
		 *
		 * @return the {@code Selector} indicating the current selection
		 */
		public Selector getSelector() {
			return selector;
		}

	}

	/**
	 * Represents an exclusive choice between three possible types.
	 *
	 * @param <A> the type of the first possible value
	 * @param <B> the type of the second possible value
	 * @param <C> the type of the third possible value
	 */
	public static class OfThree<A, B, C> {

		private A first;
		private B second;
		private C third;
		private Selector selector;

		// Private constructor to enforce controlled instantiation.
		private OfThree() {}

		/**
		 * Constructs an {@code OfThree} object with the specified values and selector.
		 *
		 * @param first    the value of the first option
		 * @param second   the value of the second option
		 * @param third    the value of the third option
		 * @param selector the selector indicating the current choice
		 */
		protected OfThree(A first, B second, C third, Selector selector) {
			this.first = first;
			this.second = second;
			this.third = third;
			this.selector = selector;
		}

		/**
		 * Creates an {@code OfThree} instance with the first value selected.
		 *
		 * @param first the value of the first option
		 * @param <A>   the type of the first value
		 * @param <B>   the type of the second value
		 * @param <C>   the type of the third value
		 * @return an {@code OfThree} instance with the first value selected
		 */
		public static <A, B, C> OfThree<A, B, C> first(A first) {
			OfThree<A, B, C> out = new OfThree<>();
			out.first = first;
			out.selector = Selector.First;
			return out;
		}

		/**
		 * Creates an {@code OfThree} instance with the second value selected.
		 *
		 * @param second the value of the second option
		 * @param <A>    the type of the first value
		 * @param <B>    the type of the second value
		 * @param <C>    the type of the third value
		 * @return an {@code OfThree} instance with the second value selected
		 */
		public static <A, B, C> OfThree<A, B, C> second(B second) {
			OfThree<A, B, C> out = new OfThree<>();
			out.second = second;
			out.selector = Selector.Second;
			return out;
		}

		/**
		 * Creates an {@code OfThree} instance with the third value selected.
		 *
		 * @param third the value of the third option
		 * @param <A>   the type of the first value
		 * @param <B>   the type of the second value
		 * @param <C>   the type of the third value
		 * @return an {@code OfThree} instance with the third value selected
		 */
		public static <A, B, C> OfThree<A, B, C> third(C third) {
			OfThree<A, B, C> out = new OfThree<>();
			out.third = third;
			out.selector = Selector.Third;
			return out;
		}

		/**
		 * Gets the first value if selected.
		 *
		 * @return the first value
		 * @throws IllegalStateException if the current selection is not {@code First}
		 */
		public A getFirst() {
			if(getSelector() != Selector.First) {
				throw new IllegalStateException("Either isn't of first");
			}
			return first;
		}

		/**
		 * Gets the second value if selected.
		 *
		 * @return the second value
		 * @throws IllegalStateException if the current selection is not {@code Second}
		 */
		public B getSecond() {
			if(getSelector() != Selector.Second) {
				throw new IllegalStateException("Either isn't of second");
			}
			return second;
		}

		/**
		 * Gets the third value if selected.
		 *
		 * @return the third value
		 * @throws IllegalStateException if the current selection is not {@code Third}
		 */
		public C getThird() {
			if(getSelector() != Selector.Third) {
				throw new IllegalStateException("Either isn't of third");
			}
			return third;
		}

		/**
		 * Gets the current selector.
		 *
		 * @return the {@code Selector} indicating the current selection
		 */
		public Selector getSelector() {
			return selector;
		}

	}



}
