package gen.configure;

/**
 * Тип связи входов A и B у блоков
 * 
 * @author Fedor Trofimov
 */
public enum LinkType {
	A_RG_B_OS("A - вход, B - параметр"), // на A подается значение с регистра, на B - значение с обратной связи
	B_RG_A_OS("B - вход, A - параметр"); // наоборот
	
	private String label;
	
	LinkType(String label) {
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
}
