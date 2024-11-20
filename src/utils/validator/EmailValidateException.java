package utils.validator;

/**
 * @author olgakharina
 * @date 20.11.24
 *//**
 * Исключение для обработки ошибок валидации email.
 */
public class EmailValidateException extends Exception {

  /**
   * Конструктор исключения с сообщением об ошибке.
   *
   * @param message Сообщение, описывающее причину исключения.
   */
  public EmailValidateException(String message) {
    super(message);
  }

  /**
   * Переопределение метода getMessage для более подробного описания исключения.
   *
   * @return Сообщение об исключении.
   */
  @Override
  public String getMessage() {
    return "Email validation exception: " + super.getMessage();
  }
}
