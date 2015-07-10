package exceptions;

@SuppressWarnings("serial")
public class ElementAlreadyExistException extends Exception{
	public ElementAlreadyExistException(String m){
		super(m);
	}
}
