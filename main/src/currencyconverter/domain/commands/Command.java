package currencyconverter.domain.commands;

public interface Command<T extends EmptyInput> {
    void execute(T command);
}
