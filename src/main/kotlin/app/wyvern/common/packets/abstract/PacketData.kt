package app.wyvern.common.packets.abstract

import app.wyvern.common.entities.enums.GatewayOpCode
import kotlinx.serialization.Serializable

@Serializable
abstract class PacketData{
    abstract val id: GatewayOpCode
    abstract val type: String
}