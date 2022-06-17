package hello.spamemailfilterservice.filtering.candidate

import hello.spamemailfilterservice.filtering.candidate.Candidate

class CandidateArray : ArrayList<Candidate> {
    constructor() : super()
    constructor(collection: Collection<Candidate>) : super(collection)
    constructor(initialCapacity: Int) : super(initialCapacity)
}