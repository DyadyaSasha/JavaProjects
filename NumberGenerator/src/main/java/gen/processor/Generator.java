package gen.processor;

import gen.configure.LinkType;
import gen.configure.OperationType;
import gen.configure.TableType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Класс RFSR генератора
 * 
 * @author Fedor Trofimov
 *
 */
public class Generator implements IGenerator {

	private int[] registers0; // буфферные регистры RG
	private int[][] hTables0; // буфферные таблицы H

	private int[] registers; // регистры RG
	private int[][] hTables; // таблицы H
	private int[][] h2Tables; // таблицы H2 для R-swap блока
	private int[][] addrTables; // адресные таблицы Addr
	private int[][] addr2Tables; // адресные таблицы Addr2 для R-swap
	private boolean[] rBlocks; // признаки наличия R-блоков у регистров RG
	private int N; // разрядность числа
	private int M; // степень многочлена
	private int MAX; // кол-во чисел согласно разрядности числа
	private LinkType linkType; // тип связи входов A и B
	private TableType tableType; // тип таблицы у R-блоков
	private OperationType operationType; // тип операции таблицы
	private String filePath; // путь к файлу, куда выводится выходящая последовательность геренатора

	private final int PREFIX_LENGTH = 10; // длина префикса, при которой 2 подпоследовательности считаются равными
	private final String FILE_NAME = "generation_results.txt";

	/**
	 * Задает параметры генератора
	 * 
	 * @param bitness разрядность чисел, генерируемой последовательности
	 * @param registers начальные значения регистров
	 * @param rBlocks массив наличия у регистров R-блоков. Например, если степень многочлена 4 и выбраны блоки R2 и R4,
	 * 		то массив будет иметь значения [true, false, true, false]
	 * @param hTables значения таблиц H для каждого из R блоков. Строка двухмерного массива - значения таблицы H, а столбцы массива - номера R блоков. 
	 * 		Например, если степень многочлена - 3, выбраны блоки R2 и R3, разрядность числа - 3, 
	 * 		то массив имеет вид {{0, 1, 2, 3}, {1, 3, 2, 0}, {}}. (последний столбец пустой, поскольку R4 не выбран)
	 * @param linkType тип связи входов A и B у блоков
	 * @param tableType тип таблицы у R блоков
	 * @param operationType тип операции вычисления адреса таблицы H в R блоке
	 * @param filePath путь до файла в ФС, куда выводится сформированная последовательность.
	 * 		null - если вывод в файл отключен
	 */
	public void init(int bitness, int[] registers, boolean[] rBlocks,
			int[][] hTables, LinkType linkType, TableType tableType,
			OperationType operationType, String filePath) {
		this.registers0 = registers;
		this.hTables0 = hTables;
		this.rBlocks = rBlocks;
		this.N = bitness;
		this.M = registers.length;
		this.MAX = 1 << N;
		this.linkType = linkType;
		this.operationType = operationType;
		this.tableType = tableType;
		this.filePath = filePath;
	}

	/**
	 * Генерирует выходящую последовательность. Перед вызовом этого метода необходимо один раз вызвать метод init()
	 * 
	 * @param tacts - количество тактов работы геренатора
	 * @return выходящая последовательность
	 */
	public int[] generateSequence(int tacts) throws IOException {
		int[] sequence = new int[tacts];
		// сброс в начальное состояние
		this.reset();
		for (int t = 0; t < tacts; t++) {
			int lastReg = registers[M - 1];
			for (int j = M - 1; j > 0; j--) {
				registers[j] = registers[j - 1];
			}
			registers[0] = lastReg;
			for (int j = 1; j < M; j++) {
				if (rBlocks[j - 1]) {
					int opA;
					int opB;
					if (linkType == LinkType.A_RG_B_OS) {
						opA = registers[j];
						opB = lastReg;
					} else {
						opA = lastReg;
						opB = registers[j];
					}
					int index = getTableIndex(t, j, opA, opB);
					registers[j] = hTables[j - 1][index];
					// перемешать таблицу
					if(tableType == TableType.R_EXC)
						exclusionMixTable(t, j, opA, opB);
					if(tableType == TableType.R_SWAP)
						swapMixTable(t, j, opA, opB);
				}
			}
			sequence[t] = registers[M - 1];
		}
		if(filePath != null){
			writeToFile(sequence);
		}
		return sequence;
	}
	
	/**
	 * Вычисляет период выходящей последовательности. Перед вызовом этого метода необходимо один раз вызвать метод init()
	 * 
	 * @return период последовательности
	 */
	public int computePeriod(){
		if(this.tableType == TableType.STATIC) {
			return this.computePeriodByRG();
		} else {
			return this.computePeriodBySubsequence();
		}
	}
	
	/**
	 * Устанавливает начальные зрачения регистров 
	 * @param newRG новые значения регистров
	 */
	public void setRegisters(int [] newRG) {
		this.registers0 = newRG;
	}
	
