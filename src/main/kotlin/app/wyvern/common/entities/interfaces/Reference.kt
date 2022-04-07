package app.wyvern.common.entities.interfaces

interface Reference<T> {
    fun tryGet(): T?
}