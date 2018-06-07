package gen.processor;

import gen.configure.LinkType;
import gen.configure.OperationType;
import gen.configure.TableType;

import java.io.IOException;

/**
 * Описывает функциональность RFSR генератора
 * 
 * @author Fedor Trofimov
 */
public interface IGenerator {
	
	public void init(int bitness, int[] registers, boolean[] rBlocks,
                     int[][] hTables, LinkType linkType, TableType tableType,
                     OperationType operationType, String filePath);
	
	public int[] generateSequence(int tacts) throws IOException;
	
	public int computePeriod();
	
}
