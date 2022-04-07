package app.wyvern.server.plugins

import app.wyvern.common.packets.PacketSerial
import app.wyvern.common.packets.abstract.PacketData
import app.wyvern.server.gateway.contexts.ConnectionContext
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json


fun <T> Iterable<T>.forEachUnblocking(action: (T) -> Unit) {
    // run all in coroutine
    for (element in this) {
        CoroutineScope(Dispatchers.Unconfined).launch {
            element ?: return@launch
            action(element)
        }
    }
}

suspend fun ReceiveChannel<Frame>.receivePacket(): PacketData {
    val packet = this.receive()
    if (packet.frameType == FrameType.TEXT){
        val packetString = packet.data.decodeToString()
        return Json.decodeFromString(PacketSerial, packetString)
    }
    return receivePacket() // just ignore this packet lmao
}
fun Frame.decodeToPacket(): PacketData? {
    if (this.frameType == FrameType.TEXT) {
        val packetString = this.data.decodeToString()
        return Json.decodeFromString(PacketSerial, packetString)
    }
    return null
}
suspend fun WebSocketSession.openAsConnection(block: suspend ConnectionContext.() -> Unit) {
    val connectionContext = ConnectionContext(this)
    connectionContext.block()
}
suspend fun WebSocketSession.sendPacket(packet: PacketData) {
    val packetString = Json.encodeToString(PacketSerial, packet)
    this.send(Frame.Text(packetString))
}