package example.codeclan.com.fruitmachine.interfaces;

import java.util.ArrayList;

import example.codeclan.com.fruitmachine.models.Symbol;

/**
 * Created by user on 10/07/2017.
 */

public interface ISymbolProvider
{
    int addSymbol(Symbol symbol);
    Symbol getSymbol(int id);
    Symbol getSymbolByImageName(String imageName);
    ArrayList<Symbol> getAllSymbolsFromSet(String imageSetName);
    void updateSymbolLocation(int id, String location);
}
