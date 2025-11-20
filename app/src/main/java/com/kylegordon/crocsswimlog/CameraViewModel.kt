package com.kylegordon.crocsswimlog

import android.app.Application
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.File

class CameraViewModel(val app: Application): AndroidViewModel(app) {
    private val _imageFileUri = MutableStateFlow<Uri?>(null)
    val imageFileUri = _imageFileUri.asStateFlow()

    fun setupImageUri(): Uri {
        val context = app.applicationContext
        val imagePath = File(context.filesDir, "images")
        if (!imagePath.exists())
            imagePath.mkdirs()

        val tmpFile = File.createTempFile("cis357", ".jpg", imagePath)
        val imageFileUri = FileProvider.getUriForFile(
            context, context.packageName + ".provider",
            tmpFile
        )
        _imageFileUri.update {
            imageFileUri
        }
        return imageFileUri
    }
    fun removeImageFile() {
        _imageFileUri.value?.let {
            app.applicationContext.contentResolver.delete(it, null, null)
        }
    }
}