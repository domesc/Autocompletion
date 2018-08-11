package com.github.domesc

import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

import scala.collection.mutable

class AutocompleteTrieSpec extends FlatSpec with Matchers with BeforeAndAfter {

  behavior of "Autocomplete"

  var testWords: List[String] = _
  var testTrie: Trie = _
  
  before {
  
    testWords = List(
      "pandora",
      "pinterest",
      "paypal",
      "pg&e",
      "project free tv priceline",
      "press democrat",
      "progressive",
      "project runway",
      "proactive",
      "Program",
      "programming",
      "progeria",
      "progesterone",
      "progenex",
      "procurable",
      "processor",
      "proud",
      "print",
      "prank",
      "bowl",
      "owl",
      "river",
      "phone",
      "kayak",
      "kayak",
      "stamps",
      "reprobe")
    
    testTrie = new Trie()
    testTrie.insertAll(testWords)
  }
  
  it should "not find any word in an empty set of words" in {
    val emptyTrie = new Trie()
    Autocomplete.run(emptyTrie, "pro") shouldBe empty
  }
  
  it should "find maxResult words in the Trie" in {
    Autocomplete.run(testTrie, "pro") shouldEqual mutable.SortedSet("proactive", "processor", "procurable", "progenex")
  }
  
  it should "not find a prefix which is not present in the Trie" in {
    Autocomplete.run(testTrie, "zo") shouldBe empty
  }
  
  it should "find less than maxResult words" in {
    Autocomplete.run(testTrie, "pand") shouldEqual mutable.SortedSet("pandora")
  }
  
  it should "find only unique words" in {
    Autocomplete.run(testTrie, "ka") shouldEqual mutable.SortedSet("kayak")
  }
  
  it should "not care about case sensitivity" in {
    Autocomplete.run(testTrie, "progra") shouldEqual mutable.SortedSet("program", "programming")
    Autocomplete.run(testTrie, "Progra") shouldEqual mutable.SortedSet("Program", "Programming")
  }
  
  it should "find 6 words" in {
    Autocomplete.run(testTrie, "pro", maxResult = 6) shouldEqual mutable.SortedSet("proactive", "processor", "procurable", "progenex", "progeria",
      "progesterone")
  }
  
  it should "find 0 result because the max parameter is set to 0" in {
    Autocomplete.run(testTrie, "pro", maxResult = 0) shouldBe empty
  }
  
}
