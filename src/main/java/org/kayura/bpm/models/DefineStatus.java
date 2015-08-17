package org.kayura.bpm.models;

/**
 * 0 设计；1 发布；2 作废；
 */
public abstract class DefineStatus {
    public final static Integer design = 0;
    public final static Integer release = 1;
    public final static Integer obsolete = 2;
}