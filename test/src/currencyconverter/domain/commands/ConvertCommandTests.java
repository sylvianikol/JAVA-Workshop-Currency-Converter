package currencyconverter.domain.commands;

import currencyconverter.domain.entities.Money;
import currencyconverter.domain.external.ExchangeService;
import currencyconverter.domain.io.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class ConvertCommandTests {
    static class ExchangeServiceStub implements ExchangeService {

        @Override
        public Money exchange(Money from, String toCurrency) {
            return null;
        }
    }

    @Test
    public void calls_exchange_service_with_with_arguments_from_input() {
        // GIVEN
        ExchangeService serviceMock = Mockito.mock(ExchangeService.class);
        Money sixLeva = new Money(new BigDecimal("6"), "BGN");
        Money threeUSD = new Money(new BigDecimal("3"), "USD");


        Mockito.when(serviceMock.exchange(ArgumentMatchers.any(), ArgumentMatchers.anyString()))
                .thenReturn(threeUSD);

        Logger loggerStub = new Logger() {
            @Override
            public void logLine(String line) {
                // do nothing
            }
        };

        ConvertCommand.Input input = new ConvertCommand.Input(sixLeva, "USD");

        // WHEN
        ConvertCommand convertCommand = new ConvertCommand(serviceMock, loggerStub);
        convertCommand.execute(input);

        // THEN
        Mockito.verify(serviceMock, Mockito.times(2)).exchange(sixLeva, "BGN");

    }

    @Test
    public void logs_returned_value() {
        // GIVEN
        ExchangeService serviceMock = Mockito.mock(ExchangeService.class);
        Money sixLeva = new Money(new BigDecimal("6"), "BGN");
        Money threeUSD = new Money(new BigDecimal("3"), "USD");


        Mockito.when(serviceMock.exchange(ArgumentMatchers.any(), ArgumentMatchers.anyString()))
                .thenReturn(threeUSD);

        Logger loggerStub = new Logger() {
            @Override
            public void logLine(String line) {
                Assert.assertEquals(threeUSD.toString(), line);
            }
        };

        ConvertCommand.Input input = new ConvertCommand.Input(sixLeva, "USD");

        // WHEN
        ConvertCommand convertCommand = new ConvertCommand(serviceMock, loggerStub);
        convertCommand.execute(input);

        // THEN assert in logger stub

    }
}