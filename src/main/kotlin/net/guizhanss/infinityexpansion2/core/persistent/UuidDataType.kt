package net.guizhanss.infinityexpansion2.core.persistent

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import java.nio.ByteBuffer
import java.util.UUID

class UuidDataType : PersistentDataType<ByteArray, UUID> {

    override fun getPrimitiveType() = ByteArray::class.java

    override fun getComplexType() = UUID::class.java

    override fun toPrimitive(complex: UUID, context: PersistentDataAdapterContext): ByteArray {
        val bb = ByteBuffer.wrap(ByteArray(16))
        bb.putLong(complex.mostSignificantBits)
        bb.putLong(complex.leastSignificantBits)
        return bb.array()
    }

    override fun fromPrimitive(primitive: ByteArray, context: PersistentDataAdapterContext): UUID {
        val bb = ByteBuffer.wrap(primitive)
        val firstLong = bb.getLong()
        val secondLong = bb.getLong()
        return UUID(firstLong, secondLong)
    }

    companion object {

        val TYPE = UuidDataType()
    }
}
