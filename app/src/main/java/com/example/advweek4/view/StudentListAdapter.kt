package com.example.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R
import com.example.advweek4.model.Student
import com.example.advweek4.viewmodel.DetailViewModel

class StudentListAdapter(val studentList:ArrayList<Student>, private val detailViewModel: DetailViewModel)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    class StudentViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val txtId = holder.itemView.findViewById<TextView>(R.id.txtNamaHome)
        val txtName = holder.itemView.findViewById<TextView>(R.id.txtWaitressHome)
        val btnDetail = holder.itemView.findViewById<Button>(R.id.btnDetail)
        txtId.text = studentList[position].id
        txtName.text = studentList[position].name
        btnDetail.setOnClickListener {
            val selectedItem = studentList[position]
            detailViewModel.setSelectedStudent(selectedItem)

            val action = StudentListFragmentDirections.actionStudentDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateStudentList(newStudentList: ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

}