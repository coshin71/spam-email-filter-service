package hello.spamemailfilterservice.filtering

import hangeul.Hangeul
import hello.spamemailfilterservice.filtering.candidate.CandidateSearcher
import hello.spamemailfilterservice.filtering.data.Data
import hello.spamemailfilterservice.filtering.dictionary.AssetDataDictionary
import hello.spamemailfilterservice.filtering.excel.ExcelReader
import java.io.File

class SpamDetector {

    private val dictionary = AssetDataDictionary()
    private lateinit var dataList: List<Data>
    private lateinit var wordGroupList: List<Pair<Data.Type, List<String>>>
    private lateinit var spamList: List<Pair<String, Int>>
    private lateinit var hamList: List<Pair<String, Int>>
    private var hamCount: Int = 0
    private var spamCount: Int = 0

    fun init(filePath: String) {
        val _filePath = File("").absoluteFile
        System.out.println("[!!!!!!!!!] $_filePath")
        dataList = ExcelReader.xlsToDataList(filePath)
        wordGroupList =
            dataList.groupBy { it.type }.map { it.key to it.value.map { it.subtractNouns(dictionary) }.flatten() }
        spamList = wordGroupList.first().second.groupBy { it }.map { it.key to it.value.size }
        hamList = wordGroupList.last().second.groupBy { it }.map { it.key to it.value.size }
        hamCount = hamList.sumOf { it.second }
        spamCount = spamList.sumOf { it.second }
    }

    fun detect(target: String): Result {
        var spamProbability = 1.00
        var hamProbability = 1.00
        val testWordList = mutableListOf<String>()
        target.split(" ").forEach { word ->
            val searcher = CandidateSearcher(dictionary, word, Hangeul.spreadHangeulString(word))
            searcher.search()
            val allCandidates = searcher.getCandidateArrayList().flatten()
                .map { it.componentList }
                .map { components -> components.filter { it.getTypeName() == "명사" && it.getSource().length > 1 } }
                .flatten()
                .map { it.getSource() }
            allCandidates.firstOrNull()?.let { testWordList.add(it) }
        }
        testWordList.forEach { testWord ->
            hamProbability *= (((hamList.find { it.first == testWord }?.second?.toDouble()
                ?: 0.005)) / hamCount.toDouble())
            spamProbability *= (((spamList.find { it.first == testWord }?.second?.toDouble()
                ?: 0.005)) / spamCount.toDouble())
        }
        return Result(spamProbability > hamProbability, hamProbability, spamProbability)
    }

    data class Result(val isSpam: Boolean, val hamProbability: Double, val spamProbability: Double)
}