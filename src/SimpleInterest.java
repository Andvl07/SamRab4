class SimpleInterestCalculator implements InterestCalculator {
    @Override
    public double calculateInterest(double depositAmount, double interestRate) {
        return depositAmount * interestRate;
    }
}