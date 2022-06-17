package hello.spamemailfilterservice.config

import hello.spamemailfilterservice.Constants
import hello.spamemailfilterservice.filtering.SpamDetector
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class SpamMailConfig {

    @Bean
    open fun spamDetector(): SpamDetector = SpamDetector().apply { init(Constants.SPAM_DATA_FILE_PATH) }

}