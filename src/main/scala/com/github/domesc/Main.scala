package com.github.domesc

import scala.io.Source

object Main extends App {
  
  val trie = new Trie()
  
  // load the file containing the words
  val file = if(args.isEmpty) Source.fromResource("words.txt") else Source.fromFile(args(0))
  for(line <- file.getLines) { trie.insert(line) }
  
  while(true) {
    println("Insert a word to search:\n")
    val results = Autocomplete.run(trie, scala.io.StdIn.readLine())
    results.foreach(println)
    println()
  }
}
