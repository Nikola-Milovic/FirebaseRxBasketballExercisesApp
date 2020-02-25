package com.nikolam.basketpro.ui.drills.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.nikolam.basketpro.R
import com.nikolam.basketpro.databinding.DrillsListFragmentBinding
import com.nikolam.basketpro.model.Drill
import com.nikolam.basketpro.util.DrillsClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrillsListFragment : Fragment(), DrillsClickListener{

    private lateinit var viewDataBinding: DrillsListFragmentBinding

    private val drillsListviewModel by viewModel<DrillsListViewModel>()

    val args: DrillsListFragmentArgs by navArgs()

    lateinit var drillListAdapter : DrillsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DrillsListFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = drillsListviewModel
        }
        viewDataBinding.lifecycleOwner = this


        setupAdapter()

        observeLiveData()

        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drillsListviewModel.fetchDrillList(args.DrillType)

        Log.d("TAG", args.DrillType)
    }


    fun observeLiveData(){
        drillsListviewModel.drillsList.observe(viewLifecycleOwner, Observer {
            updateAdapter(it)

        })

    }


    fun updateAdapter(list : ArrayList<Drill>){

        drillListAdapter.update(list)

    }



    fun setupAdapter() {


        drillListAdapter = DrillsListAdapter(requireContext(), this)
        viewDataBinding.drillsListRecycleView.apply {


            layoutManager = LinearLayoutManager(activity).apply {
                isSmoothScrollbarEnabled = true
            }

            adapter = drillListAdapter
            this.addItemDecoration(
                DividerItemDecoration(
                    context!!,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun recyclerViewListClicked(id: String, view: View) {
        val action = DrillsListFragmentDirections.actionDrillsListFragmentToDrillDetailFragment(id)
        findNavController().navigate(action)
    }

}
