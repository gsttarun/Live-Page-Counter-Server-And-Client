import com.google.gson.annotations.SerializedName

data class WSMessage(
    @SerializedName("type")
    val type: MessageType,

    @SerializedName("id")
    val id: String
)