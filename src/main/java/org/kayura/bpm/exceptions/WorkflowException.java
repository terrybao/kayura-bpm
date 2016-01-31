/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.exceptions;

public class WorkflowException extends RuntimeException {
    
    private static final long serialVersionUID = 6887709273388225788L;
    
    public WorkflowException() {
	super();
    }
    
    public WorkflowException(String message) {
	super(message);
    }
    
    public WorkflowException(String message, Throwable cause) {
	super(message, cause);
    }
    
    public WorkflowException(Throwable cause) {
	super(cause);
    }
    
}
