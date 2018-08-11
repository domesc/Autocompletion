package com.github.domesc

import scala.collection.mutable

object Autocomplete {
  
  /**
    * Search for the words with a certain prefix
    * @param trie the [[Trie]] structure containing all words.
    * @param prefix the prefix to search.
    * @param maxResult the maximum results to search
    */
  def run(trie: Trie, prefix: String, maxResult: Int = 4): mutable.SortedSet[String] = {
    val prefixNode: Option[TrieNode] = trie.search(prefix)
  
    val uniqueWords = mutable.SortedSet.empty[String]
    prefixNode.foreach(completeWords(_, prefix, uniqueWords, maxResult))
    uniqueWords
  }
  
  /**
    * Find all complete words having a certain prefix and fill them in a ordered Set, this allows us to have unique words
    * and preserve the order of them
    */
  private def completeWords(node: TrieNode, currentPrefix: String, results: mutable.SortedSet[String], max: Int): Unit = {
    if(node.isEndOfWord) results.add(currentPrefix)
    if(results.size < max) {
      // we need the results to be ordered
      val orderedChildren = node.children.toSeq.sortBy{ case(char, _) => char}
      orderedChildren.foreach{ case (char, child) => completeWords(child, currentPrefix + char, results, max)}
    }
  }
}
