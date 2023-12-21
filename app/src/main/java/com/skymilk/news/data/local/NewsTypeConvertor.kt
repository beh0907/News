package com.skymilk.news.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.skymilk.news.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConvertor {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id}-${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split('-').let {
            Source(
                id = it[0],
                name = it[1]
            )
        }
    }

}