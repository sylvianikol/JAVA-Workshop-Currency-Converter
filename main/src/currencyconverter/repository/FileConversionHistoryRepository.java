package currencyconverter.repository;

import currencyconverter.domain.entities.Money;
import currencyconverter.domain.repository.ConversionHistoryRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileConversionHistoryRepository implements ConversionHistoryRepository {

    private Path filePath;

    public FileConversionHistoryRepository(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> getLast(int n) {
        try {
            Files.readAllLines(filePath);
            // TODO: get last N rows
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Money from, Money to) {
        String format = String.format("from: %s to %s", from, to);
        try {
            Files.write(filePath, List.of(format), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
