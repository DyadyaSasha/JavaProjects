package gen;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import gen.configure.LinkType;
import gen.configure.OperationType;
import gen.configure.TableType;
import gen.processor.Generator;

public class GeneratorApp {

	public static void main(String[] args) throws IOException{
		
		commonTest();
		
		Generator gen = new Generator();
		int n = 2; // разрядность
		int m = 4; // степень многочлена
		int [] registers= {0, 0, 0, 0};
		boolean [] rBlocks = new boolean [m - 1];
		Arrays.fill(rBlocks, true);
		int [][] hTables = { { 0, 1, 2, 3} , { 3, 2, 1, 0}, { 0, 1, 3, 2} };
		gen.init(n, registers, rBlocks, hTables, LinkType.B_RG_A_OS, TableType.R_SWAP, OperationType.A_B_MOD, null);
//		runTest1(n, m, gen);
	}

	private static void runTest1(int n, int m, Generator gen) {
		int[] rg = new int[m];
		int N = (1 << n) - 1;
		int M = m - 1;
		int j = 0;
		while (j != -1) {
			System.out.print(Arrays.toString(rg) + ";");
			gen.setRegisters(rg);
			System.out.println(gen.computePeriod());
			j = M;
			while (j != -1 && rg[j] == N)
				j--;
			if (j == -1)
				break;
			for (int i = M; i > j; i--) {
				rg[i] = 0;
			}
			rg[j] += 1;
		}
	}
	
	private static void commonTest() throws IOException{
		int[] rg = { 0, 1, 2 }; // начальное заполнение регистров RG
		boolean[] r = { true, true }; // наличие R блоков у регистров
		int[][] h = { { 0, 1, 2, 3 },  { 0, 1, 2, 3 }}; // поскольку выше указано наличие блоков R2, R3, а R4 отсутствует, то для последнего таблица H пуста = {}
		
		Generator gen = new Generator();
		gen.init(2, rg, r, h, LinkType.B_RG_A_OS, TableType.R_EXC, OperationType.A_B_MOD, "1.txt"); // количество разрядов чисел равно 2
		int[] sequence = gen.generateSequence(100); // генерация последовательности
		System.out.println("Последовательность: " + Arrays.toString(sequence));
		int period = gen.computePeriod();
		System.out.println("Период последовательности: " + period);
	}
	
	private static void mixTables(int [] h, int [] addr){
		Random rand = new Random();
		int N = h.length;
		int cnt = 0;
		for(int i = 0; i < 100; i++){
			System.out.println("h:" + Arrays.toString(h));
			System.out.println("addr:" + Arrays.toString(addr));
			int a = rand.nextInt(N);
			int b = rand.nextInt(N);
			cnt = i % N;
			int indexFrom = (a+b) % (N - cnt);
			int indexTo = N - 1 - cnt;
			int tmp = h[indexFrom];
			h[indexFrom] = h[indexTo];
			h[indexTo] = tmp;
			tmp = addr[h[indexFrom]];
			addr[h[indexFrom]] = addr[h[indexTo]];
			addr[h[indexTo]] = tmp;
		}
	}

}
