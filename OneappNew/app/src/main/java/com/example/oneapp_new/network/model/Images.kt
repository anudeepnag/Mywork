package com.example.oneapp_new.network.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

data class Images(@SerializedName("base_url")val baseUrl: String)