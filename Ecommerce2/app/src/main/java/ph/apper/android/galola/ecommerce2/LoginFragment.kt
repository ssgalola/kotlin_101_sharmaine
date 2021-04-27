package ph.apper.android.galola.ecommerce2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tv_register_here).setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_SecondFragment)
        }

        view.findViewById<Button>(R.id.btn_login).setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_HomeFragment)
        }
    }
}