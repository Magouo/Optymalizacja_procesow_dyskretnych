package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int numJobs = 9;
        int[] machineCounts = { 3, 5, 8, 10 };
        int minTime = 10;
        int maxTime = 50;

        if (numJobs > 10) {
            System.out.println(
                    "BŁĄD: Liczba zadań > 10. Przegląd zupełny jest niewykonalny."
            );
            System.out.println(
                    "Zmniejsz liczbę zadań (numJobs) do 10 lub mniej."
            );
            return;
        }

        System.out.println(
                "--- URUCHAMIANIE ZESTAWU TESTÓW PORÓWNAWCZYCH ---"
        );
        System.out.println("Liczba zadań (n): " + numJobs);
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------"
        );
        // Nagłówek tabeli wyników
        System.out.printf(
                "%-8s | %-10s %-10s %-10s | %-10s %-10s %-12s | %-12s %-12s\n",
                "# Maszyn",
                "Cmax(P)",
                "Cmax(N)",
                "Cmax(Opt)",
                "T(P)[ms]",
                "T(N)[ms]",
                "T(BF)[ms]",
                "Błąd(P) [%]",
                "Błąd(N) [%]"
        );
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------"
        );

        for (int machineCount : machineCounts) {
            Problem problem = new Problem(numJobs, machineCount, minTime, maxTime);

            PalmerSolver palmerSolver = new PalmerSolver(problem);
            long startTimePalmer = System.nanoTime();
            Solution palmerSolution = palmerSolver.solve();
            long timePalmer = (System.nanoTime() - startTimePalmer) / 1_000_000;

            long startTimeNeh = System.nanoTime();
            Solution nehSolution = problem.solve();
            long timeNeh = (System.nanoTime() - startTimeNeh) / 1_000_000;

            BruteForceSolver bfSolver = new BruteForceSolver(problem);
            long startTimeBf = System.nanoTime();
            Solution bfSolution = bfSolver.solve();
            long timeBf = (System.nanoTime() - startTimeBf) / 1_000_000;

            double errorPalmer =
                    ((double) (palmerSolution.makespan - bfSolution.makespan) /
                            bfSolution.makespan) *
                            100;
            double errorNeh =
                    ((double) (nehSolution.makespan - bfSolution.makespan) /
                            bfSolution.makespan) *
                            100;

            System.out.printf(
                    "%-8d | %-10d %-10d %-10d | %-10d %-10d %-12d | %-12.2f %-12.2f\n",
                    machineCount,
                    palmerSolution.makespan,
                    nehSolution.makespan,
                    bfSolution.makespan,
                    timePalmer,
                    timeNeh,
                    timeBf,
                    errorPalmer,
                    errorNeh
            );
        }
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------"
        );
        System.out.println("Legenda: (P)-Palmer, (N)-NEH, (Opt/BF)-Optymalny/Brute-Force, T-Czas");
    }
}