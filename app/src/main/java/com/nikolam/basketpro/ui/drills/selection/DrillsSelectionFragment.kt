package com.nikolam.basketpro.ui.drills.selection


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikolam.basketpro.databinding.DrillsSelectionFragmentBinding
import com.nikolam.basketpro.model.DrillsType
import com.nikolam.basketpro.util.DrillsClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrillsSelectionFragment : Fragment(), DrillsClickListener {


    private lateinit var viewDataBinding: DrillsSelectionFragmentBinding

    private val drillsSelectionviewModel by viewModel<DrillsSelectionViewModel>()

    lateinit var drillSelectionAdapter : DrillSelectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DrillsSelectionFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = drillsSelectionviewModel
        }
        viewDataBinding.lifecycleOwner = this


        setupAdapter()

        observeLiveData()


        return viewDataBinding.root
    }


    fun observeLiveData(){

        drillsSelectionviewModel.drillsTypeList.observe(viewLifecycleOwner, Observer {
            updateAdapter(it)
        })

    }


    fun setupAdapter() {

        drillSelectionAdapter = DrillSelectionAdapter(requireContext(), this)
        viewDataBinding.drillsSelectionRecycleView.apply {


            layoutManager = LinearLayoutManager(activity).apply {
                isSmoothScrollbarEnabled = true
            }

            adapter = drillSelectionAdapter
            this.addItemDecoration(
                DividerItemDecoration(
                    context!!,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun recyclerViewListClicked(id: String, view: View) {
        val action = DrillsSelectionFragmentDirections.actionDrillsSelectionFragmentToDrillsListFragment(id)
        findNavController().navigate(action)
    }

    fun updateAdapter(list : ArrayList<DrillsType>) {
        Log.d("TAG", list.toString())
        drillSelectionAdapter.update(list)
    }


}

