package Model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class Entry implements Comparable<Entry> {

    private String folder;

    @CsvBindByPosition(position = 0)
    private String title;

    @CsvBindByPosition(position = 1)
    private String rocArea;

    @CsvBindByPosition(position = 2)
    private String rocError;

    @CsvBindByPosition(position = 3)
    private String title2;

    @CsvBindByPosition(position = 4)
    private String rocArea2;

    @CsvBindByPosition(position = 5)
    private String rocError2;

    @CsvBindByPosition(position = 6)
    private String nTrs;

    @CsvBindByPosition(position = 7)
    private String nVs;

    @CsvBindByPosition(position = 8)
    private String time_trs;

    @CsvBindByPosition(position = 9)
    private String time_vs;

    @CsvBindByPosition(position = 10)
    private String c;

    @CsvBindByPosition(position = 11)
    private String nSv;

    @CsvBindByPosition(position = 12)
    private String nSvPlus;

    @CsvBindByPosition(position = 13)
    private String nSvMinus;

    @CsvBindByPosition(position = 14)
    private String last;

    public String getRocArea2() {
        Double v = Double.valueOf(this.fixDouble(this.rocArea2)) * 1000000000;
        return String.valueOf(v.intValue());
    }

    public String getTitle() {
        return  this.title;
    }

    public String getArea() {
        return this.rocArea;
    }

    public String[] getCsvResources() {
        return  new String[]{"svm_00", this.title, this.fixDouble(this.rocArea), this.fixDouble(this.rocError), this.title2, this.fixDouble(this.rocArea2), this.fixDouble(this.rocError2), this.fixDouble(this.nTrs), this.fixDouble(this.nVs), this.fixDouble(this.time_trs), this.fixDouble(this.time_vs), this.c, this.nSv, this.nSvPlus, this.nSvMinus, this.last};
    }

    @Override
    public int compareTo(Entry entry) {
        try {
            int i1 = Integer.parseInt(this.getRocArea2());
            int i2 = Integer.parseInt(entry.getRocArea2());
            return i2 - i1;
        } catch(NumberFormatException e) {
            return -1;
        }
    }

    private String fixDouble(String input) {
        String fixed = input.replace(",", ".");
        return  fixed;
    }
}
