import java.time.LocalDate;
class BankAccount extends Account {

    double interestRate;
    public BankAccount(String accountNumber, double depositAmount, LocalDate openingDate, double interestRate, InterestCalculator interestCalculator) {
        super(accountNumber, depositAmount, openingDate,interestCalculator);
        this.interestRate = interestRate;
    }
      public BankAccount(String accountNumber, double depositAmount, LocalDate openingDate,LocalDate closingDate, double interestRate, InterestCalculator interestCalculator) {
        super(accountNumber, depositAmount, openingDate, closingDate, interestCalculator);
          this.interestRate = interestRate;
    }
    @Override
    public double calculateInterest() {
        return interestCalculator.calculateInterest(depositAmount, interestRate);
    }
}