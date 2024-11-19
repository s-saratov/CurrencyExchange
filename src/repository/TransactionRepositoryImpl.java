package repository;

import model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

    private static final double FEE_RATE = 1.5;
    private BigDecimal totalBaseCurrencyFeesCollected = BigDecimal.ZERO;

    AccountRepository accountRepository;
    CurrencyRateRepository currencyRateRepository;

    List<Transaction> transactionList;


    @Override
    public void depositTransaction(int accountID, TransactionType transactionType, BigDecimal amount) {

        //находим целевой счёт
        Account targetAccount = accountRepository.getByID(accountID);

        // рассчитываем комиссию и вычитаем её из суммы операции
        BigDecimal depositFee = amount.multiply(BigDecimal.valueOf(FEE_RATE));
        BigDecimal netAmount = amount.subtract(depositFee);

        //накапливаем сумму комиссии
        //TODO перевести в евро перед отправленим в накопитель
        //получаем код валюты аккаунта-рецепиента
        String targetAccountCurrencyCode = targetAccount.getCurrency().getCurrencyCode();
        BigDecimal depositFeeEuro = convertToBaseCurrency(targetAccountCurrencyCode, depositFee);
        totalBaseCurrencyFeesCollected = totalBaseCurrencyFeesCollected.subtract(depositFeeEuro);

        //зачисляем сумму за вычетом комиссии на целевой счёт и обновляем баланс аккаунта
        BigDecimal updateBalance = targetAccount.getBalance().add(netAmount);
        targetAccount.setBalance(updateBalance);

        //создаём объект трансакции
        Transaction depositTransaction = new Transaction(
                accountID,
                transactionType,
                amount
        );

        //добавляем нашу операцию в список трансакций
        transactionList.add(depositTransaction);
    }

    @Override
    public void withdrawTransaction(int accountID, TransactionType transactionType, BigDecimal amount) {

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

        //накапливаем сумму комиссии, переведенную в евро
        //TODO перевести в евро перед отправленим в накопитель
        //получаем код валюты аккаунта
        String sourceAccountCurrencyCode = sourceAccount.getCurrency().getCurrencyCode();
        BigDecimal withdrawFeeEuro = convertToBaseCurrency(sourceAccountCurrencyCode, withdrawFee);
        totalBaseCurrencyFeesCollected = totalBaseCurrencyFeesCollected.add(withdrawFeeEuro);

        //создаём объект трансакции
        Transaction withdrawTransaction = new Transaction(
                accountID,
                transactionType,
                amount
        );

        //добавляем нашу операцию в список трансакций
        transactionList.add(withdrawTransaction);
    }

    @Override
    public BigDecimal convertToBaseCurrency(String fromCurrencyCode, BigDecimal amount) {

        //получили курс валюты
        BigDecimal exchangeRate = currencyRateRepository.getCurrencyRates(fromCurrencyCode);

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
    public boolean exchangeCurrency(int sourceAccountID, int targetAccountID, BigDecimal amount) {
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
        BigDecimal fromCurrencyRate = currencyRateRepository.getCurrencyRates(fromCurrencyCode);
        BigDecimal toCurrencyRate = currencyRateRepository.getCurrencyRates(toCurrencyCode);

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
        //накапливаем сумму комиссии
        totalBaseCurrencyFeesCollected = totalBaseCurrencyFeesCollected.add(exchangeFeeEuro);

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
                sourceAccountID,
                targetAccountID,
                amount
        );

        //сохраняем транзакцию в репозитории
        transactionList.add(exchangeTransaction);

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

        List<Transaction> accountTransaction = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            /*
            if () {
            }

             */
        }

        return List.of();
    }

    @Override
    public List<Transaction> getAllTransactions(String currentCode) {
        return transactionList;
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
