package io.humanteq.df2

import android.graphics.Bitmap
import android.os.Bundle
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.activity.*


class Activity : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)
        val config = AndroidApplicationConfiguration()
        config.useAccelerometer = false
        config.disableAudio = true

        val avatarList = arrayListOf<Bitmap>()

        val comp = Comparison2()
        val view = initializeForView(comp, config)
        comp.setInitialOffset(resources.getDimension(R.dimen.y_offset))
        comp.setAvatarRadius(resources.getDimension(R.dimen.avatar_radius), resources.getDimension(R.dimen.avatar_gap))
        box2dContainer.addView(view)
        Glide.with(context)
                .asBitmap()
                .apply(RequestOptions().dontAnimate().dontTransform().circleCrop())
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRAQBguYhewMnxgcoKPV6PwPR3IkAW6nz6Y8R4R8pMjgjfdma0")
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        Gdx.app.postRunnable {
                            avatarList.add(resource)
                            comp.setTextureList(avatarList.toTextureList())
                            comp.rerender()
                        }
                    }
                })
        Glide.with(context)
                .asBitmap()
                .apply(RequestOptions().dontAnimate().dontTransform().circleCrop())
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcST85rMyu_cMlpBaHHXmLC5QLEDU5EviVBycrQeBuT6iBFKATOK")
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        Gdx.app.postRunnable {
                            avatarList.add(resource)
                            comp.setTextureList(avatarList.toTextureList())
                            comp.rerender()
                        }
                    }
                })

//        bg {
////            val bitmap1 = Glide.with(context)
////                    .asBitmap()
////                    .apply(RequestOptions().dontAnimate().dontTransform().circleCrop())
////                    .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRAQBguYhewMnxgcoKPV6PwPR3IkAW6nz6Y8R4R8pMjgjfdma0")
////                    .submit(450, 450)
////                    .get()
////            val bitmap2 = Glide.with(context)
////                    .asBitmap()
////                    .apply(RequestOptions().dontAnimate().dontTransform().circleCrop())
////                    .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcST85rMyu_cMlpBaHHXmLC5QLEDU5EviVBycrQeBuT6iBFKATOK")
////                    .submit(450, 450)
////                    .get()
////            val options = BitmapFactory.Options()
////            options.inPreferredConfig = Bitmap.Config.ARGB_8888
////            val bitmap3 = BitmapFactory.decodeResource(resources, R.drawable.linux, options)
//
////            avatarList.add(bitmap2)
////            avatarList.add(bitmap3)
//
//
////            view.invalidate()
//
//        }
//        view.invalidate()
//        val options = BitmapFactory.Options()
//        options.inPreferredConfig = Bitmap.Config.ARGB_8888
//        val bitmap3 = BitmapFactory.decodeResource(resources, R.drawable.linux, options)
//        avatarList.add(bitmap3)
//        comp.setImageList(avatarList)


        Glide.with(context)
                .asBitmap()
                .apply(RequestOptions().dontAnimate().dontTransform().circleCrop())
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRAQBguYhewMnxgcoKPV6PwPR3IkAW6nz6Y8R4R8pMjgjfdma0")
                .into(iv)
//        comp.pause()
    }
}