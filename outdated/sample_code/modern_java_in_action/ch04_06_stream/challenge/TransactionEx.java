package old.sample_code.modern_java_in_action.ch04_06_stream.challenge;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionEx {
    public static void main(String[] args) {
        TransactionData transactionData = new TransactionData();
        List<Transaction> transactions = transactionData.transactions;

        // Q1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정렬하시오.
        List<Integer> transactionsOccurredIn2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .map(Transaction::getValue)
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());

        transactionsOccurredIn2011.forEach(System.out::println);
        System.out.println();

        // Q2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
        List<String> cities = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());

        cities.forEach(System.out::println);
        System.out.println();

        // Q3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
        List<Trader> tradersWorkingAtCambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        tradersWorkingAtCambridge.forEach(trader -> System.out.println(trader.getName()));
        System.out.println();

        // Q4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오
        List<String> traderNames = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());

        traderNames.forEach(System.out::println);
        System.out.println();

        // Q5. 밀라노에 거래자가 있는가?
        boolean isTraderWorksInMilan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"));

        System.out.println("Is there anybody who works in Milan? " + isTraderWorksInMilan);
        System.out.println();

        // Q6. 케임브리지에 거주하는 거래자의 모든 트랜잭션의 값을 출력하시오.
        List<Integer> transactionValuesOfTraderWhoLivesInCambridge = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(Collectors.toList());

        transactionValuesOfTraderWhoLivesInCambridge.forEach(System.out::println);
        System.out.println();

        // Q7. 전체 트랜잭션 중 최댓값은 얼마인가?
        Optional<Integer> maxValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max); // `reduce((x, y) -> x > y ? x : y)` 과 동일

        maxValue.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("There is No Value")
        );

        // Q8. 전체 트랜잭션 중 최솟값은 얼마인가?
        Optional<Integer> minValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);

        minValue.ifPresent(System.out::println);
    }
}
