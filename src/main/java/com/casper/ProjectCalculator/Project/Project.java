package com.casper.ProjectCalculator.Project;

public class Project {
    String name;
    LumberPlan lumberPlan;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LumberPlan getLumberPlan() {
        return lumberPlan;
    }

    public void setLumberPlan(LumberPlan lumberPlan) {
        this.lumberPlan = lumberPlan;
    }
}
