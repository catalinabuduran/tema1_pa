import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Servere {
	static class Task {
		public static final String INPUT_FILE = "servere.in";
		public static final String OUTPUT_FILE = "servere.out";

		private int n;
		private int[] P;
		private int[] C;

		public void solve() {
			readInput();
			writeOutput(getResult());
		}

		private void readInput() {
			try {
				FileReader fr = new FileReader(INPUT_FILE);
				BufferedReader br = new BufferedReader(fr);
				String new_line = br.readLine();
				n = Integer.parseInt(new_line);
				P = new int[n];
				C = new int[n];

				new_line = br.readLine();
				String[] pPowers = new_line.split(" ");
				for (int i = 0; i < n; i++) {
					P[i] = Integer.parseInt(pPowers[i]);
				}

				new_line = br.readLine();
				String[] cAmounts = new_line.split(" ");
				for (int i = 0; i < n; i++) {
					C[i] = Integer.parseInt(cAmounts[i]);
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

		private double minim_power(double amountsOfCurrent) {
			double power = Double.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				double currentPower = P[i] - Math.abs(C[i] - amountsOfCurrent);
				power = Math.min(power, currentPower);
			}
			return power;
		}

		private double binarySearch(double precision, double l, double r) {
			if (r - l <= precision) {
				return (r + l) / 2;
			}
			double mid = (r + l) / 2;
			double right_min_power_of_mid = minim_power(mid + precision);
			double minim_power_of_mid = minim_power(mid);
			if (minim_power_of_mid < right_min_power_of_mid) {
				return binarySearch(precision, mid, r);
			} else {
				return binarySearch(precision, l, mid);
			}
		}

		private double getResult() {
			double curent_maxim = Double.MIN_VALUE, curent_minim = Double.MAX_VALUE;
			double result =  Double.NEGATIVE_INFINITY;
			for (int i = 0; i < C.length; i++) {
				if (C[i] < curent_minim) {
					curent_minim = C[i];
				}
				if (C[i] > curent_maxim) {
					curent_maxim = C[i];
				}
			}
			result = minim_power(binarySearch(0.00001, curent_minim, curent_maxim));
			return result;
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}