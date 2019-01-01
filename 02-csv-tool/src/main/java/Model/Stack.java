package Model;

import java.util.*;

public class Stack {
    List<Entry> entries = new ArrayList<>();

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    public List<Entry> getSortedEntries() {
        List<Entry> sorted = this.entries;
        Collections.sort(sorted);

        return sorted;
    }

}
