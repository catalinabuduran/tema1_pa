import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Colorare {
	static class Task {
		private static final String INPUT_FILE = "colorare.in";
		private static final String OUTPUT_FILE = "colorare.out";
		private static final long MOD = 1000000007;

		private int K;
		private long result;
		private String[] input;

		public void solve() {
			readInput();
			writeOutput(getResult());
		}

		private void readInput() {
			try {
				FileReader fr = new FileReader(INPUT_FILE);
				BufferedReader br = new BufferedReader(fr);
				K = Integer.parseInt(br.readLine());
				input = br.readLine().split(" ");
				br.close();
				fr.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(long result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private long fastPow(int base, int exp, long mod) {
			if (exp == 0) {
				return 1;
			}
			long half = fastPow(base, exp / 2, mod);
			long result = (half * half) % mod;
			if (exp % 2 != 0) {
				result = (result * base) % mod;
			}
			return result;
		}

		public long getResult() {
			String prev_type = "";

			for (int i = 0; i < 2 * K; i += 2) {
				int X = Integer.parseInt(input[i]);
				String T = input[i + 1];

				if (i == 0) {
					if (T.equals("H")) {
						result = 6 * fastPow(3, X - 1, MOD) % MOD;
					} else {
						result = 3 * fastPow(2, X - 1, MOD) % MOD;
					}
				} else {
					if (prev_type.equals("H") && T.equals("H")) {
						result = (result * fastPow(3, X, MOD)) % MOD;
					} else if (prev_type.equals("V") && T.equals("V")) {
						result = (result * fastPow(2, X, MOD)) % MOD;
					} else if (prev_type.equals("V") && T.equals("H")) {
						result = (result * 2 * fastPow(3, X - 1, MOD)) % MOD;
					} else if (prev_type.equals("H") && T.equals("V")) {
						result = (result * fastPow(2, X - 1, MOD)) % MOD;
					}
				}
				prev_type = T;
			}
			return result % MOD;
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}