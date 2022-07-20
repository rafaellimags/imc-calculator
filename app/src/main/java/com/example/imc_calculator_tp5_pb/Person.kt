package com.example.imc_calculator_tp5_pb

import kotlin.math.pow

class Person {
    private lateinit var name: String;
    private var mass: Float = 0.0f
    private var height: Float = 0.0f
    private var bmi: Float = 0.0f

    private fun calculateBMI() {
        this.bmi = this.mass.div(this.height.pow(2))
    }

    fun getBMI(): Float {
        return this.bmi
    }

    fun setBodyParams(bParams: List<Float>) {
        this.mass = bParams[0]
        this.height = bParams[1]
        calculateBMI()
    }
}