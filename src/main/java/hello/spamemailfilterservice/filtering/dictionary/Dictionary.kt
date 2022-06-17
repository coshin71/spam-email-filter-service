package hello.spamemailfilterservice.filtering.dictionary

import hello.spamemailfilterservice.filtering.component.Component
import hello.spamemailfilterservice.filtering.component.HyungTaeSo
import hello.spamemailfilterservice.filtering.component.Word

interface Dictionary {
    fun find(eumsos: String?): List<Component>

    fun findWord(eumsos: String?): List<Word>

    fun findHyungTaeSo(eumsos: String?): List<HyungTaeSo>

    fun findHyungTaeSoByType(eumsos: String?, allowedTypeSet: MutableSet<String>): List<HyungTaeSo>

    fun findWordByPumsa(eumsos: String?, allowedPumsaSet: Set<String>): List<Word>

    fun findWordByEon(eumsos: String?, allowedEonSet: Set<String>): List<Word>

    fun isWellContinued(headType: String?, tailType: String?): Boolean
}