package hello.spamemailfilterservice.filtering.component

interface Component {
    fun getTypeName(): String
    fun getSource(): String
}