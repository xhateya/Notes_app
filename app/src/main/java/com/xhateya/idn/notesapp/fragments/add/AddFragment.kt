package com.xhateya.idn.notesapp.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.xhateya.idn.notesapp.R
import com.xhateya.idn.notesapp.data.model.NoteData
import com.xhateya.idn.notesapp.data.viewModelsData.NotesViewModel
import com.xhateya.idn.notesapp.databinding.FragmentAddBinding
import com.xhateya.idn.notesapp.fragments.SharedViewModels

class AddFragment : Fragment(){

    private var _addBinding : FragmentAddBinding? = null
    private val addBinding get() = _addBinding!!

    private val notesViewModel :NotesViewModel by viewModels()
    private val sharedViewModel : SharedViewModels by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _addBinding = FragmentAddBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        addBinding.spPrioritas.onItemSelectedListener = sharedViewModel.listener

        return addBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add){
            insertDataToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {
        val mTitle = addBinding.etTitle.text.toString()
        val mPriority= addBinding.spPrioritas.selectedItem.toString()
        val mDesc= addBinding.etDesc.text.toString()

        val validation = sharedViewModel.verifyDataFromUser(mTitle, mDesc)
        if (validation){
            val newData = NoteData(
                0,
                mTitle,
                sharedViewModel.parsePriority(mPriority),
                mDesc
            )
            notesViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Added Success", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(),"Please Fill yak", Toast.LENGTH_SHORT).show()
        }

    }


}