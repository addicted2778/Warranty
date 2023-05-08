package com.warranty.base

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    fun getColor(id: Int): Int {
        val version = Build.VERSION.SDK_INT
        return if (version >= 23) {
            ContextCompat.getColor(context, id)
        } else {
            context.resources.getColor(id)
        }
    }

    fun getDrawable(id: Int): Drawable {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.resources.getDrawable(id, context.theme)
        } else {
            context.resources.getDrawable(id)
        }
    }

    fun wordFirstCap(str: String): String {
        val words = str.trim { it <= ' ' }.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val ret = StringBuilder()
        for (i in words.indices) {
            if (words[i].trim { it <= ' ' }.length > 0) {
                ret.append(words[i].trim { it <= ' ' }[0].uppercaseChar())
                ret.append(words[i].trim { it <= ' ' }.substring(1))
                if (i < words.size - 1) {
                    ret.append(' ')
                }
            }
        }
        return ret.toString()
    }
}