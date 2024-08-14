package com.example.likemindsassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.likemindsassignment.databinding.ActivityCharacterBinding
import com.example.likemindsassignment.databinding.ActivityMainBinding
import com.example.likemindsassignment.utils.NetworkResult
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {

    private var _binding: ActivityCharacterBinding?= null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CharacterViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val index = intent.getIntExtra("KEY_INDEX", 0)
        _binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindObserver()
        viewModel.getChar(index)
    }

    private fun bindObserver(){
        viewModel.charHarryPoterLiveData.observe(this) {
            when(it){
                is NetworkResult.Success -> {
                    binding.apply {
                        characterActivityName.text = it.data?.fullName ?: ""
                        characterActivityNickName.text = it.data?.nickname ?: ""
                        characterActivityHouse.text = it.data?.hogwartsHouse ?: ""
                        characterActivityInterpreted.text = it.data?.interpretedBy ?: ""
                        characterActivityChildren.text = it.data?.children?.firstOrNull() ?: ""
                        characterActivityBirthday.text = it.data?.birthdate ?: ""
                        Picasso.get().load(it.data?.image).into(binding.characterActivityImage)
                    }
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
}