package gen.configure;

/**
 * Тип операции вычисления адреса таблицы H в R блоке
 * 
 * @author Fedor Trofimov
 */
public enum OperationType {
	A_B_MOD("(m(A) + B) mod"),
	A_B_COUNTER_MOD("(m(A) + B + counter) mod"),
	A_B_XOR("A xor B");
	
	private String label;
	
	OperationType(String label) {
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
}
