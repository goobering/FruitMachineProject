package example.codeclan.com.fruitmachine.models;

import java.util.ArrayList;

import example.codeclan.com.fruitmachine.database.SymbolHandler;
import example.codeclan.com.fruitmachine.enums.SymbolDb;

/**
 * Created by user on 07/07/2017.
 */

public class Reel
{
    private ArrayList<Symbol> symbols;

    public Reel(ArrayList<Symbol> symbols)
    {
        this.symbols = symbols;
    }

    public ArrayList<Symbol> getSymbols()
    {
        return symbols;
    }

    public void setSymbols(ArrayList<Symbol> symbols)
    {
        this.symbols = symbols;
    }
}
