/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeofword;

import java.util.TreeSet;

/**
 *
 * @author Nhat Ha
 */
public class Word implements Comparable<Word> {

    protected String info;
    protected Word left = null;
    protected Word right = null;
    protected TreeSet lines = null;

    public Word(String info) {
        this.info = info;
    }

    public Word(String info, TreeSet lines) {
        this.info = info;
        this.lines = lines;
    }

    public Word(String info, Word left, Word right, TreeSet lines) {
        this.info = info;
        this.left = left;
        this.right = right;
        this.lines = lines;
    }

    @Override
    public int compareTo(Word o) {
        return this.info.compareToIgnoreCase(o.info);
    }

}
