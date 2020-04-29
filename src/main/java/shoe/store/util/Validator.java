package shoe.store.util;

public class Validator {

	public static boolean checkId(String id) {
		return id != null && id.matches("[0-9]+");
	}
}
