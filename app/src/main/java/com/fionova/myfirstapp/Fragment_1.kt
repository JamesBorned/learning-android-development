package com.fionova.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fionova.myfirstapp.databinding.ActivityFragment1Binding

class Fragment_1 : Fragment() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_fragment1)
//    }

    private val myAdapter = PhonesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val root = inflater.inflate(R.layout.activity_fragment1, container, false)
//
//        loadData()
//        root.recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        root.recyclerView.adapter = myAdapter
//
//        return root
        val root = inflater.inflate(R.layout.activity_fragment1, container, false)

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