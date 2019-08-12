package com.casper.ProjectCalculator.Calculator;

import com.casper.ProjectCalculator.Project.CutPlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Calculator {
    List<Double> listOfMeasurements;
    Double lengthOfSingleStockBoardInInches;
    Double goalNumberOfBoards;

    ExecutorService executorService;
    CutPlan bestCutPlan;

    public Calculator(List<Double> listOfMeasurements, Double lengthOfSingleStockBoardInInches) throws ExecutionException, InterruptedException {
        this.listOfMeasurements = listOfMeasurements;
        this.lengthOfSingleStockBoardInInches = lengthOfSingleStockBoardInInches;
        determineGoalNumberOfBoards();
        determineBestCutPlan();
    }

    public CutPlan getBestCutPlan() {
        return bestCutPlan;
    }

    public void setBestCutPlan(CutPlan bestCutPlan) {
        this.bestCutPlan = bestCutPlan;
    }


    public void determineGoalNumberOfBoards() {
        Double goalNumberOfBoards = getSumOfAllMeasurements() / this.lengthOfSingleStockBoardInInches;
        this.goalNumberOfBoards = goalNumberOfBoards;
    }

    public Double getSumOfAllMeasurements() {
        Double sumOfAllMeasurements = 0.0;
        for (Double measurement : this.listOfMeasurements) {
            sumOfAllMeasurements += measurement;
        }
        return sumOfAllMeasurements;
    }

    public void determineIfCurrentCutPlanIsBetterThanCurrentBestCutPlan(CutPlan cutPlan) {
        if (cutPlan.getBoardList().size() < bestCutPlan.getBoardList().size()) {
            setBestCutPlan(cutPlan);
        }
    }

    public void determineBestCutPlan() throws InterruptedException, ExecutionException {
        List<Callable<CutPlan>> permutationThreads = createPermutationThreads();
        executorService = Executors.newFixedThreadPool(permutationThreads.size());
        List<Future<CutPlan>> bestCutPlanFromEachThreadAsListOfFutures = executorService.invokeAll(permutationThreads);
        List<CutPlan> bestCutPlans = new ArrayList<>();
        for (Future<CutPlan> future : bestCutPlanFromEachThreadAsListOfFutures) {
            determineIfCurrentCutPlanIsBetterThanCurrentBestCutPlan(future.get());
        }
        shutdownExecutor();
    }

    public List<Callable<CutPlan>> createPermutationThreads() {
        List<Callable<CutPlan>> callables = new ArrayList<>();
        List<List<Double>> startingPermutations = getAllStartingPermutations(listOfMeasurements);
        for (List<Double> permutation : startingPermutations) {
            Callable<CutPlan> callableForBestArrangementFromCurrentPermutation = () -> {
                return new CutPlan(permutation, lengthOfSingleStockBoardInInches, goalNumberOfBoards);
            };
            callables.add(callableForBestArrangementFromCurrentPermutation);
        }
        return callables;
    }


    public void shutdownExecutor() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public List<List<Double>> getAllStartingPermutations(List<Double> listOfMeasurements) {
        List<List<Double>> startingPermutations = new ArrayList<>();
        for (int k = 0; k < listOfMeasurements.size(); k++) {
            startingPermutations.add(getNextPermutation(listOfMeasurements));
        }
        return startingPermutations;
    }

    public List<Double> getNextPermutation(List<Double> list) {
        for (int j = 0; j < list.size() - 1; j++) {
            Double a = list.get(list.size() - 1);
            int i;
            for (i = list.size() - 1; i > 0; i--) {
                list.set((i), list.get(i - 1));
            }
            list.set(i, a);
        }
        List<Double> copyOfList = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            copyOfList.set(i, list.get(i));
        }
        return copyOfList;
    }

    public static <T extends Comparable<T>> void checkEachPermutationInThreadForBestPermutation(T[] elements, char delimiter) {
        Arrays.sort(elements);
        boolean hasNext = true;
        while(hasNext) {
//            printArray(elements, delimiter);
            int k = 0, l = 0;
            hasNext = false;
            for (int i = elements.length - 1; i > 0; i--) {
                if (elements[i].compareTo(elements[i - 1]) > 0) {
                    k = i - 1;
                    hasNext = true;
                    break;
                }
            }
            for (int i = elements.length - 1; i > k; i--) {
                if (elements[i].compareTo(elements[k]) > 0) {
                    l = i;
                    break;
                }
            }
            swap(elements, k, l);
            Collections.reverse(Arrays.asList(elements).subList(k + 1, elements.length));
        }
    }

    private static <T> void swap(T[] elements, int a, int b) {
        T tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
    }
}
