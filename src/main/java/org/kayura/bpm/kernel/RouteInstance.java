package org.kayura.bpm.kernel;

import java.util.List;

import org.kayura.bpm.models.Route;
import org.kayura.bpm.models.Transition;
import org.kayura.bpm.models.WorkflowProcess;

public class RouteInstance {

	private Route route;
	private WorkflowProcess process;

	public RouteInstance(Route route) {
		this.route = route;
		this.process = (WorkflowProcess) route.getParent();
	}

	public List<ActivityInstance> getNextActivities() {

		List<Transition> toTransitions = route.getToTransitions();

		
		
		return null;
	}

}
