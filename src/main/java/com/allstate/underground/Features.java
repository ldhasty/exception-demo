
package com.allstate.underground;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "geolookup",
    "conditions"
})
public class Features {

    @JsonProperty("geolookup")
    private Integer geolookup;
    @JsonProperty("conditions")
    private Integer conditions;
    @JsonProperty("hourly")
    private Integer hourly;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("geolookup")
    public Integer getGeolookup() {
        return geolookup;
    }

    @JsonProperty("geolookup")
    public void setGeolookup(Integer geolookup) {
        this.geolookup = geolookup;
    }

    @JsonProperty("conditions")
    public Integer getConditions() {
        return conditions;
    }

    @JsonProperty("conditions")
    public void setConditions(Integer conditions) {
        this.conditions = conditions;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonProperty("hourly")
    public Integer getHourly() {
        return hourly;
    }

    @JsonProperty("hourly")
    public void setHourly(Integer hourly) {
        this.hourly = hourly;
    }
}
