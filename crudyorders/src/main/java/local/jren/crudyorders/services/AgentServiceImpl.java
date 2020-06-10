package local.jren.crudyorders.services;

import local.jren.crudyorders.models.Agent;
import local.jren.crudyorders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepository agentRepository;

    @Override
    public Agent findAgentById(long id) {
        return agentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Agent "+id+" Not Found"));
    }
}
