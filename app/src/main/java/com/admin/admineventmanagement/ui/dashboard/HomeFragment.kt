package com.admin.admineventmanagement.ui.dashboard

import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.admin.admineventmanagement.R
import com.admin.admineventmanagement.adapter.UserEventAdapter
import com.admin.admineventmanagement.databinding.FragmentHomeBinding
import com.admin.admineventmanagement.viewmodel.ManagementViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private val viewModel: ManagementViewModel by viewModels()

    private lateinit var pieChart: PieChart

    private val calendar: Calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        pieChart = binding.pieChart
        binding.recyclerView.adapter = UserEventAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }

        val entries: MutableList<PieEntry> = ArrayList()
        entries.add(PieEntry(30f, "Entry 1"))
        entries.add(PieEntry(20f, "Entry 2"))
        entries.add(PieEntry(50f, "Entry 3"))

        val dataSet = PieDataSet(entries, null)
        dataSet.isHighlightEnabled = true
        dataSet.colors = listOf(Color.RED, Color.GREEN, Color.rgb(0,191,255))
        dataSet.valueTextSize = 16f
        dataSet.valueTextColor = Color.WHITE
        dataSet.sliceSpace = 3f

        val legend: Legend = pieChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.textSize = 16f
        legend.setDrawInside(false)

        pieChart.apply {
            setUsePercentValues(true)
            description.isEnabled = false
            legend.isEnabled = true
            setEntryLabelColor(Color.WHITE)
            setEntryLabelTextSize(16f)
            setHoleColor(Color.WHITE)
            centerText = "Attendance: 150"
//            animateY(1000, Easing.EaseInOutQuad)
        }

        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}