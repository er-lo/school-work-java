//Erick Lopez
//Advanced Java
//OCCC Fall 2023
//OCCCDate with Exceptions
//InvalidOCCCDateException

public class InvalidOCCCDateException extends IllegalArgumentException {
	private String errorMessage;
	
	public InvalidOCCCDateException() {
		super();
		errorMessage = "Invalid Date.";
	}
	
	public InvalidOCCCDateException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public String getMessage() {
		return errorMessage;
	}
	
	public String toString() {
		return "InvalidOCCCDateException: " + errorMessage;
	}
}
