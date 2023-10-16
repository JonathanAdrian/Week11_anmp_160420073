package com.example.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R
import com.example.advweek4.model.Menu
import com.example.advweek4.viewmodel.MenuDetailViewModel
import com.example.advweek4.viewmodel.MenuViewModel
import com.example.advweek4.viewmodel.SharedViewModel

class MenuFragment : Fragment() {
    private lateinit var viewModel: MenuViewModel
    private lateinit var menuDetailViewModel: MenuDetailViewModel
    private lateinit var menuAdapter : MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuDetailViewModel = ViewModelProvider(requireActivity()).get(MenuDetailViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        menuAdapter = MenuAdapter(arrayListOf(), menuDetailViewModel)
        viewModel.refresh()
        val recViewMenu = view.findViewById<RecyclerView>(R.id.recViewMenu)
        recViewMenu.layoutManager = LinearLayoutManager(context)
        recViewMenu.adapter = menuAdapter
        observeViewModel()

        val editSearch = view.findViewById<EditText>(R.id.editSearch)
        val btnSearch = view.findViewById<Button>(R.id.btnSearch)
        val txtChange = view.findViewById<TextView>(R.id.txtChange)
        val txtTable = view.findViewById<TextView>(R.id.txtTable)

        val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel.tableNumber.observe(viewLifecycleOwner, { tableNumber ->
            txtTable.text = "Table $tableNumber"
        })

        txtChange.setOnClickListener{
            val action = MenuFragmentDirections.actionItemHome()
            Navigation.findNavController(it).navigate(action)
        }

        btnSearch.setOnClickListener {
            val searchQuery = editSearch.text?.toString()?.trim()

            // Use the existing menusLD data to filter the menu items
            val menus = viewModel.menusLD.value
            val filteredMenu = menus?.filter { menu ->
                searchQuery?.let { it1 -> menu?.nama?.contains(it1, ignoreCase = true) } == true
            }

            // Update the RecyclerView with the filtered menu items
            menuAdapter.updateMenu(filteredMenu as ArrayList<Menu>)
        }
    }

    fun observeViewModel() {
        viewModel.menusLD.observe(viewLifecycleOwner, Observer {
            menuAdapter.updateMenu(it)
        })
    }
}