package app.wyvern.common.packets

import app.wyvern.common.entities.enums.GatewayOpCode
import app.wyvern.common.packets.abstract.PacketData
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.*
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

object PacketSerial : JsonContentPolymorphicSerializer<PacketData>(PacketData::class) {
    val packetDataClasses = mutableMapOf<GatewayOpCode, HashMap<String, KClass<out PacketData>>>()

    @OptIn(InternalSerializationApi::class)
    override fun selectDeserializer(element: JsonElement) = run {
        val op = element.jsonObject["op"]?.jsonPrimitive?.int?.let(GatewayOpCode::from) ?: throw SerializationException("Unknown Payload ${element.jsonObject}")
        val typemap = packetDataClasses[op]!!  // Assume all GatewayOpCode values are valid
        val serializer = element.jsonObject["type"]?.jsonPrimitive?.content ?: "@default"  // use "@default" if null
        typemap[serializer]?.serializer() ?: throw SerializationException("...")  // TODO: Give this a proper name
    }

    inline fun <reified T : PacketData> registerOp(op: GatewayOpCode, type: String){
        if(packetDataClasses.containsKey(op)){
            packetDataClasses[op]!![type] = T::class
        }else{
            packetDataClasses[op] = hashMapOf(type to T::class)
        }
    }
}