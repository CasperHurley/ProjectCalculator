package com.casper.ProjectCalculator.controllers;

import com.casper.ProjectCalculator.Project.InputFromUI;
import com.casper.ProjectCalculator.Project.LumberPlan;
import com.casper.ProjectCalculator.Project.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path="/")
public class ProjectController {

    @RequestMapping(path="/create", method= RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> createProject(@RequestBody String json) throws IOException {
        InputFromUI inputFromUI = new InputFromUI();
        ObjectMapper mapper = new ObjectMapper();
        inputFromUI = mapper.readValue(json, InputFromUI.class);
        Project project = new Project(inputFromUI.getName());
        LumberPlan lumberPlan = new LumberPlan(inputFromUI.getListOfMeasurements());
        project.setLumberPlan(lumberPlan);
        return new ResponseEntity<String>(mapper.writeValueAsString(project), HttpStatus.OK);
    }
}
