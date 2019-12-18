package moneycalculator.view;

import moneycalculator.view.ExchangeRateLoader;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestExchangeRateLoader implements ExchangeRateLoader {
    
    private final String url = "https://api.exchangerate-api.com/v4/latest/EUR";

    @Override
    public List<ExchangeRate> load() {
        List<ExchangeRate> list = new ArrayList<>();
        try {
            URL urll = new URL(url);
            URLConnection connection = urll.openConnection();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line = reader.readLine();
                Date date = new Date(119,10,27);
                line = line.substring(line.indexOf("{", 1), line.length()-1);

                int index = 0;
                int indexAux = 0;
                
                while((index = line.indexOf(",",indexAux)) != -1) {
                    indexAux = line.indexOf(",", index + 1);
                    if(indexAux != -1) {
                        ExchangeRate exRate = new ExchangeRate(date,Double.valueOf(line.substring(index+7, indexAux)),
                                new Currency("USD","Dolar","$"),new Currency(line.substring(index+2, index+5),"MAAA","$"));
                        list.add(exRate);
                    } else {
                        ExchangeRate exRate = new ExchangeRate(date,Double.valueOf(line.substring(index+7, line.length() - 1)),
                                new Currency("USD","Dolar","$"),new Currency(line.substring(index+2, index+5),"MAAA","$"));
                        list.add(exRate);
                    }
                    indexAux = index + 1;
                }
            } catch (IOException ex) {
                System.out.println("io1");
                Logger.getLogger(RestExchangeRateLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            System.out.println("malformed");
            Logger.getLogger(RestExchangeRateLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("io2");
            Logger.getLogger(RestExchangeRateLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
