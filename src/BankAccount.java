import java.time.LocalDate;
class BankAccount extends Account {
    public BankAccount(String accountNumber, double interestRate, double depositAmount, LocalDate openingDate) {
        super(accountNumber, interestRate, depositAmount, openingDate);
    }
    public BankAccount(String accountNumber, double interestRate, double depositAmount, LocalDate openingDate, LocalDate closingDate) {
        super(accountNumber, interestRate, depositAmount, openingDate, closingDate);
    }

    @Override
    public double calculateInterest() {
        return depositAmount * interestRate; // Простой расчет процентов
    }
}