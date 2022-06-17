package hello.spamemailfilterservice.filtering.data

import hello.spamemailfilterservice.filtering.candidate.CandidateSearcher
import hello.spamemailfilterservice.filtering.dictionary.AssetDataDictionary
import hangeul.Hangeul

class Data(var type: Type = Type.SPAM, var content: String = "") {
    enum class Type {
        SPAM, HAM
    }

    fun subtractNouns(dictionary: AssetDataDictionary): List<String> {
        val wordList = mutableListOf<String>()
        content.split(" ").forEach { word ->
            val searcher = CandidateSearcher(dictionary, word, Hangeul.spreadHangeulString(word))
            searcher.search()
            val allCandidates = searcher.getCandidateArrayList().flatten()
                .map { it.componentList }
                .map { components -> components.filter { it.getTypeName() == "명사" && it.getSource().length > 1 } }
                .flatten()
                .map { it.getSource() }
            allCandidates.firstOrNull()?.let { wordList.add(it) }
        }
        return wordList
    }
}