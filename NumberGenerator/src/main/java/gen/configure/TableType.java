package gen.configure;

/**
 * Тип таблицы у R блоков
 * 
 * @author Fedor Trofimov
 */
public enum TableType {
	STATIC("статическая"),
	R_SWAP("R-swap"),
	R_EXC("R-exc");
	
	private String label;
	
	TableType(String label) {
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
}
