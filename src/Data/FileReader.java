package src.Data;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.ArrayList;

public class FileReader{

    public static ArrayList<Data> readFile(String filename) throws IOException{
        String contents = Files.readString(Path.of(filename), StandardCharsets.UTF_8);

        List<String> lines = List.of(contents.split("\n"));
        ArrayList<Data> DataStr = new ArrayList<>();
        DataStr = lines.stream()
                .skip(1)
                .map(line -> line.split(","))
                .map(Parser::parseData)
                .collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
        return DataStr;
    }
}
