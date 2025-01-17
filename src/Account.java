import java.time.LocalDate;

abstract class Account {
    String accountNumber;
    double depositAmount;
    LocalDate openingDate;
    LocalDate closingDate;
    InterestCalculator interestCalculator;

    public Account(String accountNumber, double depositAmount, LocalDate openingDate, InterestCalculator interestCalculator) {
        this.accountNumber = accountNumber;
        this.depositAmount = depositAmount;
        this.openingDate = openingDate;
        this.closingDate = null;
        this.interestCalculator = interestCalculator;
    }
  public Account(String accountNumber, double depositAmount, LocalDate openingDate,LocalDate closingDate, InterestCalculator interestCalculator) {
        this.accountNumber = accountNumber;
        this.depositAmount = depositAmount;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.interestCalculator = interestCalculator;
    }
  

    public void closeAccount(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public boolean isClosed(){
        return this.closingDate != null;
    }

    public abstract double calculateInterest();

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", depositAmount=" + depositAmount +
                ", openingDate=" + openingDate +
                ", closingDate=" + closingDate +
                '}';
    }
}