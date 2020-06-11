package local.jren.crudyorders.services;

import local.jren.crudyorders.models.Agent;

public interface AgentService {
    Agent findAgentById(long id);
    void deleteUnassignedAgent(long id);
}
