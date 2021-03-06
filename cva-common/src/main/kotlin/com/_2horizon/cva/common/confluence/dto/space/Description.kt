package com._2horizon.cva.common.confluence.dto.space

import com.fasterxml.jackson.annotation.JsonProperty

data class Description(
    @JsonProperty("plain")
    val plain: Plain,
    @JsonProperty("view")
    val view: View
)
