package test_with_remote_apis.methods

import com.slack.api.Slack
import com.slack.api.methods.kotlin_extension.request.chat.blocks
import org.junit.Test
import test_with_remote_apis.config.Constants
import kotlin.test.assertNull

class ChatScheduleMessageTest {

    @Test
    fun test() {
        val slack = Slack.getInstance()
        val client = slack.methods(System.getenv(Constants.SLACK_SDK_TEST_BOT_TOKEN))
        val modification = client.chatScheduleMessage {
            it.channel("#random")
                    .text("fallback")
                    .postAt(((System.currentTimeMillis() / 1000) + 20).toInt())
                    .blocks {
                        section {
                            plainText("test")
                        }
                        divider()
                        actions {
                            blockId("actions-id")
                            button {
                                actionId("a-id-1")
                                text("Button 1 :rocket:")
                                value("value-1")
                            }
                            button {
                                actionId("a-id-2")
                                text("Button 2 :rocket:", emoji = false)
                                value("value-2")
                            }
                        }
                    }

        }
        assertNull(modification.error)
    }

}
