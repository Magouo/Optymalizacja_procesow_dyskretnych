package org.example;

import java.util.Comparator;

class ItemComparator implements Comparator<Task> {
    @Override
    public int compare(Task a, Task b) {
        return Integer.compare(a.totalTime, b.totalTime) * -1;
    }
}