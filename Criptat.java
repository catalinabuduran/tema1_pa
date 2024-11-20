import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Criptat {
	static class Task {
		public static final String INPUT_FILE = "criptat.in";
		public static final String OUTPUT_FILE = "criptat.out";
		int N, totalLength = 0;
		String[] words;

		public void solve() {
			readInput();
			writeOutput(getResult());
		}

		private void readInput() {
			try {
				FileReader fr = new FileReader(INPUT_FILE);
				BufferedReader br = new BufferedReader(fr);

				N = Integer.parseInt(br.readLine());

				words = new String[N];
				totalLength = 0;
				for (int i = 0; i < N; i++) {
					words[i] = br.readLine().trim();
					totalLength += words[i].length();
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
				pw.printf("%d\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int getResult() {
			Set<Character> litere_diferite = new HashSet<>();
			int lungimeMax = 0;
			for (String word : words) {
				for (char charr : word.toCharArray()) {
					litere_diferite.add(charr);
				}
			}
			for (char litera_curenta : litere_diferite) {
				int[] dprog = new int[totalLength + 1];
				Arrays.fill(dprog, 0);
				for (String word : words) {
					int frecv = 0;
					int word_length = word.length();
					for (char charr: word.toCharArray()) {
						if (charr == litera_curenta) {
							frecv++;
						}
					}
					for (int j = totalLength; j >= word_length; j--) {
						dprog[j] = Math.max(dprog[j], dprog[j - word_length] + frecv);
					}
				}
				for (int i = 0; i <= totalLength; i++) {
					if (dprog[i] > i / 2) {
						lungimeMax = Math.max(lungimeMax, i);
					}
				}
			}
			return lungimeMax;
		}
	}
	public static void main(String[] args) {
		new Task().solve();
	}
}