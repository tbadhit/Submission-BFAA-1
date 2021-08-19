package com.tbadhit.submissionbfaa_1

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tbadhit.submissionbfaa_1.adapter.CardViewUserAdapter
import com.tbadhit.submissionbfaa_1.databinding.ActivityMainBinding
import com.tbadhit.submissionbfaa_1.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvUser: RecyclerView

    private var list: ArrayList<User> = arrayListOf()
    private lateinit var adapter: CardViewUserAdapter

    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataPhoto: TypedArray
    private lateinit var dataCompany: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataFollowing: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.appbar)

        rvUser = binding.rvUsers
        rvUser.setHasFixedSize(true)

        prepare()

        showRecyclerCardView()

        addItem()

        adapter.setOnItemClickListener(object : CardViewUserAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val userData = User(
                    list[position].username,
                    list[position].name,
                    list[position].avatar,
                    list[position].company,
                    list[position].location,
                    list[position].repository,
                    list[position].follower,
                    list[position].following
                )

                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, userData)
                startActivity(intent)
            }

        })
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataPhoto = resources.obtainTypedArray(R.array.avatar)
        dataCompany = resources.getStringArray(R.array.company)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepository = resources.getStringArray(R.array.repository)
        dataFollowers = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
    }

    private fun showRecyclerCardView() {
        rvUser.layoutManager = LinearLayoutManager(this)
        adapter = CardViewUserAdapter(this)
        rvUser.adapter = adapter
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val user = User(
                dataUsername[position],
                dataName[position],
                dataPhoto.getResourceId(position, -1),
                dataCompany[position],
                dataLocation[position],
                dataRepository[position].toInt(),
                dataFollowers[position].toInt(),
                dataFollowing[position].toInt()
            )
            list.add(user)
        }
        adapter.listUsers = list
    }
}