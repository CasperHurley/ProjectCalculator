package com.casper.ProjectCalculator.controllers;

import com.casper.ProjectCalculator.Project.InputPayload;
import com.casper.ProjectCalculator.Project.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path="/project")
public class ProjectController {

    @RequestMapping(path="/create", method= RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> createProject(@RequestBody String json) throws IOException, ExecutionException, InterruptedException {
        /*
            - Request body:
                - Input Payload {
                    name: null,
                    listOfMeasurements: [#, #, #, #, #.##, #, #.##, #.###, #.##],
                    stockLengthsToCheck: [#, #, ##]
                }
            - Response body:
                - Project {
                    name,
                    listOfMeasurements,
                    bestCutPlansForDifferentStockLengths: [
                        cutPlan1: {
                            lengthOfStockBoardInInches,
                            totalCost,
                            boardList: [
                                board1: {
                                    length,
                                    width,
                                    thickness,
                                    measurementsInBoard: [#, #, #, #, #.##]
                                },
                                board2: {
                                    length,
                                    width,
                                    thickness,
                                    measurementsInBoard: [#, #.##, #.###]
                                },
                                board3: {
                                    length,
                                    width,
                                    thickness,
                                    measurementsInBoard: [#.##]
                                }
                            ]
                        },
                        cutPlan2: {
                            lengthOfStockBoardInInches,
                            totalCost,
                            boardList: [
                                board1: {
                                    length,
                                    width,
                                    thickness,
                                    measurementsInBoard: [#, #, #, #, #.##]
                                },
                                board1: {
                                    length,
                                    width,
                                    thickness,
                                    measurementsInBoard: [#, #.##, #.###, #.##]
                                }
                            ]
                        },
                        cutPlan3: {
                            lengthOfStockBoardInInches,
                            totalCost,
                            boardList: [
                                board1: {
                                    length,
                                    width,
                                    thickness,
                                    measurementsInBoard: [#, #, #, #, #.##, #, #.##, #.###, #.##]
                                }
                            ]
                        }
                    ]
                }
        */
        InputPayload inputPayload = new InputPayload();
        ObjectMapper mapper = new ObjectMapper();
        inputPayload = mapper.readValue(json, InputPayload.class);
        Project project = new Project(inputPayload);
        return new ResponseEntity<String>(mapper.writeValueAsString(project), HttpStatus.OK);
    }
}
