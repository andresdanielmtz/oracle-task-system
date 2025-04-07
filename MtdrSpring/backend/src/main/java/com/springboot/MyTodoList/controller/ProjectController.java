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

import com.springboot.MyTodoList.model.Project;
import com.springboot.MyTodoList.service.ProjectService;

/**
 * ProjectController class
 * CRUD operations for Project entity
 */

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) {
        try {
            ResponseEntity<Project> responseEntity = projectService.getItemById(id);
            return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create new project endpoint.
    @PostMapping
    public ResponseEntity<Project> addNewProject(@RequestBody Project new_project)
            throws Exception {
        Project project = projectService.addProject(new_project);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + project.getID_Project());
        responseHeaders.set("Access-Control-Expose-Headers", "location");

        return ResponseEntity.ok()
                .headers(responseHeaders).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@RequestBody Project project, @PathVariable int id) {
        try {
            Project project1 = projectService.updateProject(id, project);
            System.out.println(project1.toString());
            return new ResponseEntity<>(project1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable int id) {
        try {
            projectService.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}