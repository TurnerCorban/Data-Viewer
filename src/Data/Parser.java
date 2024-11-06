package src.Data;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Parser {
    public static Float parseFloat(String text){
        float result;
        try {
            result = Float.parseFloat(text);
        } catch (NumberFormatException e) {
            result = -99;
        }
        return result;
    }
    public static Data parseData(String[] data){
        return new Data(
                data[0],
                data[1],
                Parser.parseFloat(data[2]),
                Parser.parseFloat(data[3]),
                Parser.parseFloat(data[4]),
                Parser.parseFloat(data[5]),
                Parser.parseFloat(data[6]),
                Parser.parseFloat(data[7]),
                Parser.parseFloat(data[8]),
                Parser.parseFloat(data[9]),
                Parser.parseFloat(data[10]),
                Parser.parseFloat(data[11]),
                Parser.parseFloat(data[12]),
                Parser.parseFloat(data[13]),
                Parser.parseFloat(data[14]),
                Parser.parseFloat(data[15]),
                Parser.parseFloat(data[16]),
                Parser.parseFloat(data[17]),
                Parser.parseFloat(data[18]),
                Parser.parseFloat(data[19]),
                Parser.parseFloat(data[20]),
                Parser.parseFloat(data[21])
        );
    }
}
