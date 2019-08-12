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
//    Double goalNumberOfBoards;

    ExecutorService executorService;
    CutPlan bestCutPlan;

    public Calculator(List<Double> listOfMeasurements, Double lengthOfSingleStockBoardInInches) throws ExecutionException, InterruptedException {
        this.listOfMeasurements = listOfMeasurements;
        this.lengthOfSingleStockBoardInInches = lengthOfSingleStockBoardInInches;
//        determineGoalNumberOfBoards();
        determineBestCutPlan();
    }

    public CutPlan getBestCutPlan() {
        return bestCutPlan;
    }

    public void setBestCutPlan(CutPlan bestCutPlan) {
        this.bestCutPlan = bestCutPlan;
    }


//    public void determineGoalNumberOfBoards() {
//        Double goalNumberOfBoards = getSumOfAllMeasurements() / this.lengthOfSingleStockBoardInInches;
//        this.goalNumberOfBoards = goalNumberOfBoards;
//    }

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

    public boolean tellMeIfCurrentCutPlanIsBetterThanCurrentBestCutPlan(CutPlan currentPlan, CutPlan currentBest) {
        return currentPlan.getBoardList().size() < currentBest.getBoardList().size();
    }

    public void determineBestCutPlan() throws InterruptedException, ExecutionException {
        List<Callable<CutPlan>> permutationThreads = createPermutationThreads();
        executorService = Executors.newFixedThreadPool(permutationThreads.size());
        List<Future<CutPlan>> bestCutPlanFromEachThreadAsListOfFutures = executorService.invokeAll(permutationThreads);
        bestCutPlan = bestCutPlanFromEachThreadAsListOfFutures.get(0).get();
        for (Future<CutPlan> future : bestCutPlanFromEachThreadAsListOfFutures) {
            determineIfCurrentCutPlanIsBetterThanCurrentBestCutPlan(future.get());
        }
        shutdownExecutor();
    }

    public List<Callable<CutPlan>> createPermutationThreads() {
        List<Callable<CutPlan>> callables = new ArrayList<>();
        List<List<Double>> startingPermutations = getAllStartingPermutations(listOfMeasurements);
        // need to run for every permutation, create a thread for each starting permutation that calculates cutPlan, checks if it is better than the current best cutPlan for that Thread, if it is then make it the new best one, and the thread finally ends when it equals the start of the next thread
        for (List<Double> startingPermutation : startingPermutations) {
            Callable<CutPlan> callableForBestArrangementFromCurrentPermutation = () -> {
                // run through all permutations until the start of the next one
                Integer currentIndex = startingPermutations.indexOf(startingPermutation);
                Integer nextIndex = currentIndex + 1;
                CutPlan bestCutPlanFromCurrentStartingPermutationThread;
                if (nextIndex < startingPermutations.size()) {
                    bestCutPlanFromCurrentStartingPermutationThread = getBestCutPlanFromCurrentStartingPermutationThread(startingPermutation, startingPermutations.get(currentIndex + 1));
                } else {
                    bestCutPlanFromCurrentStartingPermutationThread = getBestCutPlanFromCurrentStartingPermutationThread(startingPermutation, startingPermutations.get(0));
                }
                return bestCutPlanFromCurrentStartingPermutationThread;
            };
            callables.add(callableForBestArrangementFromCurrentPermutation);
        }
        return callables;
    }

    public CutPlan getBestCutPlanFromCurrentStartingPermutationThread(List<Double> startingPermutation, List<Double> nextStartingPermutation) {
        List<Double> currentPermutation = startingPermutation;
        CutPlan currentBestPlan = new CutPlan(currentPermutation, this.lengthOfSingleStockBoardInInches);

        checkEachPermutationInThreadForBestPermutation();

//        while (!currentPermutation.equals(nextStartingPermutation)) {
//            CutPlan currentPlan = new CutPlan(currentPermutation, this.lengthOfSingleStockBoardInInches);
//            if (tellMeIfCurrentCutPlanIsBetterThanCurrentBestCutPlan(currentPlan, currentBestPlan)) {
//                currentBestPlan = currentPlan;
//            }
//            currentPermutation =
//        }
        return currentBestPlan;
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

    public <T extends Comparable<T>> void checkEachPermutationInThreadForBestPermutation(T[] currentPermutation) {
        // starts with startingPermutation, runs calculation, shifts, runs calculation, etc, that run func needs to keep updating the bestCutPlanForThisThread
        Arrays.sort(currentPermutation);
        CutPlan bestCutPlanThisThread = new CutPlan(convertArrayToList((Double[])currentPermutation), this.lengthOfSingleStockBoardInInches);

        boolean hasNext = true;
        while(hasNext) {
            // printArray(currentPermutation); This is where the magic should happen
            int k = 0, l = 0;
            hasNext = false;
            for (int i = currentPermutation.length - 1; i > 0; i--) {
                if (currentPermutation[i].compareTo(currentPermutation[i - 1]) > 0) {
                    k = i - 1;
                    hasNext = true;
                    break;
                }
            }
            for (int i = currentPermutation.length - 1; i > k; i--) {
                if (currentPermutation[i].compareTo(currentPermutation[k]) > 0) {
                    l = i;
                    break;
                }
            }
            swap(currentPermutation, k, l);
            Collections.reverse(Arrays.asList(currentPermutation).subList(k + 1, currentPermutation.length));
        }
    }

    private static <T> void swap(T[] currentPermutation, int a, int b) {
        T tmp = currentPermutation[a];
        currentPermutation[a] = currentPermutation[b];
        currentPermutation[b] = tmp;
    }

    public static List<Double> convertArrayToList(Double array[]) {
        List<Double> list = new ArrayList<>();
        for (Double t : array) {
            list.add(t);
        }
        return list;
    }
}
