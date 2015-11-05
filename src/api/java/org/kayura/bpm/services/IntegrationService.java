package org.kayura.bpm.services;

import org.kayura.bpm.types.StartArgs;
import org.kayura.bpm.types.StartResult;

public interface IntegrationService {
	
	/**
	 * 
	 * @param args
	 * @return
	 */
	StartResult startup(StartArgs args);
}
