package com.slack.api.model.kotlin_extension.block.element

import com.slack.api.model.block.element.ChannelsSelectElement
import com.slack.api.model.kotlin_extension.block.BlockLayoutBuilder
import com.slack.api.model.kotlin_extension.block.Builder
import com.slack.api.model.kotlin_extension.block.composition.container.SingleConfirmationDialogContainer
import com.slack.api.model.kotlin_extension.block.composition.dsl.ConfirmationDialogDsl
import com.slack.api.model.kotlin_extension.block.element.container.SingleActionIdContainer
import com.slack.api.model.kotlin_extension.block.element.container.SinglePlaceholderContainer
import com.slack.api.model.kotlin_extension.block.element.dsl.ActionIdDsl
import com.slack.api.model.kotlin_extension.block.element.dsl.PlaceholderDsl

// same name with the object + "Builder" suffix
@BlockLayoutBuilder
class ChannelsSelectElementBuilder private constructor(
        private val actionIdContainer: SingleActionIdContainer,
        private val confirmationDialogContainer: SingleConfirmationDialogContainer,
        private val placeholderContainer: SinglePlaceholderContainer
) : Builder<ChannelsSelectElement>,
        ActionIdDsl by actionIdContainer,
        ConfirmationDialogDsl by confirmationDialogContainer,
        PlaceholderDsl by placeholderContainer {
    private var initialChannel: String? = null
    private var responseUrlEnabled: Boolean? = null

    constructor() : this(SingleActionIdContainer(), SingleConfirmationDialogContainer(), SinglePlaceholderContainer())

    fun initialChannel(channel: String) {
        initialChannel = channel
    }

    fun responseUrlEnabled(enabled: Boolean) {
        responseUrlEnabled = enabled
    }

    override fun build(): ChannelsSelectElement {
        return ChannelsSelectElement.builder()
                .actionId(actionIdContainer.underlying)
                .placeholder(placeholderContainer.underlying)
                .initialChannel(initialChannel)
                .confirm(confirmationDialogContainer.underlying)
                .responseUrlEnabled(responseUrlEnabled)
                .build()
    }
}