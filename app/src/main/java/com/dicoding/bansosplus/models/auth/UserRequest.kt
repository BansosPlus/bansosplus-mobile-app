package com.dicoding.bansosplus.models.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserRequest {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("nik")
    @Expose
    var nik: String? = null

    @SerializedName("nokk")
    @Expose
    var noKk: String? = null

    @SerializedName("income")
    @Expose
    var income: String? = null

    @SerializedName("floor_area")
    @Expose
    var floorArea: String? = null

    @SerializedName("wall_quality")
    @Expose
    var wallQuality: String? = null

    @SerializedName("number_of_meals")
    @Expose
    var numberOfMeals: String? = null

    @SerializedName("fuel")
    @Expose
    var fuel: String? = null

    @SerializedName("education")
    @Expose
    var education: String? = null

    @SerializedName("total_asset")
    @Expose
    var totalAsset: String? = null

    @SerializedName("treatment")
    @Expose
    var treatment: String? = null

    @SerializedName("number_of_dependents")
    @Expose
    var numberOfDependents: String? = null
}