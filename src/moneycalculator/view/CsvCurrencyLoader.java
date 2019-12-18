package moneycalculator.view;

import moneycalculator.view.CurrencyLoader;
import moneycalculator.model.Currency;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvCurrencyLoader implements CurrencyLoader {

    private final File file = new File("currencies.csv");

    @Override
    public List<Currency> create() {
        List<Currency> list = new ArrayList<>();
        auxCreate(list);
        return list;
    }

    private Currency createCurrency(String line) {
        String[] split = line.split(",");
        return new Currency(split[0],split[1],split[2]);
    }

    private void auxCreate(List<Currency> list) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while(true) {
                String line = reader.readLine();
                if (line == null) break;
                Currency currency = createCurrency(line);
                list.add(currency);
            }
        } catch (Exception ex) {
            System.out.println("Se ha producido una excepción");
        }    
    }
    
}
