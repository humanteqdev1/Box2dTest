package io.humanteq.df2

import android.graphics.Bitmap
import android.opengl.GLES20
import android.opengl.GLUtils
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer


class Comparison2 : ApplicationAdapter() {
    lateinit var spriteBatch: SpriteBatch
    lateinit var avatar: Texture
    lateinit var sr: ShapeRenderer
    lateinit var avatarRenderer: ShapeRenderer
    lateinit var dotRenderer: ShapeRenderer
    private val bgColor = Color.valueOf("171926")
    private val dotColor = Color.valueOf("d8d8d8")
    private var xStep = 0f
    private var yStep = 0f
    private val columns = 40
    private val rows = 40
    private var initialYOffset = 0f
    private var xStepAccum = 0f
    private var yStepAccum = 0f
    private var avatarRadius = 0f
    private var avatarGap = 0f
    //    private val avatarListTemp = arrayListOf<Bitmap>()
    private val avatarList = arrayListOf<Texture>()

    override fun create() {
        Gdx.graphics.isContinuousRendering = false
//        avatar = Texture()
        bgColor.a = 0f
        dotRenderer = ShapeRenderer()
        dotRenderer.color = dotColor
        avatarRenderer = ShapeRenderer()
        avatarRenderer.color = Color.WHITE
        spriteBatch = SpriteBatch()

        val width = Gdx.graphics.width
        val height = Gdx.graphics.height

        xStep = (width / columns).toFloat()
        yStep = (height / rows).toFloat()

//        avatarList.clear()
//        avatarList.addAll(avatarListTemp.toTextureList())
    }

    override fun render() {
        if (xStep == 0f || avatarList.isEmpty())
            return

        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        xStepAccum = 0f
        yStepAccum = initialYOffset
        dotRenderer.begin(ShapeRenderer.ShapeType.Filled)
        repeat(rows + 1) {
            repeat(columns + 1) {
                dotRenderer.circle(xStepAccum, yStepAccum, 1f)
                xStepAccum += xStep
            }
            xStepAccum = 0f
            yStepAccum += yStep
        }

        dotRenderer.end()
//        avatar.begin()
//        avatar.end()
        avatarRenderer.begin(ShapeRenderer.ShapeType.Filled)
        avatarRenderer.circle(250f, 250f, avatarRadius)
        avatarRenderer.end()

        if (avatarList.isNotEmpty()) {
            spriteBatch.begin()
//        spriteBatch.draw(avatarList[0], 250f, 250f)
            spriteBatch.draw(avatarList[0], 200f, 300f)
            spriteBatch.draw(avatarList[1], 300f, 400f)
            spriteBatch.end()
        }

        Gdx.graphics.isContinuousRendering = false
    }

    override fun dispose() {
        avatar.dispose()
//        img.dispose()
        sr.dispose()
        dotRenderer.dispose()
        spriteBatch.dispose()
    }

    fun setInitialOffset(value: Float) {
        initialYOffset = value
    }

    fun setAvatarRadius(avatarRadius: Float, avatarGap: Float) {
        this.avatarRadius = avatarRadius
        this.avatarGap = avatarGap
    }

//    fun setImageList(avatarList: ArrayList<android.graphics.Bitmap>) {
//        this.avatarList.clear()
//        this.avatarListTemp.addAll(avatarList)
//    }

    fun setTextureList(textureList: Collection<Texture>) {
        this.avatarList.addAll(textureList)
    }

    fun rerender() {
        Gdx.graphics.requestRendering()
    }
}

fun java.util.ArrayList<Bitmap>.toTextureList(): Collection<Texture> {
    val result = arrayListOf<Texture>()

    forEach {
        val tex = Texture(it.width, it.height, Pixmap.Format.RGBA8888)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex.textureObjectHandle)
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, it, 0)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0)

//            it.recycle()
        result.add(tex)
    }

    return result
}
