package local.jren.crudyorders.controllers;

import local.jren.crudyorders.models.Agent;
import local.jren.crudyorders.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agents")
public class AgentController {
    @Autowired
    private AgentService agentService;

    // GET http://localhost:2019/agents/agent/9
    @GetMapping(value = "/agent/{id}", produces = {"application/json"})
    public ResponseEntity<?> findAgentById(@PathVariable long id) {
        Agent agent = agentService.findAgentById(id);
        return new ResponseEntity<>(agent, HttpStatus.OK);
    }

    // DELETE http://localhost:2019/agents/unassigned/8
    @DeleteMapping(value = "/unassigned/{agentcode}")
    public ResponseEntity<?> deleteUnassignedAgent(@PathVariable long agentcode) {
        agentService.deleteUnassignedAgent(agentcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
