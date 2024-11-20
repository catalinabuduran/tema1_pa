import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class Oferta {
	static class Task {
		private static final String INPUT_FILE = "oferta.in";
		private static final String OUTPUT_FILE = "oferta.out";
		int N, K;
		int[] preturi;

		public void solve() {
			readInput();
			writeOutput(getResult());
		}

		private void readInput() {
			try {
				FileReader fr = new FileReader(INPUT_FILE);
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();
				String[] firstLine = line.split(" ");
				N = Integer.parseInt(firstLine[0]);
				K = Integer.parseInt(firstLine[1]); 

				preturi = new int[N];
				line = br.readLine();
				String[]pretProdus = line.split(" ");
				for (int i = 0; i < N; i++) {
					preturi[i] = Integer.parseInt(pretProdus[i]);
				}
				br.close();
				fr.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(double result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%.1f\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public double getResult() {
			double[] cost = new double[N + 1];
			Arrays.fill(cost, Double.MAX_VALUE);
			//costul initial
			cost[0] = 0;
			for (int i = 0; i < N; i++) {
				//daca este cumparat un produs individual
				cost[i + 1] = Math.min(cost[i + 1], cost[i] + preturi[i]);
				if (i + 1 < N) { //2 produse
					double low_price = Math.min(preturi[i], preturi[i + 1]);
					cost[i + 2] = Math.min(cost[i + 2], cost[i] + preturi[i]
								+ preturi[i + 1] - 0.5 * low_price);
				}
				if (i + 2 < N) { //3 produse
					double low_price = Math.min(preturi[i], Math.min(preturi[i + 1],
								preturi[i + 2]));
					cost[i + 3] = Math.min(cost[i + 3], cost[i] + preturi[i] + preturi[i + 1]
								+ preturi[i + 2] - low_price);
				}
			}
			double result = cost[N];
			return result;
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
