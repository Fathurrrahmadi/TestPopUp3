package com.fathur.testpopup3

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var imageoption: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageoption =findViewById<ImageView>(R.id.img_option)

        imageoption.setOnClickListener { val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId){
                    R.id.menu_open_website -> {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://imgur.com/a/oC9G6eq"))
                        startActivity(intent)
                        true
                    }
                    R.id.menu_show_toast -> {
                        Toast.makeText(this, "pathetic.", Toast.LENGTH_LONG).show()
                        true
                    }
                    else -> false
                }
            }

            popupMenu.inflate(R.menu.menu_main)

            try {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popupMenu)
                mPopup.javaClass
                        .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                        .invoke(mPopup, true)
            } catch (e: Exception){
                Log.e("Main", "Error showing menu icons.", e)
            } finally {
                popupMenu.show()
            }
        }

    }
}