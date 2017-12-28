package util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;
/**
 * Classe respons�vel por fornecer m�todos �teis para valida��o de dados
 * em geral.
 * 
 * @author D�bora
 */
public class Validator {
	/**
	 * Verifica se uma string � diferente de null e n�o � vazia.
	 *
	 * @return
	 */
	public static final boolean isEmpty(String s) {
		return (s == null || s.trim().length() == 0);
	}
	
	
	public static final boolean isEmpty(Object o) {
		if (o == null)
			return true;
		if (o instanceof String)
			return isEmpty( (String) o);
		if (o instanceof Number) {
			Number i = (Number) o;
			return (i.intValue() == 0);
		}
		if (o instanceof Object[])
			return ((Object[]) o).length == 0;
		if (o instanceof int[])
			return ((int[]) o).length == 0;
		if (o instanceof Collection<?>)
			return ((Collection<?>) o).size() == 0;
		if (o instanceof Map<?, ?>)
			return ((Map<?, ?>) o).size() == 0;
		return false;
	}
	
	public static final boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}
	/** Verifica se um email � v�lido. */
	public static boolean validateEmail(String email) {
		if (EmailValidator.getInstance().isValid(email))
			return true;

		return false;
	}
}
