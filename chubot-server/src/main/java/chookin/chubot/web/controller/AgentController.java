package chookin.chubot.web.controller;

import chookin.chubot.server.ChubotServer;
import chookin.chubot.web.model.Agent;
import chookin.chubot.web.model.JobDetail;
import com.jfinal.core.Controller;

import java.util.Collection;

/**
 * Created by zhuyin on 8/19/15.
 */
public class AgentController extends Controller {

    public void index() {
        render("/agents.html");
    }

    public void agents(){
        setAttr("items", ChubotServer.instance().handler().agents());
        renderJson();
    }

    public void agent() throws InterruptedException {
        int agentId = getParaToInt("id");
        Agent agent = ChubotServer.instance().handler().agent(agentId);
        setAttr("agent", agent);
        Collection<JobDetail> jobs = ChubotServer.instance().handler().getJobs(agentId, getPara("status"));
        setAttr("items", jobs);
        render("/agent.html");
    }

    public void history(){
        Collection<Agent> history = Agent.DAO.find("select * from agent");
        setAttr("items", history);
        renderJson();
    }
}
