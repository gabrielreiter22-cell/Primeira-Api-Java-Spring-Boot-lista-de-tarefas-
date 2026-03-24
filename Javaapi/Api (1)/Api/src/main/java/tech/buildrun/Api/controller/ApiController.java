package tech.buildrun.Api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
    @RestController
    //filtro tem que ser o id não tasks num geral, pra ser mais específico ao deletar algo
    //fazer um put pra terminar os métodos http

    @RequestMapping (path = "/IdAndTask")


    public class ApiController {

        public static class Task {
            private Integer id;
            private String tarefa;

            public Integer getId() { return id; }
            public void setId(Integer id) { this.id = id; }

            public String getTarefa()
            {
                return tarefa;
            }
            public void setTarefa(String tarefa)
            {
                this.tarefa = tarefa;
            }
        }

        Map<Integer, Task> idAndTask = new HashMap<>();

        @PostMapping
        public ResponseEntity<String> createTask(@RequestBody  Task task ) {
            if (idAndTask.containsKey(task.getId())){
                return ResponseEntity.ok("id de tarefa ja existe use outro id");
            }
            else {
                idAndTask.put(task.getId(), task);
                return ResponseEntity.ok("tarefa criada");
            }
        }

        @GetMapping
        public ResponseEntity<Map<Integer, Task>> getTarefa() {
            return ResponseEntity.ok(idAndTask);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteTask(@PathVariable Integer id) {

            if (!idAndTask.containsKey(id)) {
                return ResponseEntity.status(404).body("tarefa não encontrada");
            }

            idAndTask.remove(id);
            return ResponseEntity.ok("tarefa removida");
        }

    }