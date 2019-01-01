import Model.Entry;
import Model.Stack;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CsvTool extends JFrame implements ActionListener {

    private JButton btnStart;
    private JFileChooser chooser;

    private static final String RESULT_FILE = "_overview__svm_00_orig.csv";
    private static final String RESULT_FILE_SORTED = "_overview__svm_00_sortByAUCvs.csv";

    private File folder;
    private int count = 0;

    private Stack stack;

    public CsvTool() {
        this.stack = new Stack();
        initUI();
    }

    private void initUI() {
        setTitle("Parsování CSV");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        btnStart = new JButton("Načíst složku a parsovat");
        btnStart.addActionListener(this);
        add(btnStart);
    }
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            CsvTool ct = new CsvTool();
            ct.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Vyber složku");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.folder = chooser.getSelectedFile();
            try {
                this.loadFiles(chooser.getSelectedFile());
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    this.writeCSVFile();
                    this.writeCSVSortedFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            JOptionPane.showMessageDialog(null, "Hotovo, zpracovano " + this.count + " souboru" , "výsledek", JOptionPane.INFORMATION_MESSAGE);
            this.count = 0;
        }
        else {
            System.out.println("No Selection ");
        }
    }

    private void loadFiles(File dir) throws IOException {
        try {
            File[] files = dir.listFiles();
            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    try {
                        int i1 = Integer.parseInt(f1.getName());
                        int i2 = Integer.parseInt(f2.getName());
                        return i1 - i2;
                    } catch(NumberFormatException e) {
                        return -1;
                    }
                }
            });

            for (File file : files) {
                if (file.isDirectory()) {
                    loadFiles(file);
                } else {
                    if (this.getExtension(file).equals("csv") && !file.getName().equals(RESULT_FILE) && !file.getName().equals(RESULT_FILE_SORTED)) {
                        this.count++;
                        this.readCSVFile(file);
                    } else {
                        System.out.println("skipping --- "  + file.getCanonicalPath());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readCSVFile(File file) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
        ) {
            CsvToBean<Entry> csvToBean = new CsvToBeanBuilder(reader)
                    .withSkipLines(1)
                    .withSeparator(';')
                    .withType(Entry.class)
                    //.withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<Entry> csvEntryIterator = csvToBean.iterator();

            while (csvEntryIterator.hasNext()) {
                Entry entry = csvEntryIterator.next();
                this.stack.addEntry(entry);
            }
        }
    }

    private void writeCSVFile() throws IOException {

        try (
                Writer writer = Files.newBufferedWriter(Paths.get(this.folder.getAbsolutePath() + '/' + RESULT_FILE));

                CSVWriter csvWriter = new CSVWriter(writer,
                        ';',
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            String[] headerRecord = {"Prefix", "Title", "ROC-area", "ROC-error", "Title", "ROC-area", "ROC-error", "nTrs", "nVs", "time_trs", "time_vs", "C", "nSv", "nSv+", "nSv-"};
            csvWriter.writeNext(headerRecord);

            for (Entry entry : this.stack.getEntries()) {
                csvWriter.writeNext(entry.getCsvResources());
            }
        }
    }

    private void writeCSVSortedFile() throws IOException {

        try (
                Writer writer = Files.newBufferedWriter(Paths.get(this.folder.getAbsolutePath() + '/' + RESULT_FILE_SORTED));

                CSVWriter csvWriter = new CSVWriter(writer,
                        ';',
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            String[] headerRecord = {"Prefix", "Title", "ROC-area", "ROC-error", "Title", "ROC-area", "ROC-error", "nTrs", "nVs", "time_trs", "time_vs", "C", "nSv", "nSv+", "nSv-"};
            csvWriter.writeNext(headerRecord);

            for (Entry entry : this.stack.getSortedEntries()) {
                csvWriter.writeNext(entry.getCsvResources());
            }
        }
    }

    private String getExtension(File file) {
        String filename = "";
        String extension = "";
        try {
            filename = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = filename.lastIndexOf('.');
        if (i >= 0) {
            extension = filename.substring(i+1);
        }
        return extension;
    }



}
