package space.gavinklfong.demo.streamapi.models;

public enum CustomerTier {

	BASIC (0),
	GOLD (1),
	PLATINUM (2);
	
	private final int value;
	
	private CustomerTier(int s) {
		value = s;
	}
	
    public boolean equalsName(int otherValue) {
        return value == otherValue;
    }

    public String toString() {
       return this.value + "";
    }	
}
