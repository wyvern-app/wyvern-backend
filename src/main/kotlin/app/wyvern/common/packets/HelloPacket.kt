package app.wyvern.common.packets

import app.wyvern.common.entities.enums.GatewayOpCode
import app.wyvern.common.packets.abstract.PacketData

@kotlinx.serialization.Serializable
data class HelloPacket(
    override val id: GatewayOpCode = GatewayOpCode.HELLO,
    override val type: String = "@default",
    val data: HelloPacketPayload
) : PacketData() {
    @kotlinx.serialization.Serializable
    data class HelloPacketPayload(
        val platform: String,
        val version: String,
    )
    constructor(platform: String, version: String) : this(data = HelloPacketPayload(platform, version))
}
