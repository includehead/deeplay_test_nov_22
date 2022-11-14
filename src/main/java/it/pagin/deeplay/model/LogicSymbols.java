package it.pagin.deeplay.model;

public class LogicSymbols {
    private final String negationSymbolRegex;
    private final String orSymbol;
    private final String orSymbolRegex;
    private final String andSymbol;
    private final String andSymbolRegex;

    public LogicSymbols(final String negationSymbol, final String orSymbol, final String andSymbol) {
        this.andSymbol = andSymbol;
        this.andSymbolRegex = "(.*)" + andSymbol + "(.*)";
        this.negationSymbolRegex = "(.*)" + negationSymbol + "(.*)";
        this.orSymbol = orSymbol;
        this.orSymbolRegex = "(.*)" + orSymbol + "(.*)";
    }

    public String getNegationSymbolRegex() {
        return negationSymbolRegex;
    }

    public String getOrSymbol() {
        return orSymbol;
    }

    public String getOrSymbolRegex() {
        return orSymbolRegex;
    }

    public String getAndSymbol() {
        return andSymbol;
    }

    public String getAndSymbolRegex() {
        return andSymbolRegex;
    }
}
