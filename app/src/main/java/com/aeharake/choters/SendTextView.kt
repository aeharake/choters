package com.aeharake.choters

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener

class SendTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var etText: EditText
    private var btnSend: ImageView

    interface OnSendClickListener {
        fun onSendClick(text: String)
    }

    private var onSendClickListener: OnSendClickListener? = null

    init {
        inflate(context, R.layout.send_text_layout, this)
        etText = findViewById(R.id.et_text)
        btnSend = findViewById(R.id.btn_send)
        etText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                btnSend.isEnabled = s.toString().isNotBlank()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


        })
        btnSend.setOnClickListener {
            onSendClickListener?.onSendClick(getText())
            clearText()
        }
        btnSend.isEnabled = false
    }


    private fun clearText() {
        etText.setText("")
    }

    fun getText(): String {
        return etText.text.toString()
    }

    fun setOnSendClickListener(onSendClickListener: OnSendClickListener?) {
        this.onSendClickListener = onSendClickListener
    }

}