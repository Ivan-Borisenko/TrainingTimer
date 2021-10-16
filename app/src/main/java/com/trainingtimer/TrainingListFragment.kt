package com.trainingtimer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "TrainingListFragment"

class TrainingListFragment : Fragment() {

    private lateinit var trainingRecyclerView: RecyclerView
    private var adapter: TrainingAdapter? = null

    private val trainingListViewModel: TrainingListViewModel by lazy {
        ViewModelProviders.of(this).get(TrainingListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total trainings: ${trainingListViewModel.trainings.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_training_list, container, false)

        trainingRecyclerView =
            view.findViewById(R.id.training_recycler_view) as RecyclerView
        trainingRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private fun updateUI() {
        val trainings = trainingListViewModel.trainings
        adapter = TrainingAdapter(trainings)
        trainingRecyclerView.adapter = adapter
    }

    private inner class TrainingHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var training: Training

        private val titleTextView: TextView = itemView.findViewById(R.id.training_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.training_rest)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(training: Training) {
            this.training = training
            titleTextView.text = this.training.title
            //restTextView.text = this.training.rest.toString()
        }

        override fun onClick(v: View?) {
            Toast.makeText(context, "${training.title} pressed!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private inner class TrainingAdapter(var trainings: List<Training>)
        : RecyclerView.Adapter<TrainingHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : TrainingHolder {
            val view = layoutInflater.inflate(R.layout.list_item_training, parent, false)
            return  TrainingHolder(view)
        }

        override fun onBindViewHolder(holder: TrainingHolder, position: Int) {
            val training = trainings[position]
            holder.bind(training)
        }

        override fun getItemCount() = trainings.size
    }

    companion object {
        fun newInstance(): TrainingListFragment {
            return TrainingListFragment()
        }
    }
}