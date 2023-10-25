package com.example.calculatorapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

internal class GridRVAdapter(
    private val keyboardList: List<GridViewModal>,
    private val context: Context
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var keyTV: TextView

    override fun getCount(): Int {
        return keyboardList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView

        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.gridview_item, null)
        }
        // on below line we are initializing our course image view
        // and course text view with their ids.
        keyTV = convertView!!.findViewById(R.id.idKey)

        keyTV.setText(keyboardList.get(position).keyName)

        if (keyboardList.get(position).category == Category.OPERATOR) {
            keyTV.setBackgroundResource(R.color.operator_color);
        } else {
            keyTV.setBackgroundResource(R.color.operand_color);
        }
        // at last we are returning our convert view.
        return convertView
    }
}