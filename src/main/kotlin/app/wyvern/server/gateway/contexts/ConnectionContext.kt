package app.wyvern.server.gateway.contexts

import app.wyvern.common.entities.interfaces.CommonUser
import app.wyvern.common.packets.abstract.PacketData
import app.wyvern.server.plugins.sendPacket
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConnectionContext(private val wsSession: WebSocketSession) {
    var hasSentHello = false
    lateinit var user: CommonUser
    internal var scope = CoroutineScope(Dispatchers.IO)
    fun <T : PacketData> send(packetData: T) {
        // sendPacket is suspend, so we have to launch it in a coroutine
        // despite the fact that this could *technically* be ub
        // if we send a hundred million things at once and ktor-ws isnt made well
        scope.launch { wsSession.sendPacket(packetData) }
    }


}
