import java.util.*;

public class StringValidator {

	// function to check if parentheses are balanced
	// Must start and end with parentheses
	static boolean isBalanced(String lisp) {
		if (lisp != null && !lisp.isEmpty()) {

			Deque<Character> stack = new ArrayDeque<Character>();

			for (int i = 0; i < lisp.length(); i++) {
				char character = lisp.charAt(i);

				if (character == '(') {
					stack.push(character);
					continue;
				}

				// IF current current character is not opening
				// parenthesis, then it must be closing. So stack
				// cannot be empty at this point.
				if (stack.isEmpty())
					return false;				

				if (character == ')') {
					stack.pop();
				}
			}

			return (stack.isEmpty());

		}
		return false;
	}

	// testing
	public static void main(String[] args) {
		// balanced
		String lisp1 = "(a((b)(c)d))";
		// not balanced
        String lisp2 = "(a((b)(c)d)))"; 

		if (isBalanced(lisp1))
			System.out.println(lisp1 + " is Balanced.");
		else
			System.out.println(lisp1 + " is Not Balanced.");
		if (isBalanced(lisp2))
			System.out.println(lisp2 + " is Balanced.");
		else
			System.out.println(lisp2 + " is Not Balanced.");
	}
}
