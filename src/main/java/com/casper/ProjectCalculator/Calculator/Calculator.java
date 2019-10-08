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

    ExecutorService executorService;
    CutPlan bestCutPlan;

    public Calculator(List<Double> listOfMeasurements, Double lengthOfSingleStockBoardInInches) throws ExecutionException, InterruptedException {
        this.listOfMeasurements = listOfMeasurements;
        this.lengthOfSingleStockBoardInInches = lengthOfSingleStockBoardInInches;
        determineBestCutPlan();
    }

    public void setListOfMeasurements(List<Double> listOfMeasurements) {
        this.listOfMeasurements = listOfMeasurements;
    }

    public CutPlan getBestCutPlan() {
        return bestCutPlan;
    }

    public void setBestCutPlan(CutPlan bestCutPlan) {
        this.bestCutPlan = bestCutPlan;
    }

    public Double getSumOfAllMeasurements() {
        Double sumOfAllMeasurements = 0.0;
        for (Double measurement : this.listOfMeasurements) {
            sumOfAllMeasurements += measurement;
        }
        return sumOfAllMeasurements;
    }

    public void determineIfCurrentCutPlanIsBetterThanCurrentBestCutPlan(CutPlan cutPlan) {
        if (null != bestCutPlan && (cutPlan.getBoardList().size() < bestCutPlan.getBoardList().size())) {
            setBestCutPlan(cutPlan);
        } else if (null == bestCutPlan) {
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
        for (Future<CutPlan> future : bestCutPlanFromEachThreadAsListOfFutures) {
            try {
                CutPlan cutPlan = future.get();
                determineIfCurrentCutPlanIsBetterThanCurrentBestCutPlan(cutPlan);
            } catch(Exception ex) {
                throw ex;
            }
        }
        shutdownExecutor();
    }

    public List<Callable<CutPlan>> createPermutationThreads() {
        List<Callable<CutPlan>> callables = new ArrayList<>();
        List<List<Double>> startingPermutations = getAllStartingPermutations(listOfMeasurements);
        for (List<Double> startingPermutation : startingPermutations) {
            Callable<CutPlan> callableForBestArrangementFromCurrentPermutationRange = () -> {
                // run through all permutations until the start of the next one
                Integer currentIndex = startingPermutations.indexOf(startingPermutation);
                Integer nextIndex = currentIndex + 1;
                CutPlan bestCutPlanFromCurrentStartingPermutationThread;
                if (nextIndex < startingPermutations.size()) { // if not the last starting index
                    bestCutPlanFromCurrentStartingPermutationThread = checkEachPermutationInThreadForBestCutPlan(startingPermutation, startingPermutations.get(currentIndex + 1));
                } else { // if the last starting index
                    bestCutPlanFromCurrentStartingPermutationThread = checkEachPermutationInThreadForBestCutPlan(startingPermutation, startingPermutations.get(0));
                }
                return bestCutPlanFromCurrentStartingPermutationThread;
            };
            callables.add(callableForBestArrangementFromCurrentPermutationRange);
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

    public CutPlan checkEachPermutationInThreadForBestCutPlan(List<Double> startingPermutation, List<Double> nextStartingPermutation) {
        List<Double> currentPermutation = startingPermutation;
        Collections.sort(currentPermutation);
        CutPlan bestCutPlanThisThread = new CutPlan(currentPermutation, this.lengthOfSingleStockBoardInInches);

        boolean hasNext = true;
        while(hasNext && (!currentPermutation.equals(nextStartingPermutation))) {

            CutPlan currentPlan = new CutPlan(currentPermutation, this.lengthOfSingleStockBoardInInches);
            if (tellMeIfCurrentCutPlanIsBetterThanCurrentBestCutPlan(currentPlan, bestCutPlanThisThread)) {
                bestCutPlanThisThread = currentPlan;
            }

            int k = 0, l = 0;
            hasNext = false;
            for (int i = currentPermutation.size() - 1; i > 0; i--) {
                if (currentPermutation.get(i).compareTo(currentPermutation.get(i - 1)) > 0) {
                    k = i - 1;
                    hasNext = true;
                    break;
                }
            }
            for (int i = currentPermutation.size() - 1; i > k; i--) {
                if (currentPermutation.get(i).compareTo(currentPermutation.get(k)) > 0) {
                    l = i;
                    break;
                }
            }
            swap(currentPermutation, k, l);
            Collections.reverse(Arrays.asList(currentPermutation).subList(k + 1, currentPermutation.size()));
        }
        return bestCutPlanThisThread;
    }

    private void swap(List<Double> currentPermutation, int a, int b) {
        Double tmp = currentPermutation.get(a);
        currentPermutation.set(a, currentPermutation.get(b));
        currentPermutation.set(b, tmp);
    }
}
