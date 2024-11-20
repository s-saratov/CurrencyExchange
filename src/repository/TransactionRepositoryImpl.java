package repository;

import model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

    private static final double FEE_RATE = 1.5;

    AccountRepository accountRepository;
    CurrencyRateRepository currencyRateRepository;

    List<Transaction> transactionList;

    @Override
    public void feeDepositTransaction(BigDecimal feeAmount) {

        //находим сервисный счёт обменки
        //TODO указать ID сервисного счёта обменки
        Account serviceAccount = accountRepository.getByID(777);

        //проверяем, был ли найден
        if (serviceAccount == null) {
            System.out.println("Account not found!");
            return;
        }

        // Получаем текущий список транзакций аккаунта
        List<Transaction> serviceAccountTransactions = accountRepository.getTransactions();

        //проверяем, найден ли список транзакций, и создаём его, если он отсутствует
        if (serviceAccountTransactions == null) {
            System.out.println("Transaction list from service account not found!");
            return;
        }

        //зачисляем сумму комиссии на сервисный счёт и обновляем баланс аккаунта
        BigDecimal updateBalance = serviceAccount.getBalance().add(feeAmount);
        serviceAccount.setBalance(updateBalance);

        //создаём объект трансакции
        Transaction feeDepositTransaction = new Transaction(feeAmount);

        //добавляем нашу трансакцию в список трансакций
        transactionList.add(feeDepositTransaction);

        //TODO
        // добавить трансакцию в список трансакций сервисного счёта обменки
        accountRepository.getTransactions().add(feeDepositTransaction);
    }

    @Override
    public void depositTransaction(int userId, int accountID, TransactionType transactionType, BigDecimal amount) {

        //находим целевой счёт
        Account targetAccount = accountRepository.getByID(accountID);

        // рассчитываем комиссию и вычитаем её из суммы операции
        BigDecimal depositFee = amount.multiply(BigDecimal.valueOf(FEE_RATE));
        BigDecimal netAmount = amount.subtract(depositFee);

        //зачисляем сумму комиссии на счёт
        //получаем код валюты аккаунта-рецепиента
        String targetAccountCurrencyCode = targetAccount.getCurrency().getCurrencyCode();
        BigDecimal depositFeeEuro = convertToBaseCurrency(targetAccountCurrencyCode, depositFee);
        feeDepositTransaction(depositFeeEuro);

        //зачисляем сумму за вычетом комиссии на целевой счёт и обновляем баланс аккаунта
        BigDecimal updateBalance = targetAccount.getBalance().add(netAmount);
        targetAccount.setBalance(updateBalance);

        //создаём объект трансакции
        Transaction depositTransaction = new Transaction(
                userId,
                accountID,
                transactionType,
                amount
        );

        //добавляем нашу операцию в список трансакций
        transactionList.add(depositTransaction);

        //TODO
        // добавить трансакцию в список трансакций счёта
        accountRepository.getTransactions().add(depositTransaction);
    }

    @Override
    public void withdrawTransaction(int userId, int accountID, TransactionType transactionType, BigDecimal amount) {

        //находим счёт, с которого будет произведено списание средств
        Account sourceAccount = accountRepository.getByID(accountID);

        //рассчитываем комиссию и вычитаем её из суммы операции
        BigDecimal withdrawFee = amount.multiply(BigDecimal.valueOf(FEE_RATE));
        BigDecimal totalAmount = amount.add(withdrawFee);

        // Проверяем, достаточно ли средств на счете снятия суммы + комиссия
        if (sourceAccount.getBalance().compareTo(totalAmount) < 0) {
            throw new IllegalArgumentException("Insufficient funds on the account to cover the transaction and withdrawfee.");
        }

        //списываем сумму + комиссия со счёта и обновляем баланс аккаунта
        BigDecimal updateBalance = sourceAccount.getBalance().add(totalAmount);
        sourceAccount.setBalance(updateBalance);

        //зачисляем сумму комиссии, переведенную в евро
        //TODO перевести в евро перед отправленим в накопитель
        //получаем код валюты аккаунта
        String sourceAccountCurrencyCode = sourceAccount.getCurrency().getCurrencyCode();
        BigDecimal withdrawFeeEuro = convertToBaseCurrency(sourceAccountCurrencyCode, withdrawFee);
        feeDepositTransaction(withdrawFeeEuro);

        //создаём объект трансакции
        Transaction withdrawTransaction = new Transaction(
                userId,
                accountID,
                transactionType,
                amount
        );

        //добавляем нашу операцию в список трансакций
        transactionList.add(withdrawTransaction);

        //TODO
        // добавить трансакцию в список трансакций счёта
        accountRepository.getTransactions().add(withdrawTransaction);
    }

    @Override
    public BigDecimal convertToBaseCurrency(String fromCurrencyCode, BigDecimal amount) {

        //получили курс валюты
        BigDecimal exchangeRate = currencyRateRepository.getCurrencyRate(fromCurrencyCode);

        /*
        // Проверяем, что курс валюты корректен и не равен нулю
        if (exchangeRate == null || exchangeRate.compareTo(BigDecimal.ZERO) == 0) {
        throw new IllegalArgumentException("Invalid or missing exchange rate for currency: " + fromCurrencyCode);
        }
        */

        //возвращаем рассчитанную сумму в базовой валюте
        return amount.multiply(exchangeRate);
    }

    @Override
    public boolean exchangeCurrency(int userID, int sourceAccountID, int targetAccountID, BigDecimal amount) {
        // проверяем наличие обоих аккаунтов
        Account fromAccount = accountRepository.getByID(sourceAccountID);
        Account toAccount = accountRepository.getByID(targetAccountID);

        // если один или оба счета не найдены возвращаем false
        if (fromAccount == null || toAccount == null) {
            System.out.println("Accounts not found!");
            return false;
        }

        // проверяем, достаточно ли средств на исходном счете, если нет - возвращаем false
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            System.out.println("Insufficient funds in the source account.");
            return false;
        }

        // вытаскиваем из исходного и целеыого аккаунтов валюты
        String fromCurrencyCode = fromAccount.getCurrency().getCurrencyCode();
        String toCurrencyCode = toAccount.getCurrency().getCurrencyCode();

        //вытаскиваем из репозитория курсов валют - курсы необходимых нам валют
        BigDecimal fromCurrencyRate = currencyRateRepository.getCurrencyRate(fromCurrencyCode);
        BigDecimal toCurrencyRate = currencyRateRepository.getCurrencyRate(toCurrencyCode);

        //проверяем не нулевые ли мы получили курсы
        if (fromCurrencyRate == null || toCurrencyRate == null || fromCurrencyRate.equals(BigDecimal.ZERO) || toCurrencyRate.equals(BigDecimal.ZERO)) {
            System.out.println("Currency rates not found");
            return false; // валютные курсы не найдены
        }

        //конвертируем сумму из исходной валюты в базовую валюту
        BigDecimal baseCurrencyAmount = convertToBaseCurrency(fromCurrencyCode, amount);

        //рассчитываем комиссию и добавляем её из суммы операции
        BigDecimal exchangeFeeEuro = baseCurrencyAmount.multiply(BigDecimal.valueOf(FEE_RATE));
        BigDecimal netBaseCurrencyAmount = baseCurrencyAmount.subtract(exchangeFeeEuro);
        //зачисляем сумму комиссии на счёт обменки
        feeDepositTransaction(exchangeFeeEuro);

        // конвертируем сумму из базовой валюты в целевую валюту
        BigDecimal targetCurrencyAmount = netBaseCurrencyAmount.multiply(toCurrencyRate).setScale(2, RoundingMode.HALF_UP);

        //вычитаем сумму с исходного счета
        BigDecimal newFromAccountBalance = fromAccount.getBalance().subtract(amount);
        fromAccount.setBalance(newFromAccountBalance);

        //зачисляем конвертированную сумму за вычетом комиссии на целевой счет
        BigDecimal newToAccountBalance = toAccount.getBalance().subtract(targetCurrencyAmount);
        toAccount.setBalance(newToAccountBalance);

        //создаем транзакции для обмена валюты
        Transaction exchangeTransaction = new Transaction(
                userID,
                sourceAccountID,
                targetAccountID,
                amount
        );

        //сохраняем транзакцию в репозитории
        transactionList.add(exchangeTransaction);

        //TODO
        // добавить трансакцию в список трансакций счёта
        accountRepository.getTransactions().add(exchangeTransaction);

        return true; //операция выполнена успешно
    }

    @Override
    public Transaction findTransaction(int id) {

        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionID() == id) {
                return transaction;
            }
        }
        return null;
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(int accountId) {

        //проверяем, есть ли записи для данного аккаунта в карте accountOperations
        if (accountRepository.getAccountTransactions().containsKey(accountId)) {
            //если есть, возвращаем список операций по данному аккаунту
            return accountRepository.getAccountTransactions().get(accountId);
        } else {
            //если записи нет, возвращаем пустой список
            return List.of();
        }
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {

        List<Transaction> userTransactionList = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            if (transaction.getUserID() == userId) {
                userTransactionList.add(transaction);
            }
        }
        return userTransactionList;
    }

    @Override
    public List<Transaction> getAllTransactions(String currentCode) {
        return transactionList;
    }

    @Override
    public List<Transaction> getAllTransactionsByCurrency(String currentCode) {

        List<Transaction> transactionsListByCurrency = new ArrayList<>();

        for (Transaction transaction : transactionList){
            Account account = accountRepository.getByID(transaction.getAccountID());
            if (account.getCurrency().getCurrencyCode().equals(currentCode)) {
                transactionsListByCurrency.add(transaction);
            }
        }
        return transactionsListByCurrency;
    }


    @Override
    public List<Transaction> getTransactionsByDate(LocalDate date) {

        List<Transaction> transactionsForDate = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            if (transaction.getTimestamp().toLocalDate().equals(date)) {
                transactionsForDate.add(transaction);
            }
        }
        return transactionsForDate;
    }

    @Override
    public boolean canPurchaseCurrency(int accountId, String targetCurrency, BigDecimal targetAmount) {
        return false;
    }

    @Override
    public boolean deleteTransaction(int transactionId) {

        Transaction deletedTransaction = null;

        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionID() == transactionId) {
                deletedTransaction = transaction;
            }
        }
        if (deletedTransaction != null) {
            transactionList.remove(deletedTransaction); // Удаляем найденную транзакцию
            return true; // Успешное удаление
        }
        return false;
    }
}
