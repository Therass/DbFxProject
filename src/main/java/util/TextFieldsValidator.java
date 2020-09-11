package util;

public class TextFieldsValidator {
    public boolean isValid(String... strings) {
        boolean returningValue = true;
        for (String string : strings) {
            if (string.isEmpty()) {
                returningValue = false;
                new AlertWindow("Fill textfields");
                break;
            }
        }
        return returningValue;
    }
}
