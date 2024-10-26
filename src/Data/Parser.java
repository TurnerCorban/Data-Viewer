package src.Data;

public class Parser {
    public static int parseInt(String text){
        int result = 0;
        System.out.println(text);
        try {
            result = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            result = -1;
        }
        return result;
    }
    public static long parseLong(String text){
        long result = 0;
        try {
            result = Long.parseLong(text);
        } catch (NumberFormatException e) {
            result = -1;
        }
        return result;
    }
    public static Float parseFloat(String text){
        float result = 0;
        try {
            result = Float.parseFloat(text);
        } catch (NumberFormatException e) {
            result = -1;
        }
        return result;
    }
    public static Data parseData(String[] data){
        return new Data(
                data[0],
                Parser.parseFloat(data[2]),
                Parser.parseFloat(data[3]),
                Parser.parseFloat(data[4]),
                Parser.parseFloat(data[5]),
                Parser.parseFloat(data[6]),
                Parser.parseFloat(data[7]),
                Parser.parseFloat(data[8]),
                Parser.parseFloat(data[9]),
                Parser.parseFloat(data[10]),
                Parser.parseFloat(data[11])
        );
    }
}
