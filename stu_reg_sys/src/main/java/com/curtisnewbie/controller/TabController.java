package com.curtisnewbie.controller;

public interface TabController<T> {

    /**
     * Display content of
     */
    public void displayContentOf(int id);

    /**
     * Display content of
     */
    public void displayContentOf(T t);

    /**
     * Clear all content
     */
    public void clearContent();
}