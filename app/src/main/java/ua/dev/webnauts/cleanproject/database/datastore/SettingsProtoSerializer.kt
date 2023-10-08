package ua.dev.webnauts.cleanproject.database.datastore

import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import ua.dev.webnauts.cleanproject.SettingsProto
import ua.dev.webnauts.cleanproject.utils.ukToUaOrDefault
import java.io.InputStream
import java.io.OutputStream
import java.util.Locale

object SettingsProtoSerializer: Serializer<SettingsProto> {

    override val defaultValue: SettingsProto
        get() {
            val defaultLanguage = Locale.getDefault().language.ukToUaOrDefault()
            return SettingsProto.getDefaultInstance()
                .toBuilder()
                .setLocale(defaultLanguage)
                .build()
        }

    override suspend fun readFrom(input: InputStream): SettingsProto {
        return try {
            SettingsProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            exception.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: SettingsProto, output: OutputStream) {
        t.writeTo(output)
    }

}