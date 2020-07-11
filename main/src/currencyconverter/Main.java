package currencyconverter;

import currencyconverter.console.ConsoleRunner;
import currencyconverter.domain.commands.Command;
import currencyconverter.domain.commands.ConvertCommand;
import currencyconverter.domain.commands.EmptyInput;
import currencyconverter.domain.commands.EndCommand;
import currencyconverter.domain.entities.Money;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws IOException {
        ConsoleRunner runner = new ConsoleRunner();
        runner.run();
    }
}
