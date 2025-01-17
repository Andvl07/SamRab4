import java.time.LocalDate;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        BankOperations bank = new BankOperations();

        // Добавляем клиентов
        bank.addClient("Иванов", "123456",
                new BankAccount("1001", 0.05, 10000, LocalDate.of(2022, 1, 1)),
                new BankAccount("1002", 0.07, 20000, LocalDate.of(2023, 5, 15)));
        bank.addClient("Петров", "654321",
                new BankAccount("2001", 0.06, 15000, LocalDate.of(2023, 2, 10)),
                new BankAccount("2002", 0.08, 30000, LocalDate.of(2024, 1, 1)),
                new BankAccount("2003", 0.08, 1000, LocalDate.of(2021, 1, 1), LocalDate.of(2022, 1, 1)));

        bank.printClients();

        // Находим клиента с наибольшей суммой процентов
        BankClient maxInterestClient = bank.findClientWithMaxInterest();
        double maxInterestAmount = bank.getMaxInterestAmount();
        if (maxInterestClient != null) {
            System.out.println("\nКлиент с наибольшей суммой процентов: " + maxInterestClient.getLastName() +
                    ", сумма: " + maxInterestAmount);
        }

        // Находим счет с минимальным вкладом и год открытия
        System.out.println("\n" + bank.findMinDepositAccount());

        // Перевод средств
        try {
            bank.transferMoney("1001", "2001", 5000);
            System.out.println("Выполнено, новые данные:");
            bank.printClients();
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Добавляем новых клиентов
        bank.addClient("Сидоров", "987654",
                new BankAccount("3001", 0.04, 25000, LocalDate.of(2023, 10, 1)));
        System.out.println("\nПосле добавления новых клиентов:");
        bank.printClients();

        // Закрываем счет
        BankClient petrov = bank.findClientByAccount("2003");
        petrov.getAccounts().stream().filter(account -> account.getAccountNumber().equals("2003")).findFirst()
                .ifPresent(account -> account.closeAccount(LocalDate.now()));

        // Удаляем клиентов, закрывших все счета
        bank.removeClosedAccountClients();
        System.out.println("\nПосле удаления закрывших счета:");
        bank.printClients();

        // Находим клиентов с долгосрочными вкладами
        List<BankClient> longTermClients = bank.findClientsWithLongTermDeposits(2);
        System.out.println("\nКлиенты с вкладами на 2 года и более:");
        longTermClients.forEach(client -> System.out.println(client.getLastName()));
    }
}