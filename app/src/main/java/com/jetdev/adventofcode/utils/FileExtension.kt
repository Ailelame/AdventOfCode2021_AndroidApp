package com.jetdev.adventofcode.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.annotation.IdRes
import java.nio.charset.Charset

@SuppressLint("ResourceType")
fun Resources.extractListFromRawFolder(@IdRes file : Int) : List<String>{
    val fileStream = this.openRawResource(file)
    return fileStream.reader(Charset.defaultCharset()).readLines()
}