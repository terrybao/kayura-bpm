package org.kayura.bpm.kernel;

/*
 * 0 正常、1 退回、2 重置、3 回滚.
 */
public enum HandleTypes {
    normal, back, start, special
}
