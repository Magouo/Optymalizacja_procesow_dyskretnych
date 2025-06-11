package org.example;

import java.util.LinkedList;

class Solution {
    public LinkedList<Task> tasks;
    int makespan;

    Solution() {
        tasks = new LinkedList<Task>();
    }

    Solution(LinkedList<Task> tasks, int makespan) {
        this.tasks = tasks;
        this.makespan = makespan;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        if (tasks != null) {
            for (int i = 0; i < this.tasks.size(); i++) {
                output.append(tasks.get(i).toString()).append("\n");
            }
        }
        output
                .append("Całkowity czas wykonania (makespan): ")
                .append(this.makespan)
                .append("\n");
        return output.toString();
    }
}