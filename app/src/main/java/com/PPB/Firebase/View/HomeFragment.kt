package com.PPB.Firebase.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.PPB.Firebase.Adapter.HomeAdapter
import com.PPB.Firebase.Model.Mahasiswa
import com.PPB.Firebase.R
import com.PPB.Firebase.databinding.FragmentHomeBinding
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private lateinit var databaseRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,
            container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState:
    Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HomeAdapter(ArrayList())
        binding.rvUser.adapter = adapter
        binding.rvUser.layoutManager = LinearLayoutManager(requireContext())
        databaseRef = FirebaseDatabase.getInstance().getReference("mahasiswa")
        databaseRef.addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot)
            {
                val mahasiswaList = ArrayList<Mahasiswa>()
                for (snapshot in dataSnapshot.children) {
                    val mahasiswa =
                        snapshot.getValue(Mahasiswa::class.java)
                    mahasiswa?.let { mahasiswaList.add(it) }
                }
                adapter.setData(mahasiswaList)
            }
            override fun onCancelled(databaseError: DatabaseError) {
// Handle database error
            }
        })
    }
}

