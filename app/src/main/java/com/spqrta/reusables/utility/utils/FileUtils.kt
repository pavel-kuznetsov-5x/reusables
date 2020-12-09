package com.spqrta.reusables.utility.utils

import java.io.*


object FileUtils {

    fun ensureFolderExists(file: File): File {
        if (!file.exists()) {
            file.mkdirs();
        }
        return file
    }

    fun delete(dir: File) {
        if (dir.isDirectory) {
            val children = dir.list()!!
            for (child in children) {
                val success = delete(
                    File(
                        dir,
                        child
                    )
                )
//            if (!success) {
//                return false;
//            }
            }
            dir.delete()
        } else if (dir.isFile) {
            dir.delete()
        }
    }

    fun clear(dir: File) {
        if (dir.isDirectory) {
            val children = dir.list()!!
            for (child in children) {
                val success = delete(
                    File(
                        dir,
                        child
                    )
                )
//            if (!success) {
//                return false;
//            }
            }
        } else {
            throw IllegalArgumentException("not a directory")
        }
    }

    fun writeToFile(file: File, data: String, append: Boolean = false) {
        try {
            val f = FileOutputStream(file, append)
            val pw = PrintWriter(f)
            pw.println(data)
            pw.flush()
            pw.close()
            f.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

}