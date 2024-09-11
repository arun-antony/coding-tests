package com.example.sampleapplication.feature_worldbank.data


import com.google.gson.annotations.SerializedName

data class ArchiveSamplerResponse(
    @SerializedName("alternate_locations")
    val alternateLocations: AlternateLocations? = AlternateLocations(),
    @SerializedName("created")
    val created: Int? = 0,
    @SerializedName("d1")
    val d1: String? = "",
    @SerializedName("d2")
    val d2: String? = "",
    @SerializedName("dir")
    val dir: String? = "",
    @SerializedName("files")
    val files: List<File?>? = listOf(),
    @SerializedName("files_count")
    val filesCount: Int? = 0,
    @SerializedName("item_last_updated")
    val itemLastUpdated: Int? = 0,
    @SerializedName("item_size")
    val itemSize: Int? = 0,
    @SerializedName("metadata")
    val metadata: Metadata? = Metadata(),
    @SerializedName("reviews")
    val reviews: List<Review?>? = listOf(),
    @SerializedName("server")
    val server: String? = "",
    @SerializedName("uniq")
    val uniq: Int? = 0,
    @SerializedName("workable_servers")
    val workableServers: List<String?>? = listOf()
) {
    data class AlternateLocations(
        @SerializedName("servers")
        val servers: List<Server?>? = listOf(),
        @SerializedName("workable")
        val workable: List<Workable?>? = listOf()
    ) {
        data class Server(
            @SerializedName("dir")
            val dir: String? = "",
            @SerializedName("server")
            val server: String? = ""
        )

        data class Workable(
            @SerializedName("dir")
            val dir: String? = "",
            @SerializedName("server")
            val server: String? = ""
        )
    }

    data class File(
        @SerializedName("btih")
        val btih: String? = "",
        @SerializedName("crc32")
        val crc32: String? = "",
        @SerializedName("filecount")
        val filecount: String? = "",
        @SerializedName("format")
        val format: String? = "",
        @SerializedName("md5")
        val md5: String? = "",
        @SerializedName("mtime")
        val mtime: String? = "",
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("original")
        val original: String? = "",
        @SerializedName("rotation")
        val rotation: String? = "",
        @SerializedName("sha1")
        val sha1: String? = "",
        @SerializedName("size")
        val size: String? = "",
        @SerializedName("source")
        val source: String? = "",
        @SerializedName("summation")
        val summation: String? = ""
    )

    data class Metadata(
        @SerializedName("addeddate")
        val addeddate: String? = "",
        @SerializedName("backup_location")
        val backupLocation: String? = "",
        @SerializedName("collection")
        val collection: List<String?>? = listOf(),
        @SerializedName("creator")
        val creator: String? = "",
        @SerializedName("curation")
        val curation: String? = "",
        @SerializedName("description")
        val description: String? = "",
        @SerializedName("identifier")
        val identifier: String? = "",
        @SerializedName("identifier-access")
        val identifierAccess: String? = "",
        @SerializedName("identifier-ark")
        val identifierArk: String? = "",
        @SerializedName("language")
        val language: String? = "",
        @SerializedName("mediatype")
        val mediatype: String? = "",
        @SerializedName("ocr")
        val ocr: String? = "",
        @SerializedName("openlibrary_edition")
        val openlibraryEdition: String? = "",
        @SerializedName("openlibrary_work")
        val openlibraryWork: String? = "",
        @SerializedName("ppi")
        val ppi: String? = "",
        @SerializedName("publicdate")
        val publicdate: String? = "",
        @SerializedName("repub_state")
        val repubState: String? = "",
        @SerializedName("scanner")
        val scanner: String? = "",
        @SerializedName("subject")
        val subject: List<String?>? = listOf(),
        @SerializedName("title")
        val title: String? = "",
        @SerializedName("uploader")
        val uploader: String? = ""
    )

    data class Review(
        @SerializedName("createdate")
        val createdate: String? = "",
        @SerializedName("reviewbody")
        val reviewbody: String? = "",
        @SerializedName("reviewdate")
        val reviewdate: String? = "",
        @SerializedName("reviewer")
        val reviewer: String? = "",
        @SerializedName("reviewer_itemname")
        val reviewerItemname: String? = "",
        @SerializedName("reviewtitle")
        val reviewtitle: String? = "",
        @SerializedName("stars")
        val stars: String? = ""
    )
}