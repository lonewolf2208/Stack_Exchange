package com.example.stackexchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stackexchange.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var viewModel: QuestionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[QuestionViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding = FragmentHomeBinding.inflate(inflater, container, false)
        var adapter = QuestionsAdapter()
        binding.apply {
            recyclerView.apply {
                this.adapter=adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            SearchContainer.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if(!SearchContainer.text.isNullOrBlank()) {
                        viewModel.word.postValue(SearchContainer.text.toString())
                        progressBarSearch.visibility = View.VISIBLE
                    }
                }
            })


        }
        viewModel.searchquestions.observe(viewLifecycleOwner
        ) {
//            Toast.makeText(requireContext(), "changed", Toast.LENGTH_SHORT).show()
            if (it != null)
            {
                binding.progressBarSearch.visibility=View.INVISIBLE
                adapter.notifyDataSetChanged()
                adapter.submitList(it?.items)
            }
        }
        viewModel.cars.observe(viewLifecycleOwner) { result ->
            if(result.data?.size!=0) {
                adapter.submitList(result.data!![0].items)
                adapter.onClickListener(object : QuestionsAdapter.ClickListener {
                    override fun OnClick(position: Int, tagPosition: Int) {
                        binding.SearchContainer.setText(result.data[0].items[position].tags[tagPosition].toString())
                    }
                    override fun OnTextClick(position: Int) {
                        val bundle = Bundle()
                        bundle.putString("url", result.data[0].items[position].link.toString())
                        findNavController().navigate(R.id.action_homeFragment_to_webView,bundle)
                    }
                })

            }
            if(result is Resource.Error)
            {
                Toast.makeText(requireContext(),result.error?.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }
}
