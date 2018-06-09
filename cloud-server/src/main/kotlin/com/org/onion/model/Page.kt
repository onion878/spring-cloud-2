package onion.com.tgb.model

import java.util.*

data class Page (
        var total:Int,
        var rows:Int,
        var page:Int,
        var stt:Int,
        var limit:Int,
        var offset:Int,
        var stb:Int,
        var sort: String,
        var order: String,
        var search: String,
        var value: String,
        var sval: String,
        var q: String,
        var dateval: Date
)