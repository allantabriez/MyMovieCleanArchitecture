package com.example.madesubmission.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.madesubmission.R
import kotlinx.android.synthetic.main.fragment_dialog.*


class DialogFragment : DialogFragment(), View.OnClickListener {

    companion object{
        var EXTRA_DATA = "extra_data"
    }

    private var data: String? = null
    private var listener: OnDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        data = arguments?.getString(EXTRA_DATA)
        deleteText.text = resources.getString(R.string.delete_from_favorite, data)
        yesButton.setOnClickListener(this)
        noButton.setOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnDialogListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnDialogListener {
        fun onRetrieved(text: String)
    }

    override fun onClick(view: View?) {
        when (view){
            yesButton -> {
                listener?.onRetrieved("Delete")
                dialog?.dismiss()
            }
            noButton -> {
                listener?.onRetrieved("Canceled")
                dialog?.dismiss()
            }
        }
    }
}