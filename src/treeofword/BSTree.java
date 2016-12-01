/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeofword;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;

/**
 *
 * @author Nhat Ha
 */
public class BSTree {

  private Vector data = new Vector();
  private Word root = null;
  private int number = 0;
  private int total = 0;

  public boolean isEmpty() {
    return root == null;
  }

  public int getNumber() {
    return number;
  }

  public int getTotal() {
    return total;
  }

  public Word insertWord(String info, int lineNum) {
    TreeSet lines = new TreeSet();
    lines.add(lineNum);

    if (isEmpty()) {
      Word newWord = new Word(info, lines);
      root = newWord;
      number++;
      total++;
      return newWord;
    }

    Word point = root;
    Word prevWord = null;
    Word newWord = new Word(info, lines);
    while (point != null) {
      prevWord = point;
      if (newWord.compareTo(point) < 0) {
        point = point.left;
      } else if (newWord.compareTo(point) > 0) {
        point = point.right;
      } else {
        break;
      }
    }

    if (newWord.compareTo(prevWord) < 0) {
      prevWord.left = newWord;
      number++;
      total++;
    } else if (newWord.compareTo(prevWord) > 0) {
      prevWord.right = newWord;
      number++;
      total++;
    } else {
      prevWord.lines.add(lineNum);
      total++;
    }
    return newWord;
  }

  public StringBuilder build(String path) throws FileNotFoundException, IOException {
    FileReader fr = new FileReader(path);
    BufferedReader br = new BufferedReader(fr);
    String line;
    StringBuilder content = new StringBuilder();
    int lineNum = 0;
    while ((line = br.readLine()) != null) {
      content.append(line).append("\n");
      lineNum++;
      String[] words = line.split("[\\s\\p{Punct}]+");
      for (String word : words) {
        insertWord(word.toLowerCase(), lineNum);
      }
    }
    return content;
  }

  public void view() {
    inorderTravesal(root);
  }

  public void inorderTravesal(Word word) {
    if (word != null) {
      inorderTravesal(word.left);
      Vector row = new Vector();
      row.add(word.info);
      row.add(word.lines);
      data.add(row);
      inorderTravesal(word.right);
    }
  }

  public Vector getData() {
    inorderTravesal(root);
    return data;
  }

  public void destroy() {
    root = null;
    data = new Vector();
    number = total = 0;
  }
}
