## "Currency Exchange Office" Project

**Project Description:**
Develop a console application to simulate the operation of a currency exchange office. Users can register accounts, open accounts in various currencies, deposit and withdraw funds, perform currency exchanges, and view their transaction history.

**Technical Specification:**

1. **Multi-Layer Application Architecture:**
   - **Model (Model):** Define entities such as User, Account, Transaction, Currency, Exchange Rate, and other necessary objects.
   - **Service (Service):** Develop business logic for the application, including managing user data, executing currency transactions, etc.
   - **Repository (Repository):** Create a layer for interaction with data storage (e.g., in-memory or file-based).

2. **Data Organization and Account Management:**
   - Implement mechanisms for managing user data, including their accounts and transaction ledgers.
   - Users should have accounts in at least 3 different currencies.

3. **Currency Transaction Management:**
   - Provide functionality for currency transactions, including deposits, exchanges, and viewing transaction history.

4. **Data Validation:**
   - Implement validation for new user registration, ensuring the email follows the standard email format and the password meets set security criteria (e.g., minimum length, presence of letters and numbers).

5. **User Interface:**
   - Implement a console menu for accessing the application's functionalities.

6. **Testing:**
   - Develop JUnit tests to check all aspects of the application's functionality, with a focus on testing the service layer.

7. **Using the Map Collection:**
   - Actively use the `Map` collection to organize and manage data, such as user accounts, accounts, exchange rates, and other suitable scenarios.
   - For example:
      - Storing a list of user accounts, where the key is the user ID and the value is a list of their accounts.
      - Storing exchange rates, where the key is the currency code and the value is the current rate.

**Optional:**
- In case of validation failure, the application should throw an exception, which should then be properly handled. This may include informing the user of the reason for the error and offering a retry.
- Functionality to view historical exchange rates.

## User Functionality:
- Register a new user (with email and password validation)
- Log into an account
- View balance (total on all accounts or a specific account)
- Deposit funds in a selected currency (check for the existence of such an account. If absent - open the corresponding account)
- Withdraw funds from an account (with necessary checks for operation feasibility)
- Open a new account
- Close an account (checks: are there funds on the account? What to do?)
- View transaction history (for all accounts / for a specific currency)
- Currency exchange (transfer funds from one account to another with the corresponding cross-rate)

## Administrator Functionality
- Change currency exchange rates
- Add or remove currencies from the list (When deleting, check if there are open accounts in this currency for users. If so - what to do?)
- View user transactions
- View transactions by currency
- Assign another user as an administrator (moderator/cashier)

## Data Storage Organization (lists of users, currencies, exchange rates, etc.)

## Optional
- History of currency exchange rate changes
- Custom exceptions and their handling

<details style="margin-top: 16px">
  <summary style="cursor: pointer; color: green;"><b>на Русском</b></summary>


## Проект "Обменный пункт валюты"

**Описание проекта:**
Разработать консольное приложение для имитации работы обменного пункта валюты.
Пользователи могут регистрировать аккаунты, открывать счета в различных валютах, пополнять счета, снимать средства со счетов, осуществлять обмен валюты, а также просматривать историю своих операций.

**Техническое задание:**

1. **Многослойная архитектура приложения:**
    - **Модель (Model):** Определить сущности, такие как пользователь, аккаунт, операция, валюта, курс валют и другие необходимые объекты.
    - **Сервис (Service):** Разработать бизнес-логику приложения, включая управление данными пользователей, выполнение валютных операций и т.д.
    - **Репозиторий (Repository):** Создать слой для взаимодействия с хранилищем данных (например, в памяти или файле(-ах)).

2. **Организация данных и управление аккаунтами:**
    - Реализовать механизмы для управления данными пользователя, включая их аккаунты и ведение счетов.
    - У пользователя могут быть счета минимум в 3 разных валютах.

3. **Управление валютными операциями:**
    - Обеспечить функциональность для осуществления валютных операций, включая пополнение, обмен и просмотр истории операций.

4. **Валидация данных:**
    - При регистрации нового пользователя реализовать валидацию введенного email и пароля. Валидация должна убедиться, что email соответствует стандартному формату электронной почты, а пароль соответствует заданным критериям безопасности (например, минимальная длина, наличие букв и цифр).

5. **Пользовательский интерфейс:**
    - Реализовать консольное меню для доступа к функциональности приложения.

6. **Тестирование:**
    - Разработать JUnit тесты для проверки всех аспектов функциональности приложения, особое внимание уделить тестированию сервисного слоя.

7. **Использование коллекции Map:**
    - В процессе разработки приложения активно использовать коллекцию `Map` для организации и управления данными, такими как учетные записи пользователей, счета, курсы валют и другие подходящие сценарии.
    - Например:
         - Для хранения пользователей, где ключ - id пользователя, а значение объект пользователя с таким id.
         - Для хранения списка аккаунтов пользователя, где ключом будет идентификатор пользователя, а значением - список его аккаунтов.
         - Для хранения курсов валют, где ключом будет код валюты, а значением - текущий курс.

**Опционально:**
- В случае провала валидации, приложение должно выбрасывать исключение, которое затем должно быть корректно обработано. Это может включать информирование пользователя о причине ошибки и предложение повторить попытку ввода данных.
- Функционал для просмотра исторических курсов валют.


## Функционал пользователя:
- Регистрация нового пользователя (с валидацией email и password)
- Вход в аккаунт
- Просмотр баланса (остатка на всех счетах или каком-то конкретном счете)
- Пополнение счета в выбранной валюте (проверка существования такого счета у пользователя. Если отсутствует - открыть соответствующий счет)
- Снятие средств со счета (с соответствующими проверками возможности операции)
- Открытие нового счета
- Закрытие счета (с проверками: если на счету есть средства? что делать?)
- Просмотр истории операций (по всем счетам / по конкретной валюте)
- Обмен валют (перевод средств с одного счета на другой с соответствующим кросс-курсом)

## Функционал администратора
- Изменение курса валюты
- Возможность добавление или удаление валют из списка (При удалении должна быть проверка, есть ли открытые счета у пользователей в этой валюте? Если есть - что делать?)
- Просмотр операций пользователя
- Просмотр операций по валюте
- Назначение другого пользователя администратором (модератором / кассиром)

## Организация хранения данных (списки пользователей, валют, курсов и т.д. и т.п.)

## Опционально
- История изменения курсов валюты
- Свои классы исключений и их обработка
</details>