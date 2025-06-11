package org.example;

import java.util.ArrayList;

class Task {
    int taskId;
    ArrayList<Integer> tasksTime;
    int totalTime;
    int numMachines;

    int getNumMachines() {
        return numMachines;
    }

    Task(ArrayList<Integer> tasksT, int taskId) {
        this.tasksTime = tasksT;
        this.totalTime = 0;
        for (int task : tasksT) {
            this.totalTime += task;
        }
        this.taskId = taskId;
        this.numMachines = tasksT.size();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Zadanie " + (taskId + 1) + ":\t");
        for (int i = 0; i < this.tasksTime.size(); i++) {
            output
                    .append("M")
                    .append(i + 1)
                    .append(": ")
                    .append(this.tasksTime.get(i))
                    .append("\t");
        }
        return output.toString();
    }
}