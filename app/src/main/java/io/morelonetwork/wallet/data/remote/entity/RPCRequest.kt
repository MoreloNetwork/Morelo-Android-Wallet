package io.morelonetwork.wallet.data.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class RPCRequest<T>(
    val jsonrpc: String = "2.0",
    val id: String = "morelonetwork",
    val method: String,
    val params: T?
)
