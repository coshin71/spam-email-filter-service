package hello.spamemailfilterservice.filtering.candidate

import hello.spamemailfilterservice.filtering.component.Component

class Candidate {

    var componentList: MutableList<Component> = mutableListOf()
    var originalText: String? = null
    var text: String? = null

    override fun toString(): String {
        if (componentList.isEmpty()) return "(empty)"
        return "${componentList.first().getSource()}(${componentList.map { it.getTypeName() }.joinToString(",")})"
    }

    fun subtractNoun(): String? {
        if (componentList.isEmpty()) return null
        return componentList.first().getSource()
    }
}