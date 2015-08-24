package org.kayura.bpm.models;

/**
 * 0 设计；1 发布；2 作废；
 */
public abstract class DefineStatus {
    public final static Integer Design = 0;
    public final static Integer Release = 1;
    public final static Integer Obsolete = 2;
}