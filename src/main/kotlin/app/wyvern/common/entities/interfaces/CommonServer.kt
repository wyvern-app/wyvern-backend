package app.wyvern.common.entities.interfaces

import app.wyvern.common.entities.references.UserReference
import app.wyvern.common.entities.enums.ServerType

interface CommonServer {
    val internalName: String
    val id: Long
    val owner: UserReference?
    val type: ServerType

}