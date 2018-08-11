package com.github.domesc

import scala.collection.mutable

/** Data structure representing a node in a [[Trie]]*/
class TrieNode(
  var isEndOfWord: Boolean,
  val children: mutable.Map[Char, TrieNode]
)

object TrieNode {
  def apply(
    isEndOfWord: Boolean = false,
    children: mutable.Map[Char, TrieNode] = mutable.HashMap.empty
  ): TrieNode = new TrieNode(isEndOfWord,children)
}