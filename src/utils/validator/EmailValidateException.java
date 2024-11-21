package utils.validator;

public class EmailValidateException extends Exception{

    /*
    Если необходимо создать исключение, достаточно наследоваться от одного из двух классов:
    1. Проверяемое исключение - Exception
    2. Непроверяемое - RuntimeException

    getMessage() - возвращает строку с коротким описанием исключения
     */

    public EmailValidateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Email validate exception | " + super.getMessage();
    }
}