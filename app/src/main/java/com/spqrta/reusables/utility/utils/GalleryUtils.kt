package com.spqrta.reusables.utility.utils

import android.app.Activity
import android.database.Cursor
import android.graphics.Bitmap
import android.provider.MediaStore
import com.spqrta.reusables.utility.CustomApplication
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

object GalleryUtils {

    fun fetchGalleryImages(context: Activity): List<String>? {
        val columns = arrayOf(
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media._ID
        ) //get all columns of type images
        val orderBy = MediaStore.Images.Media.DATE_ADDED //order data by date
        val imagecursor: Cursor = context.managedQuery(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
            null, "$orderBy DESC"
        ) //get all data in Cursor by sorting in DESC order
        val galleryImageUrls: ArrayList<String> = ArrayList()
        for (i in 0 until imagecursor.count) {
            imagecursor.moveToPosition(i)
            val dataColumnIndex: Int =
                imagecursor.getColumnIndex(MediaStore.Images.Media.DATA) //get column index
            galleryImageUrls.add(imagecursor.getString(dataColumnIndex)) //get Image from column index
        }
        return galleryImageUrls
    }

    fun addToGallery(bitmap: Bitmap, title: String? = null) {
        MediaStore.Images.Media.insertImage(
            CustomApplication.context.contentResolver, bitmap, title ?: LocalDateTime.now().format(
            DateTimeFormatter.ISO_DATE_TIME)+".jpg" , "")
    }

}