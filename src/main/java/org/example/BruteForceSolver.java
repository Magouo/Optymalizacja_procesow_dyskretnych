package org.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class BruteForceSolver {

    private final Problem problem;
    private Solution bestSolution;

    public BruteForceSolver(Problem problem) {
        this.problem = problem;
        this.bestSolution = new Solution(null, Integer.MAX_VALUE);
    }

    public Solution solve() {
        System.out.println(
                "Rozpoczynanie rozwiązywania metodą przeglądu zupełnego..."
        );
        List<Task> tasks = new ArrayList<>(problem.tasks);
        permute(tasks, 0);
        System.out.println(
                "Zakończono wykonywanie algorytmu przeglądu zupełnego."
        );
        return bestSolution;
    }

    private void permute(List<Task> arr, int k) {
        for (int i = k; i < arr.size(); i++) {
            Collections.swap(arr, i, k);
            permute(arr, k + 1);
            Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            LinkedList<Task> currentPermutation = new LinkedList<>(arr);
            int currentMakespan = problem.calculateMakespan(currentPermutation);
            if (currentMakespan < bestSolution.makespan) {
                bestSolution = new Solution(currentPermutation, currentMakespan);
            }
        }
    }
}