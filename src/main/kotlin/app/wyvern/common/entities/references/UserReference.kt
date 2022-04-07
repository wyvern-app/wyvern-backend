package app.wyvern.common.entities.references

import app.wyvern.common.entities.interfaces.CommonUser
import app.wyvern.common.entities.interfaces.Reference

data class UserReference(val id: Long, val username: String?) : Reference<CommonUser> {
    override fun tryGet(): CommonUser? {
        return null
    }
}