	/**
	 * Сбрасывает генератор в начальные параметры
	 */
	private void reset() {
		this.registers = Arrays.copyOf(this.registers0, this.registers0.length);
		this.hTables = new int [hTables0.length][hTables0[0].length];
		this.h2Tables = new int [hTables0.length][hTables0[0].length];
		for(int i = 0; i < hTables0.length; i++){
			if (rBlocks[i])
				this.hTables[i] = Arrays.copyOf(this.hTables0[i], this.hTables0[i].length);
				this.h2Tables[i] = Arrays.copyOf(this.hTables0[i], this.hTables0[i].length);
		}
		// заполнение таблицы Addr
		this.addrTables = new int[M][MAX];
		this.addr2Tables = new int[M][MAX];
		for (int j = 0; j < rBlocks.length; j++) {
			if (rBlocks[j]) {
				for (int i = 0; i < hTables[j].length; i++) {
					addrTables[j][hTables[j][i]] = i;
					addr2Tables[j][h2Tables[j][i]] = i;
				}
			}
		}
	}

	/**
	 * Вычисляет период по совпадению значений регистров
	 * 
	 * @return период последовательности
	 */
	private int computePeriodByRG() {
		int t = 1;
		// сброс в начальное состояние
		this.reset();
		while (true) {
			int lastReg = registers[M - 1];
			for (int j = M - 1; j > 0; j--) {
				registers[j] = registers[j - 1];
			}
			registers[0] = lastReg;
			for (int j = 1; j < M; j++) {
				if (rBlocks[j - 1]) {
					int opA;
					int opB;
					if (linkType == LinkType.A_RG_B_OS) {
						opA = registers[j];
						opB = lastReg;
					} else {
						opA = lastReg;
						opB = registers[j];
					}
					int index = getTableIndex(t, j, opA, opB);
					registers[j] = hTables[j - 1][index];
				}
			}
			if (Arrays.equals(registers, registers0)) {
				return t;
			}
			t += 1;
		}
	}

	/**
	 * Вычисляет период по совпадению подпоследовательности
	 * 
	 * @return период последовательности
	 */
	private int computePeriodBySubsequence() {
		final int DELTA = 100; // коэффициент реалокации памяти под массив
		// сброс в начальное состояние
		this.reset();
		int tacts = DELTA;
		int[] sequence = new int[tacts];
		int t = 0;
		int period = 0;
		while (period == 0) {
			// формирование последовательности
			int lastReg = registers[M - 1];
			for (int j = M - 1; j > 0; j--) {
				registers[j] = registers[j - 1];
			}
			registers[0] = lastReg;
			for (int j = 1; j < M; j++) {
				if (rBlocks[j - 1]) {
					int opA;
					int opB;
					if (linkType == LinkType.A_RG_B_OS) {
						opA = registers[j];
						opB = lastReg;
					} else {
						opA = lastReg;
						opB = registers[j];
					}
					int index = getTableIndex(t, j, opA, opB);
					registers[j] = hTables[j - 1][index];
					// перемешать таблицу
					if(tableType == TableType.R_EXC)
						exclusionMixTable(t, j, opA, opB);
					if(tableType == TableType.R_SWAP)
						swapMixTable(t, j, opA, opB);
				}
			}
			// выделение дополнительной памяти, если массив последовательности заполнен
			if (t == tacts) {
				tacts += DELTA;
				sequence = Arrays.copyOf(sequence, tacts);
			}
			sequence[t] = registers[M - 1];
			t++;
			period = calculatePeriod(sequence, t);
		}
		return period;
	}

	/**
	 * Определяет период последовательности в массиве a длинной leng
	 * 
	 * @param a массив последовательности
	 * @param leng фактический размер (size, не capacity) массива
	 * @return период последовательности
	 */
	private int calculatePeriod(int[] a, int leng) {
		if(leng < 2 * PREFIX_LENGTH)
			return 0;
		boolean isEqual = false;
		int i = leng - 2 * PREFIX_LENGTH ;
		while(i > 0 && !isEqual) {
			int j = i;
			while((j - i) < PREFIX_LENGTH && a[j] == a[leng - PREFIX_LENGTH + (j - i)])
				++j;
			if((j - i) == PREFIX_LENGTH)
				isEqual = true;
			i--;
		}
		if(!isEqual)
			return 0;
		else 
			return leng - (i + 1) - PREFIX_LENGTH;
	}
	
	/**
	 * Выбора индекса таблицы H
	 * @param tact - текущий такт работы генератора
	 * @param hNumber - номер R-блока, чья H таблица перемешивается
	 * @param opA - значение на входе А
	 * @param opB - значение на входе B 
	 * @return
	 */
	private int getTableIndex(int tact, int hNumber, int opA, int opB) {
		int index;
		switch (this.operationType) {
		case A_B_MOD:
			index = (addrTables[hNumber - 1][opA] + opB) % MAX;
			break;
		case A_B_XOR:
			index = addrTables[hNumber - 1][opA] ^ opB;
			break;
		case A_B_COUNTER_MOD:
			int counter = tact % N;
			index = (addrTables[hNumber - 1][opA] + opB + counter) % MAX;
			break;
		default:
			throw new IllegalArgumentException(
					"Illegal operationType parameter");
		}
		return index;
	}
	
