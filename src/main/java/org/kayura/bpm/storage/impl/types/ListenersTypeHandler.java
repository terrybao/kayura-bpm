package org.kayura.bpm.storage.impl.types;

import org.kayura.bpm.models.EventListeners;
import org.kayura.mybatis.type.JsonTypeHandler;

public class ListenersTypeHandler extends JsonTypeHandler<EventListeners> {
    
    public ListenersTypeHandler() {
	super(EventListeners.class);
    }
    
}
