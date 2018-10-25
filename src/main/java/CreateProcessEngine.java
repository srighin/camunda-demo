import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateProcessEngine {

    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Deployment deployment = repositoryService.createDeployment().addClasspathResource("process.bpmn").deploy();

        System.out.println("deployment Source: "+deployment.getSource());
//        query for all deployed process definitions using the Java API
//        and the ProcessDefinitionQuery made available through the RepositoryService
//        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
//                .processDefinitionKey("process")
//                .orderByProcessDefinitionVersion()
//                .asc()
//                .list();

        //System.out.println(processDefinitions.get(0));

        //You may optionally pass in a couple of variables:
        Map<String, Object> variables = new HashMap<String,Object>();
        variables.put("creditor", "Nice Pizza Inc.");
        ProcessInstance instance = runtimeService.startProcessInstanceById("process", variables);
        System.out.println(instance.getProcessDefinitionId());
    }
}
