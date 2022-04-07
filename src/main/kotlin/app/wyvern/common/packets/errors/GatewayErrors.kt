package app.wyvern.common.packets.errors

import app.wyvern.common.entities.enums.ErrorCode

object GatewayErrors {
    val ALREADY_HELLO = ErrorPacket(
        type = "GATEWAY",
        code = ErrorCode.ALREADY_HELLO,
        message = "You have already sent a Hello packet."
    )
}
