package com._2horizon.cva.dialogflow.fulfillment.extensions

import com._2horizon.cva.common.copernicus.dto.CopernicusPageNode
import com._2horizon.cva.common.copernicus.dto.asHumanReadable
import com._2horizon.cva.common.twitter.dto.Tweet
import com._2horizon.cva.dialogflow.fulfillment.dialogflow.messenger.dto.RichContentAccordionItem
import com._2horizon.cva.dialogflow.fulfillment.dialogflow.messenger.dto.RichContentImageItem
import com._2horizon.cva.dialogflow.fulfillment.dialogflow.messenger.dto.RichContentInfoItem
import com._2horizon.cva.dialogflow.fulfillment.dialogflow.messenger.dto.RichContentItem
import com.google.cloud.dialogflow.v2beta1.Intent

/**
 * Created by Frank Lieber (liefra) on 2020-08-25.
 */
fun String.asIntentMessage(): Intent.Message {
    return Intent.Message.newBuilder()
        .setText(
            Intent.Message.Text.newBuilder().addText(this).build()
        ).build()
}

fun List<String>.asIntentMessage(): Intent.Message {
    return Intent.Message.newBuilder()
        .setText(
            Intent.Message.Text.newBuilder().addAllText(this).build()
        ).build()
}

fun CopernicusPageNode.convertToRichContentList(): MutableList<RichContentItem> {
    val items = mutableListOf<RichContentItem>()

    if (this.img != null) {
        items.add(RichContentImageItem(rawUrl = this.img!!, accessibilityText = this.title))
    }
    val infoItem = RichContentInfoItem(
        title = this.title,
        subtitle = "${this.nodeType.asHumanReadable()}: ${this.publishedAt}",
        actionLink = this.url
    )
    items.add(infoItem)
    return items
}
fun CopernicusPageNode.convertToRichAccordionList(): MutableList<RichContentItem> {
    val items = mutableListOf<RichContentItem>()

    val infoItem = RichContentAccordionItem(
        title = this.title,
        subtitle = "${this.nodeType.asHumanReadable()}: ${this.publishedAt}",
        text = """
<img src="${this.img!!}" style="height:20vh" /><br>
<p>${this.teaser}</p>
<a href="${this.url}">Click to read more</a>             
        """.trimIndent()
    )
    items.add(infoItem)
    return items
}



fun Tweet.convertToRichContentList(): MutableList<RichContentItem> {
    val items = mutableListOf<RichContentItem>()

    if (this.mediaExpandedUrls.isNotEmpty() ) {
        items.add(RichContentImageItem(rawUrl = this.mediaExpandedUrls.first(), accessibilityText = ""))
    }
    val infoItem = RichContentInfoItem(
        title = this.text,
        subtitle = "TWEET: ${this.createdAt}",
        actionLink = "https://twitter.com/${this.userScreenName}/status/${this.tweetId}"
    )
    items.add(infoItem)
    return items
}
