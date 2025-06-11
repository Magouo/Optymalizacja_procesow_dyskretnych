package org.example;

import java.util.*;

public class Problem {
    public LinkedList<Task> tasks;

    Problem(int jobs, int machines, int lowestTime, int highestTime) {
        Random rand = new Random();
        tasks = new LinkedList<Task>();
        for (int i = 0; i < jobs; i++) {
            ArrayList<Integer> tasksTimes = new ArrayList<>();
            for (int j = 0; j < machines; j++) {
                tasksTimes.add(rand.nextInt(highestTime - lowestTime) + lowestTime);
            }
            tasks.add(new Task(tasksTimes, i));
        }
    }

    int calculateMakespan(LinkedList<Task> tList) {
        if (tList.isEmpty()) {
            return 0;
        }
        int numMachines = tList.get(0).getNumMachines();
        int numTasks = tList.size();

        int[][] completionTimes = new int[numTasks][numMachines];

        completionTimes[0][0] = tList.get(0).tasksTime.get(0);
        for (int j = 1; j < numMachines; j++) {
            completionTimes[0][j] =
                    completionTimes[0][j - 1] + tList.get(0).tasksTime.get(j);
        }

        for (int i = 1; i < numTasks; i++) {
            completionTimes[i][0] =
                    completionTimes[i - 1][0] + tList.get(i).tasksTime.get(0);

            for (int j = 1; j < numMachines; j++) {
                completionTimes[i][j] =
                        Math.max(completionTimes[i - 1][j], completionTimes[i][j - 1]) +
                                tList.get(i).tasksTime.get(j);
            }
        }
        return completionTimes[numTasks - 1][numMachines - 1];
    }

    Solution solve() {
        System.out.println("Rozpoczynanie rozwiązywania algorytmem NEH...");
        Collections.sort(this.tasks, new ItemComparator());

        LinkedList<Task> bestOrder = new LinkedList<>();
        if (!tasks.isEmpty()) {
            bestOrder.add(tasks.get(0));
        }

        for (int i = 1; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            int bestMakespan = Integer.MAX_VALUE;
            int bestPosition = -1;

            for (int j = 0; j <= bestOrder.size(); j++) {
                LinkedList<Task> tempOrder = new LinkedList<>(bestOrder);
                tempOrder.add(j, currentTask);
                int currentMakespan = calculateMakespan(tempOrder);

                if (currentMakespan < bestMakespan) {
                    bestMakespan = currentMakespan;
                    bestPosition = j;
                }
            }
            bestOrder.add(bestPosition, currentTask);
        }

        Solution sol = new Solution(bestOrder, calculateMakespan(bestOrder));
        System.out.println("Zakończono wykonywanie algorytmu NEH.");
        return sol;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append(tasks.get(i).toString()).append("\n");
        }
        return output.toString();
    }
}