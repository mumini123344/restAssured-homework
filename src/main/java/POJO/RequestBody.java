package POJO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"bookingid", "lastname", "firstname", "totalprice", "depositpaid", "bookingdates", "additionalneeds"})
public class RequestBody {

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("totalprice")
    private int totalPrice;

    @JsonProperty("depositpaid")
    private boolean depositPaid;

    @JsonProperty("bookingdates")
    private Dates bookingDates;

    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    private String passportNo;

    @JsonIgnore
    private int salesPrice;

}