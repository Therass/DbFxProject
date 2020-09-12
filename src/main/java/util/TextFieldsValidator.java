package util;

public class TextFieldsValidator {

    public boolean isValid(String... strings) {
        boolean isValidReturn = true;
        for (String string : strings) {
            if (string.isEmpty()) {
                isValidReturn = false;
                new AlertWindow("Fill textfields");
                break;
            }
        }
        return isValidReturn;
    }
}
