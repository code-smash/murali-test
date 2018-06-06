import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ArrayMultiplication {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the numbers");

		int firstNum = sc.nextInt();
		int secondNum = sc.nextInt();

		multiply(toList(firstNum), toList(secondNum));

	}

	private static List<Integer> toList(int n) {
		String strNum = Integer.toString(n);
		List<Integer> intList = new LinkedList<>();
		char[] chArray = strNum.toCharArray();

		for (char c : chArray)
			intList.add(Character.getNumericValue(c));

		return intList;
	}

	private static Deque<Integer> multiply(List<Integer> lnum, List<Integer> rnum) {

		int lsize = lnum.size();
		int rsize = rnum.size();

		@SuppressWarnings("unchecked")
		Deque<Integer>[] decks = new LinkedList[rnum.size()];


		for (int i = rnum.size() - 1, k = 0; i >= 0; i--, k++) {
			decks[k] = new LinkedList<>();
			int carry = 0;
			for (int j = lnum.size() - 1; j >= 0; j--) {
				int m = lnum.get(j).intValue() * rnum.get(i).intValue() + carry;
				if (m >= 10) {
					carry = m / 10;
					m %= 10;
				} else
					carry = 0;

				decks[k].addLast(m);
			}

			if (carry > 0)
				decks[k].addLast(carry);

		}

		for (int i = 0; i < decks.length; i++)
			for (int j = 0; j < i; j++)
				decks[i].addFirst(0);

		for (int i = decks.length - 2; i >= 0; i--) {
			int s1 = decks[i + 1].size();
			int s2 = decks[i].size();
			for (int j = 0; j < s1 - s2; j++)
				decks[i].addLast(0);
		}

		for (Deque<Integer> d : decks)
			printQueue((LinkedList<Integer>) d);

		List<Integer> resQueue = new LinkedList<>();
		int carry = 0;
		while (!decks[0].isEmpty()) {
			int sum = 0;


			for (Deque<Integer> d : decks)
				sum += d.pollFirst().intValue();

			sum += carry;

			if (sum >= 10) {
				carry = sum / 10;
				sum %= 10;
			} else
				carry = 0;

			// resQueue.addLast(sum);
			resQueue.add(0, sum);

		}

		System.out.println();
		printQueue((LinkedList<Integer>) resQueue);

		return null;

	}

	private static void printQueue(LinkedList<Integer> deck) {
		System.out.println();

		for (int i = 0; i < deck.size(); i++)
			System.out.print(deck.get(i).intValue() + " ");
	}

}
