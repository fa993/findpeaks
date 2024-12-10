package com.fa993.function.supertype;

public class Either {

	private Either() {}

	public enum Selector {
		First, Second, Third;

		public boolean isFirst() {
			return this == First;
		}

		public boolean isSecond() {
			return this == Second;
		}

		public boolean isThird() {
			return this == Third;
		}

	}


	public static class OfTwo<A, B> {

		private A first;
		private B second;
		private Selector selector;

		private OfTwo() {}

		protected OfTwo(A first, B second, Selector selector) {
			this.first = first;
			this.second = second;
			this.selector = selector;
		}

		public static <A, B> OfTwo<A, B> first(A first) {
			OfTwo<A, B> out = new OfTwo<>();
			out.first = first;
			out.selector = Selector.First;
			return out;
		}

		public static <A, B> OfTwo<A, B> second(B second) {
			OfTwo<A, B> out = new OfTwo<>();
			out.second = second;
			out.selector = Selector.Second;
			return out;
		}

		public A getFirst() {
			if(getSelector() != Selector.First) {
				throw new IllegalStateException("Either isn't of first");
			}
			return first;
		}

		public B getSecond() {
			if(getSelector() != Selector.Second) {
				throw new IllegalStateException("Either isn't of second");
			}
			return second;
		}

		public Selector getSelector() {
			return selector;
		}

	}

	public static class OfThree<A, B, C> {

		private A first;
		private B second;
		private C third;
		private Selector selector;

		private OfThree() {}

		protected OfThree(A first, B second, C third, Selector selector) {
			this.first = first;
			this.second = second;
			this.third = third;
			this.selector = selector;
		}

		public static <A, B, C> OfThree<A, B, C> first(A first) {
			OfThree<A, B, C> out = new OfThree<>();
			out.first = first;
			out.selector = Selector.First;
			return out;
		}

		public static <A, B, C> OfThree<A, B, C> second(B second) {
			OfThree<A, B, C> out = new OfThree<>();
			out.second = second;
			out.selector = Selector.Second;
			return out;
		}

		public static <A, B, C> OfThree<A, B, C> third(C third) {
			OfThree<A, B, C> out = new OfThree<>();
			out.third = third;
			out.selector = Selector.Third;
			return out;
		}

		public A getFirst() {
			if(getSelector() != Selector.First) {
				throw new IllegalStateException("Either isn't of first");
			}
			return first;
		}

		public B getSecond() {
			if(getSelector() != Selector.Second) {
				throw new IllegalStateException("Either isn't of second");
			}
			return second;
		}

		public C getThird() {
			if(getSelector() != Selector.Third) {
				throw new IllegalStateException("Either isn't of third");
			}
			return third;
		}

		public Selector getSelector() {
			return selector;
		}

	}



}
