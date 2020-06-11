package local.jren.crudyorders.services;

import local.jren.crudyorders.models.Agent;
import local.jren.crudyorders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepository agentRepository;

    @Override
    public Agent findAgentById(long id) {
        return agentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Agent "+id+" Not Found"));
    }

    @Transactional
    @Override
    public void deleteUnassignedAgent(long id) {
        if (agentRepository.findById(id).isPresent()) {
            Agent agent = agentRepository.findById(id).get();
            if (agent.getCustomers().size() > 0 ) {
                throw new EntityNotFoundException("Found A Customer For Agent "+id);
            } else {
                agentRepository.deleteById(id);
            }
        } else {
            throw new EntityNotFoundException("Agent "+id+" Not Found");
        }
    }
}
