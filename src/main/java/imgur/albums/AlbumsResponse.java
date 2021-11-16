package imgur.albums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AlbumsResponse {
    @JsonProperty("data")
    public Data data;
    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("status")
    public Integer status;

    public class Data {
        @JsonProperty("id")
        public String id;
        @JsonProperty("deletehash")
        public String deletehash;
    }
}
