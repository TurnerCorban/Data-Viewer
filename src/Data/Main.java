package src.Data;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Data> data = FileReader.readFile("src/WorldInflationData_10yrs.csv");
        printData(data.get(0));
    }

    public static void printData(Data data) {
        System.out.println();
        System.out.println();
        System.out.println("Data:");
        System.out.println("Country: " + data.country());
        System.out.println("2014 " + data.yr2014());
        System.out.println("2015 " + data.yr2015());
        System.out.println("2016 " + data.yr2016());
        System.out.println("2017 " + data.yr2017());
        System.out.println("2018 " + data.yr2018());
        System.out.println("2019 " + data.yr2019());
        System.out.println("2020 " + data.yr2020());
        System.out.println("2021 " + data.yr2021());
        System.out.println("2022 " + data.yr2022());
        System.out.println("2023 " + data.yr2023());

    }
}
