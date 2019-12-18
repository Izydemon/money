package moneycalculator.view;

import moneycalculator.model.ExchangeRate;
import java.util.List;

public interface ExchangeRateLoader {

    List<ExchangeRate> load();
    
}
