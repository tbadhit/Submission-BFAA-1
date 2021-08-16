package com.tbadhit.submissionbfaa_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tbadhit.submissionbfaa_1.databinding.ActivityDetailBinding
import com.tbadhit.submissionbfaa_1.model.User

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        binding.apply {
            tvName.text = user.name
            tvUsername.text = user.username

            Glide.with(this@DetailActivity)
                .load(user.avatar)
                .apply(RequestOptions())
                .into(binding.imgItemPhoto)

            tvCompany.text = user.company
            tvLocation.text = user.location
            tvRepository.text = user.repository.toString()
            tvFollowers.text = user.follower.toString()
            tvFollowing.text = user.following.toString()
        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}