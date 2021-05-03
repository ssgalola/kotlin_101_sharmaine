package ph.apper.android.galola.ecommerce2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import ph.apper.android.galola.ecommerce2.api.RetrofitClient
import ph.apper.android.galola.ecommerce2.models.DefaultResponse
import ph.apper.android.galola.ecommerce2.models.SpinnerRegistration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tv_login_here).setOnClickListener {
            findNavController().navigate(R.id.action_RegisterFragment_to_LoginFragment)
        }

        view.findViewById<Button>(R.id.btn_register).setOnClickListener{
            val email = et_email_register.text.toString().trim()
            val password = et_password_register.text.toString().trim()
            val confirmPassword = et_confirm_pw_register.text.toString().trim()

            if(email.isEmpty()){
                et_email_register.error = "Email required"
                et_email_register.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                et_password_register.error = "Password required"
                et_password_register.requestFocus()
                return@setOnClickListener
            }

            if(confirmPassword.isEmpty()){
                et_confirm_pw_register.error = "Confirm Password required"
                et_confirm_pw_register.requestFocus()
                return@setOnClickListener
            }

            if(password==confirmPassword) {
                RetrofitClient.instance.createUser(email, password)
                    .enqueue(object : Callback<DefaultResponse> {
                        override fun onResponse(
                            call: Call<DefaultResponse>,
                            response: Response<DefaultResponse>
                        ) {
                            Toast.makeText(context, "Registration success!", Toast.LENGTH_LONG)
                                .show()
                        }

                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            Toast.makeText(context, "Registration failed.", Toast.LENGTH_LONG).show()
                        }
                    })
            }  else{
                et_confirm_pw_register.error = "Passwords do not match"
                et_confirm_pw_register.requestFocus()
                return@setOnClickListener
            }
        }

        init(view)
    }

    private fun init(view: View) {
        var spinnerArray = arrayOf(
            SpinnerRegistration.Female.toString(),
            SpinnerRegistration.Male.toString(),
            SpinnerRegistration.Others.toString()
        )

        val arrayCategoryAdapter =
            ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, spinnerArray)
        spinner_salutation.adapter = arrayCategoryAdapter

        spinner_salutation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d("Spinner Data", "msg: Selected Type: ${spinnerArray[position]}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //do nothing
            }
        }
    }
}
