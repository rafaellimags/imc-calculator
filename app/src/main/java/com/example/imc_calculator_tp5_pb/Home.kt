package com.example.imc_calculator_tp5_pb

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.*
import androidx.navigation.NavController
import androidx.navigation.Navigation

class Home : Fragment() {

    lateinit var editMass: Editable
    lateinit var editHeight: Editable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val btnCalcBMI = view.findViewById<Button>(R.id.btnCalcBMI)

        btnCalcBMI.setOnClickListener {

            val navController = Navigation.findNavController(view)
            editMass = view?.findViewById<EditText>(R.id.mass)?.text!!
            editHeight = view?.findViewById<EditText>(R.id.height)?.text!!

            if (validateFields(editMass, editHeight)) {

                val person = Person()
                val bodyParams: List<Float> = getEntryData(editMass, editHeight)

                person.setBodyParams(bodyParams)
                callPath(person.getBMI(), navController)
            } else {
                Toast.makeText(this.context, "Dados invalidos!", LENGTH_SHORT).show()
            }
        }
        return view
    }

    fun validateFields(mass: Editable, height: Editable): Boolean {
        return editMass.isNotBlank() && editHeight.isNotBlank()
    }

    fun getEntryData(mass: Editable, height: Editable): List<Float> {
        return convertdata(mass.toString(), height.toString())
    }

    fun convertdata(mass: String, height: String): List<Float> {
        var data: MutableList<Float> = mutableListOf(mass.toFloat(), height.toFloat())
        val bodyParams: List<Float> = data
        println("BP: $bodyParams")
        return bodyParams
    }

    fun callPath(bmi: Float, navController: NavController) {
        if (bmi < 16) {
            navController.navigate(R.id.action_initial_to_severeThinness)
        }
        if (bmi >= 16 && bmi < 17) {
            navController.navigate(R.id.action_initial_to_mildThinness)
        }
        if (bmi >= 17 && bmi < 18.5) {
            navController.navigate(R.id.action_initial_to_moderateThinness)
        }
        if (bmi >= 18.5 && bmi < 25) {
            navController.navigate(R.id.action_initial_to_normal)
        }
        if (bmi >= 25 && bmi < 30) {
            navController.navigate(R.id.action_initial_to_overweight)
        }
        if (bmi >= 30 && bmi < 35) {
            navController.navigate(R.id.action_initial_to_obeseClassOne)
        }
        if (bmi >= 35 && bmi < 40) {
            navController.navigate(R.id.action_initial_to_obeseClassTwo)
        }
        if (bmi > 40) {
            navController.navigate(R.id.action_initial_to_obeseClassThree)
        }
    }
}