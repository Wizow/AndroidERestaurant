package fr.isen.paul.androiderestaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fr.isen.paul.androiderestaurant.databinding.FragmentPictureBinding

class PictureFragment : Fragment() {
    private lateinit var binding: FragmentPictureBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPictureBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("picture_url")?.let { pictureUrl ->
            if(pictureUrl=="") {
                binding.dishPictureFrag.setImageResource(R.drawable.ic_launcher_foreground)
            }
            else {
                Picasso.get()
                    .load(pictureUrl)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(binding.dishPictureFrag)
            }
        }
    }

    companion object{
        fun newInstance(pictureUrl: String) =
            PictureFragment().apply {
                arguments= Bundle().apply{
                    putString("picture_url",pictureUrl)
                }
            }
    }
}