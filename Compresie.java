import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class Compresie {
	static class Task {
		public static final String INPUT_FILE = "compresie.in";
		public static final String OUTPUT_FILE = "compresie.out";
		private static final int MOD = 1000000007;

		int n, m;
		int[] A;
		int[] B;

		public void solve() {
			readInput();
			if (getResult(sumForEachPosition(A), sumForEachPosition(B)) == 0) {
				writeOutput(-1);
			} else {
				writeOutput(getResult(sumForEachPosition(A), sumForEachPosition(B)));
			}
		}

		private void readInput() {
			try {
				FileReader fr = new FileReader(INPUT_FILE);
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();
				n = Integer.parseInt(line);
				A = new int[n];
				line = br.readLine();
				String[] aElements = line.split(" ");
				for (int i = 0; i < n; i++) {
					A[i] = Integer.parseInt(aElements[i]);
				}

				m = Integer.parseInt(br.readLine());
				B = new int[m];
				line = br.readLine();
				String[] bElements = line.split(" ");
				for (int i = 0; i < m; i++) {
					B[i] = Integer.parseInt(bElements[i]);
				}
				br.close();
				fr.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.println(result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void bubbleSort(int[] array) {
			boolean interschimbare;
			for (int i = 0; i < array.length - 1; i++) {
				interschimbare = false;
				for (int j = 0; j < array.length - 1 - i; j++) {
					if (array[j] > array[j + 1]) {
						int temp = array[j];
						array[j] = array[j + 1];
						array[j + 1] = temp;
						interschimbare = true;
					}
				}
				if (!interschimbare) {
					break;
				}
			}
		}

		private int getResult(int[] sir1, int[] sir2) {
			bubbleSort(sir1);
			bubbleSort(sir2);
			int i = 0, j = 0, elementeComune = 0;
			while (i < sir1.length && j < sir2.length) {
				if (sir1[i] < sir2[j]) {
					i++;
				} else if (sir1[i] > sir2[j]) {
					j++;
				} else {
					i++;
					j++;
					elementeComune++;
				}
			}
			return elementeComune;
		}

		//calculez suma pana la o pozitie data pt fiecare element
		private int[] sumForEachPosition(int[] elements) {
			int[] sum = new int[elements.length];

			if (elements.length > 0) {
				sum[0] = elements[0];
				for (int i = 1; i < elements.length; i++) {
					sum[i] = sum[i - 1] + elements[i];
				}
			}
			return sum;
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}