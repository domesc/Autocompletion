package com.github.domesc

import scala.annotation.tailrec

/** The class defines a Trie data structure and give utilities to search and insert */
class Trie(root: TrieNode = TrieNode()) {
  
  /** Search the node corresponding to a certain prefix, return [[None]] if not found */
  def search(prefix: String): Option[TrieNode] = {
    @tailrec
    def searchRec(node: TrieNode, index: Int): Option[TrieNode] = {
      if(index == prefix.length) Some(node)
      else {
        val charIndex = prefix.charAt(index).toLower
        node.children.get(charIndex) match {
          case Some(nextNode) => searchRec(nextNode, index + 1)
          case _ => None
        }
      }
    }
    
    searchRec(root, 0)
  }
  
  /** Insert a key in the Trie data structure */
  def insert(key: String): Unit = {
    @tailrec
    def insertRec(node: TrieNode, index: Int): Unit = {
      if(index == key.length) node.isEndOfWord = true
      else if(index < key.length) {
        val charIndex = key.charAt(index).toLower
        if (!node.children.contains(charIndex)) node.children.put(charIndex, TrieNode())
        
        node.children.get(charIndex) match {
          case Some(nextNode) => insertRec(nextNode, index + 1)
          case None => // do nothing (this case shouldn't happen anyway)
        }
      }
    }
    
    insertRec(root, 0)
  }
  
  def insertAll(words: List[String]): Unit = words.foreach(insert)
}