	/**
	 * Перемешивает H таблицы R-exc блоков
	 * @param tact - текущий такт работы генератора
	 * @param hNumber - номер R-блока, чья H таблица перемешивается
	 * @param opA - значение на входе А
	 * @param opB - значение на входе B 
	 */
	private void exclusionMixTable2(int tact, int hNumber, int opA, int opB) {
		int [] hTable = hTables[hNumber - 1];
		int [] addrTable = addrTables[hNumber - 1];
		int cnt = tact % MAX;
		int indexFrom = (opA + opB) % (MAX - cnt);
		int indexTo = MAX - cnt - 1;
		int tmp = hTable[indexFrom];
		hTable[indexFrom] = hTable[indexTo];
		hTable[indexTo] = tmp;
		tmp = addrTable[hTable[indexFrom]];
		addrTable[hTable[indexFrom]] = addrTable[hTable[indexTo]];
		addrTable[hTable[indexTo]] = tmp;
	}
	
	@Deprecated
	private void exclusionMixTable(int tact, int hNumber, int opA, int opB) {
		int [] h2Table = h2Tables[hNumber - 1];
		int [] addr2Table = addr2Tables[hNumber - 1];
		int cnt = tact % MAX;
		int indexFrom = (opA + opB) % (MAX - cnt);
		int indexTo = MAX - cnt - 1;
		int tmp = h2Table[indexFrom];
		h2Table[indexFrom] = h2Table[indexTo];
		h2Table[indexTo] = tmp;
		tmp = addr2Table[h2Table[indexFrom]];
		addr2Table[h2Table[indexFrom]] = addr2Table[h2Table[indexTo]];
		addr2Table[h2Table[indexTo]] = tmp;
		if(cnt == (MAX - 1)){
			hTables[hNumber - 1] = Arrays.copyOf(h2Table, h2Table.length);
			addrTables[hNumber - 1] = Arrays.copyOf(addr2Table, addr2Table.length);
		}
	}
	
	/**
	 * Перемешивает H таблицы R-swap блоков
	 * @param tact - текущий такт работы генератора
	 * @param hNumber - номер R-блока, чья H таблица перемешивается
	 * @param opA - значение на входе А
	 * @param opB - значение на входе B 
	 */
	private void swapMixTable(int tact, int hNumber, int opA, int opB) {
		int [] h2Table = h2Tables[hNumber - 1];
		int [] addr2Table = addr2Tables[hNumber - 1];
		int cnt = tact % MAX;
		int fromIndex = cnt;
		int toIndex = (addr2Table[opA] + opB) % MAX;
		int tmp = h2Table[fromIndex];
		h2Table[fromIndex] = h2Table[toIndex];
		h2Table[toIndex] = tmp;
		addr2Table[h2Table[fromIndex]] = fromIndex;
		addr2Table[h2Table[toIndex]] = toIndex;
		if(cnt == (MAX - 1)){
			hTables[hNumber - 1] = Arrays.copyOf(h2Table, h2Table.length);
			addrTables[hNumber - 1] = Arrays.copyOf(addr2Table, addr2Table.length);
		}
		
	}
	/**
	 * Выводит параметры генератора, выходящую последовательность и ее период
	 * 
	 * @param sequence выводимая в файл последовательность
	 */
	private void writeToFile(int [] sequence) throws IOException{
		String file = filePath;
		if(new File(filePath).isDirectory())
			file = new File(filePath, FILE_NAME).getAbsolutePath();
		String el = "\n";
		try(FileWriter writer = new FileWriter(file, false)){
			writer.write("Parameters:" + el);
			writer.write("bitness: " + N + el);
			writer.write("polynomial degree: " + M + el);
			writer.write("registers: " + Arrays.toString(registers0) + el);
			writer.write("H tables: ");
			for(int i = 0; i < hTables0.length; i++){
				writer.write(Arrays.toString(hTables0[i]));
			}
			writer.write(el + "Link type: " + linkType.getLabel() + el);
			writer.write("Operation type: " + operationType.getLabel() + el);
			writer.write("Table type: " + tableType.getLabel() + el);
			writer.write(el + "Results:" + el);
			writer.write("period: " + calculatePeriod(sequence, sequence.length) + el);
			writer.write("sequence: " + Arrays.toString(sequence) + el);
		} catch(IOException exc){
			exc.printStackTrace();
//			System.out.println(exc.getMessage());
			throw exc;
		}
	}
	
	@Deprecated
	private int computeSequencePeriod(int[] a, int leng) {
		boolean b = false;
		int k = 0;
		for (k = 0; k != leng - 1; ++k) {
			if (a[k + 1] != a[0])
				continue;
			b = true;
			for (int i = 0; i != leng - k - 1; ++i) {
				if (a[i + k + 1] != a[i])
					b = false;
			}
			if (b)
				break;
		}
		int t = 0;
		if (b) {
			while (t != k + 1)
				t++;
		}
		return t;
	}

}
