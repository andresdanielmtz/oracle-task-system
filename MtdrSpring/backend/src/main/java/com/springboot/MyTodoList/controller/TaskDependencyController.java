package com.springboot.MyTodoList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.MyTodoList.model.TaskDependency;
import com.springboot.MyTodoList.service.TaskDependencyService;

@RestController
@RequestMapping("/taskDependency")
public class TaskDependencyController {
    @Autowired
    private TaskDependencyService taskDependencyService;

    @GetMapping
    public List<TaskDependency> getAllTaskDependencies() {
        return taskDependencyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDependency> getTaskDependencyById(@PathVariable int id) {
        try {
            ResponseEntity<TaskDependency> responseEntity = taskDependencyService.getItemById(id);
            return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TaskDependency> addNewTaskDependency(@RequestBody TaskDependency new_taskDependency) throws Exception {
        TaskDependency taskDependency = taskDependencyService.addTaskDependency(new_taskDependency);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + taskDependency.getID_Task_Parent());
        responseHeaders.set("Access-Control-Expose-Headers", "location");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(taskDependency); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDependency> updateTaskDependency(@RequestBody TaskDependency taskDependency, @PathVariable int id) {
        try {
            TaskDependency taskDependency1 = taskDependencyService.updateTaskDependency(id, taskDependency);
            System.out.println(taskDependency1.toString());
            return ResponseEntity.ok().body(taskDependency1);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDependency> deleteTaskDependency(@PathVariable int id) {
        try {
            taskDependencyService.deleteTaskDependency(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
