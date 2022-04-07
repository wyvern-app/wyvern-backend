package app.wyvern.common.entities.enums

enum class GatewayOpCode {

    HELLO,
    IDENTIFY,
    HEARTBEAT,
    RESUME,
    ERROR,
    EVENT,
    ACK;

    companion object {
        fun from(value: Int): GatewayOpCode {
            return values().first { it.ordinal == value }
        }
    }
}
