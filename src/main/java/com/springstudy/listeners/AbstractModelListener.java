package com.springstudy.listeners;

public class AbstractModelListener<T> {

    public void beforePersist(T entity) {
        // Default logic before persist
    }

    public void afterPersist(T entity) {
        // Default logic before persist
    }

    public void preUpdate(T entity) {
        // Default logic before update
    }

    public void postUpdate(T entity) {
        // Default logic after update

    }

    public void preRemove(T entity) {
        // Default or common preRemove logic
    }

    public void postRemove(T entity) {
        // Default or common postRemove logic
    }

    public void postLoad(T entity) {
        // Default or common postLoad logic
    }
}
