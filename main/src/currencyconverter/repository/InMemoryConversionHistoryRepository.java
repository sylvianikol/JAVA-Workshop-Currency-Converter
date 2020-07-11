package currencyconverter.repository;

import currencyconverter.domain.entities.Money;
import currencyconverter.domain.repository.ConversionHistoryRepository;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryConversionHistoryRepository implements ConversionHistoryRepository {
    private final Deque<String> history;

    public InMemoryConversionHistoryRepository() {
        this.history = new ArrayDeque<>();
    }

    @Override
    public List<String> getLast(int n) {
        return history.stream()
                .limit(n)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Money from, Money to) {
        String format = String.format("from: %s to %s", from, to);
        history.push(format);
    }

//    public static void main(String[] args) {
//        InMemoryConversionHistoryRepository repo = new InMemoryConversionHistoryRepository();
//        repo.save(new Money(BigDecimal.ONE, "USD"), new Money(BigDecimal.ONE, "BGN"));
//        repo.save(new Money(BigDecimal.TEN, "USD"), new Money(BigDecimal.TEN, "BGN"));
//
//        repo.getLast(2).forEach(str -> System.out.println(str));
//    }
}
