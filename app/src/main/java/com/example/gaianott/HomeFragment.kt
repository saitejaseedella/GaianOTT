package com.example.gaianott

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class HomeFragment : Fragment() {
    lateinit var txtTitle : TextView
    lateinit var txtSubtitle: TextView
    lateinit var txtDescription: TextView

    lateinit var imgBanner: ImageView
    lateinit var listFragment: ListFragment_poster

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)

    }

     fun init(view: View) {



        imgBanner = view.findViewById(R.id.img_banner)
        txtTitle = view.findViewById(R.id.title)
        txtSubtitle = view.findViewById(R.id.subtitle)
        txtDescription = view.findViewById(R.id.description)


        listFragment = ListFragment_poster()
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.list_fragment, listFragment)
        transaction.commit()

        val gson = Gson()
        val i : InputStream = requireContext().assets.open("movies_poster.JSON")
        val br = BufferedReader(InputStreamReader(i))
        val datalist : DataModel=gson.fromJson(br,DataModel::class.java)

        listFragment.bindData(datalist)

        listFragment.setOnContentSelectedListener {
            //Form here the banenr is getting updated
            //can we implement a onlick function on this banner then
            //play the vidoe form local source
            println("updated")
            updateBanner(it)
        }

         listFragment.setOnItemClickListener {
             val intent  = Intent(requireContext(), VideoActivity::class.java)
             intent.putExtra("movieurl", it.id)
             startActivity(intent)
         }

    }

    private fun updateBanner(datalist: DataModel.Result.Detail) {
        txtTitle.text = datalist.title
        txtDescription.text = datalist.overview

        val url = "https://www.themoviedb.org/t/p/w780" +  datalist.backdrop_path
        Glide.with(this).load(url).into(imgBanner)
    }
}


