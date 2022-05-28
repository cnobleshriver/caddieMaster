package app;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.lang.Integer;

public class CaddieSheetParser {

    private ArrayList<Caddie> caddieSheet;

    public void readCaddieSheet(String pathString) throws IOException {

        File triageFile = new File(pathString);
        FileReader fReader = new FileReader(triageFile);
        BufferedReader bfReader = new BufferedReader(fReader);

        this.caddieSheet = new ArrayList<Caddie>();
        String line;
        while ((line = bfReader.readLine()) != null) {
            String name = line;
            String lastName = bfReader.readLine();
            String number = bfReader.readLine();
            String rankStr = bfReader.readLine();
            String daysSat = bfReader.readLine();

            Rank rank = Rank.valueOf(rankStr.toUpperCase());
            this.caddieSheet.add(
                    new Caddie(name, lastName, Integer.parseInt(number), rank, Integer.parseInt(daysSat)));
        }
        bfReader.close();
        fReader.close();
    }

    public ArrayList<Caddie> getCaddies() {
        return this.caddieSheet;
    }

}
