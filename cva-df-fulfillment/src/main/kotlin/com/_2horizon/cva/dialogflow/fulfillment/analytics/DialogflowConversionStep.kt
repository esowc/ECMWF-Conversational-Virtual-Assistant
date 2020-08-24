package com._2horizon.cva.dialogflow.fulfillment.analytics

import java.time.OffsetDateTime

/**
 * Created by Frank Lieber (liefra) on 2020-08-23.
 */
data class DialogflowConversionStep(
    val datetime:OffsetDateTime,
    val session:String,
    val action:String,
    val responseId:String,
    val queryText:String,
    val alternativeQueryResultsCount: Int,
    val intentName:String,
    val intentDisplayName:String,
    val intentDetectionConfidence: Float,
    val outputContexts:List<String>,
)
