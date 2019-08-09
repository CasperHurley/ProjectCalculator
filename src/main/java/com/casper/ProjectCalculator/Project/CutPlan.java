package com.casper.ProjectCalculator.Project;

import java.util.ArrayList;
import java.util.List;

public class CutPlan {
    List<Board> boardList;

    public CutPlan(List<Double> measurements) {
        boardList = new ArrayList<>();
    }

    public void addBoardToList(Board board) {
        this.boardList.add(board);
    }
}
