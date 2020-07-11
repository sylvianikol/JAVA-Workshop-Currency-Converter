package currencyconverter.console;

import currencyconverter.domain.io.Logger;

public class ConsoleLogger implements Logger {

    @Override
    public void logLine(String line) {
        System.out.println(line);
    }
}
