package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class PalmerSolver {

    private final Problem problem;

    private static class PalmerTask {
        Task task;
        double slopeIndex;

        PalmerTask(Task task, double slopeIndex) {
            this.task = task;
            this.slopeIndex = slopeIndex;
        }
    }

    public PalmerSolver(Problem problem) {
        this.problem = problem;
    }

    public Solution solve() {
        System.out.println("Rozpoczynanie rozwiązywania heurystyką Palmera...");
        List<PalmerTask> palmerTasks = new ArrayList<>();
        if (problem.tasks.isEmpty()) {
            return new Solution(new LinkedList<>(), 0);
        }
        int numMachines = problem.tasks.get(0).getNumMachines();

        for (Task task : problem.tasks) {
            double index = 0;
            for (int j = 0; j < numMachines; j++) {
                index += (double) (numMachines - 2 * j - 1) * task.tasksTime.get(j);
            }
            palmerTasks.add(new PalmerTask(task, index));
        }

        palmerTasks.sort(
                Comparator.comparingDouble((PalmerTask pt) -> pt.slopeIndex).reversed()
        );

        LinkedList<Task> finalOrder = new LinkedList<>();
        for (PalmerTask pt : palmerTasks) {
            finalOrder.add(pt.task);
        }

        int makespan = problem.calculateMakespan(finalOrder);
        System.out.println("Zakończono wykonywanie heurystyki Palmera.");
        return new Solution(finalOrder, makespan);
    }
}