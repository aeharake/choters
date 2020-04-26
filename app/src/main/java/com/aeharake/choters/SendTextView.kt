package com.aeharake.choters

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView

class SendTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var etText: EditText
    private var btnSend: ImageView

    interface OnSendClickListener{
        fun onSendClick(text: String)
    }
    private var onSendClickListener: OnSendClickListener? = null
    init {
        inflate(context, R.layout.send_text_layout, this)
        etText = findViewById(R.id.et_text)
        btnSend = findViewById(R.id.btn_send)
        btnSend.setOnClickListener {
            onSendClickListener?.onSendClick(getText())
            clearText()
        }
    }

    private fun clearText() {
        etText.setText("")
    }

    fun getText() : String{
        return etText.text.toString()
    }

    fun setOnSendClickListener(onSendClickListener: OnSendClickListener?){
        this.onSendClickListener = onSendClickListener
    }

}