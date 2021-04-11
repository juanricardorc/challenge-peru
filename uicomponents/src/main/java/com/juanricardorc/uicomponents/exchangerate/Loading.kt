package com.juanricardorc.uicomponents.exchangerate

import android.R
import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView


class Loading : RelativeLayout {
    private var relativeLayout: RelativeLayout? = null
    private var messageTextViewAlertDialog: TextView? = null
    //private var lottieAnimationViewAlertDialog: LottieAnimationView? = null
    private var alertDialog: AlertDialog? = null
    private var typedArray: TypedArray? = null

    constructor(context: Context) : super(context) {
        views(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        views(context)
        setTypeArray(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        views(context)
        setTypeArray(context, attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        views(context)
        setTypeArray(context, attrs)
    }

    private fun views(context: Context) {
        View.inflate(getContext(), R.layout.expandable_list_content, this)
        binds(context)
    }

    private fun binds(context: Context) {

        //relativeLayout = findViewById(R.id.relative_layout_custom_loading)
        //relativeLayout.setVisibility(View.GONE)
    }

    private fun setTypeArray(context: Context, attrs: AttributeSet) {
       // typedArray = context.obtainStyledAttributes(attrs, R.styleable.Loading)
        setupTypeArray(typedArray, context)
        typedArray!!.recycle()
    }

    private fun setupTypeArray(typedArray: TypedArray?, context: Context) {
        /*relativeLayout!!.setBackgroundResource(
            typedArray!!.getResourceId(
                R.styleable.Loading_loading_background,
                R.drawable.background_transparent
            )
        )*/
        setupAlertDialog()
        setupTextMessage()
        setupTextSizeMessage()
        setupTextColorMessage()
        //setupMessageTypeFace(typedArray, context);
    }

    private fun setupAlertDialog() {
        /*alertDialog = Builder(context).create()
        val view: View = LayoutInflater.from(context).inflate(R.layout.canvas_loading, null)
        messageTextViewAlertDialog = view.findViewById(R.id.message_text_view_custom_loading)
        lottieAnimationViewAlertDialog = view.findViewById(R.id.loading_lottie_animation_view)
        alertDialog.getWindow()
            .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.setView(view)
        alertDialog.setCancelable(false)*/
    }

    fun setupIconColor(color: Int) {
        /*val filter =
            SimpleColorFilter(typedArray!!.getColor(R.styleable.Loading_loading_icon_color, color))
        lottieAnimationViewAlertDialog.addValueCallback(
            KeyPath("**"),
            LottieProperty.COLOR_FILTER,
            LottieValueCallback<ColorFilter>(filter)
        )*/
    }

    private fun setupTextMessage() {
       /* messageTextViewAlertDialog!!.text =
            typedArray!!.getText(R.styleable.Loading_loading_text_message)*/
    }

    private fun setupTextSizeMessage() {
        /*val dimensionPixelSize = typedArray!!.getDimensionPixelSize(
            R.styleable.Loading_loading_text_size_message,
            DEFAULT_TEXT_SIZE
        )*/
        /*messageTextViewAlertDialog!!.setTextSize(
            TypedValue.COMPLEX_UNIT_SP,
            dimensionPixelSize.toFloat()
        )*/
    }

    private fun setupTextColorMessage() {
        /*messageTextViewAlertDialog!!.setTextColor(
            typedArray!!.getColor(
                R.styleable.Loading_loading_text_color_message,
                resources.getColor(R.color.white)
            )
        )*/
    }

    /*private void setupMessageTypeFace(TypedArray typedArray, Context context) {
        int integer = typedArray.getInteger(R.styleable.Loading_loading_message_type_face, DEFAULT_TYPE_FACE);
        int fontName = 0;
        switch (integer) {
            case 0:
                fontName = R.string.regular;
                break;
            case 1:
                fontName = R.string.regular_italic;
                break;
            case 2:
                fontName = R.string.bold;
                break;
            case 3:
                fontName = R.string.bold_italic;
                break;
        }
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + context.getResources().getString(fontName) + ".ttf");
        this.messageTextViewAlertDialog.setTypeface(typeface);
    }*/
    fun setTextSizeMessage(size: Float) {
        messageTextViewAlertDialog!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }

    fun setTextColorMessage(color: Int) {
        messageTextViewAlertDialog!!.setTextColor(color)
    }

    fun show(message: String?) {
        setTextMessage(message)
        //alertDialog.show()
    }

    fun show() {
        //alertDialog.show()
    }

    fun setTextMessage(message: String?) {
        messageTextViewAlertDialog!!.text = message
    }

    /***
     * Oculta y también destruye el diálogo. para volver a mostrar el diálogo, primero hay
     * que volver a crearlo.
     */
    fun dismiss() {
        //alertDialog.dismiss()
    }

    companion object {
        private const val DEFAULT_TYPE_FACE = 0
        private const val DEFAULT_TEXT_SIZE = 16
    }
}