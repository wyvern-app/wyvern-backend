package app.wyvern.common.packets.errors

import app.wyvern.common.entities.enums.ErrorCode
import app.wyvern.common.entities.enums.GatewayOpCode
import app.wyvern.common.packets.abstract.PacketData


@kotlinx.serialization.Serializable
class ErrorPacket(
    override val id: GatewayOpCode = GatewayOpCode.ERROR,
    override val type: String,
    val data: ErrorManifest
) : PacketData() {
    @kotlinx.serialization.Serializable
    class ErrorManifest(
        val code: ErrorCode,
        val message: String
    )
    constructor(type: String, code: ErrorCode, message: String) : this(type = type, data = ErrorManifest(code, message))
}