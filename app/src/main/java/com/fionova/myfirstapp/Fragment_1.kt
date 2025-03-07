package com.fionova.myfirstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Fragment_1 : Fragment() {

    private val myAdapter = PhonesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment1, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = myAdapter // Проверка на null

        loadData()
        return root
    }

    private fun loadData() {
        myAdapter.setupPhones(PhonesData.phonesArr)
    }
}