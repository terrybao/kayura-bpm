/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.bpm.models;

/**
 * 0 设计；1 发布；2 作废；
 */
public abstract class DefineStatus {
	
	/**
	 * 设计状态 = 0
	 */
	public final static Integer Design = 0;
	
	/**
	 * 发布状态 = 1
	 */
    public final static Integer Release = 1;
    
    /**
     * 作废状态 = 2
     */
    public final static Integer Obsolete = 2;
}