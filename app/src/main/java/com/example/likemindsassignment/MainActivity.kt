package com.example.likemindsassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.likemindsassignment.data.HarryPoterResponseItem
import com.example.likemindsassignment.databinding.ActivityMainBinding
import com.example.likemindsassignment.utils.NetworkResult
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding?= null
    private val binding get() = _binding!!

    private val viewModel:MainViewModel by viewModels()
    private lateinit var adapter: HarryPorterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = HarryPorterAdapter{
            val intent = Intent(this, CharacterActivity::class.java)
            intent.putExtra("KEY_INDEX", it.index)
            startActivity(intent)
        }
        bindObserver()
        viewModel.getHarryResponse()
        binding.harryPoterRv.layoutManager= LinearLayoutManager(this)
        binding.harryPoterRv.adapter = adapter
    }



    private fun bindObserver(){
        viewModel.harryPoterLiveData.observe(this) {
            when(it){
                is NetworkResult.Success -> {
                    adapter.submitList(it.data)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(this,it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    Toast.makeText(this,"Loading...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onItemClicked(response: HarryPoterResponseItem){
        /*val bundle= Bundle()
        bundle.putString("note", Gson().toJson(noteResponse))*/
        Log.d("TAG", "In the Intent")
         val intent = Intent(this, CharacterActivity::class.java)
        startActivity(intent)
    }
}