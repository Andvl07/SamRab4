import java.time.LocalDate;

abstract class Account {
    String accountNumber;
    double interestRate;
    double depositAmount;
    LocalDate openingDate;
    LocalDate closingDate;

    public Account(String accountNumber, double interestRate, double depositAmount, LocalDate openingDate) {
        this.accountNumber = accountNumber;
        this.interestRate = interestRate;
        this.depositAmount = depositAmount;
        this.openingDate = openingDate;
        this.closingDate = null; // Счет открыт, пока не закрыт
    }

    public Account(String accountNumber, double interestRate, double depositAmount, LocalDate openingDate, LocalDate closingDate) {
        this.accountNumber = accountNumber;
        this.interestRate = interestRate;
        this.depositAmount = depositAmount;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
    }


    public void closeAccount(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public boolean isClosed(){
      return this.closingDate != null;
    }

    public abstract double calculateInterest(); // Абстрактный метод для расчета процентов

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", interestRate=" + interestRate +
                ", depositAmount=" + depositAmount +
                ", openingDate=" + openingDate +
                ", closingDate=" + closingDate +
                '}';
    }
}