package com.covid19.rtracker.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"success",
"data",
"lastRefreshed",
"lastOriginUpdate"
})
public class Indiadata {

@JsonProperty("success")
private Boolean success;
@JsonProperty("data")
private List<Datum> data = null;
@JsonProperty("lastRefreshed")
private String lastRefreshed;
@JsonProperty("lastOriginUpdate")
private String lastOriginUpdate;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("success")
public Boolean getSuccess() {
return success;
}

@JsonProperty("success")
public void setSuccess(Boolean success) {
this.success = success;
}

@JsonProperty("data")
public List<Datum> getData() {
return data;
}

@JsonProperty("data")
public void setData(List<Datum> data) {
this.data = data;
}

@JsonProperty("lastRefreshed")
public String getLastRefreshed() {
return lastRefreshed;
}

@JsonProperty("lastRefreshed")
public void setLastRefreshed(String lastRefreshed) {
this.lastRefreshed = lastRefreshed;
}

@JsonProperty("lastOriginUpdate")
public String getLastOriginUpdate() {
return lastOriginUpdate;
}

@JsonProperty("lastOriginUpdate")
public void setLastOriginUpdate(String lastOriginUpdate) {
this.lastOriginUpdate = lastOriginUpdate;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
return new ToStringBuilder(this).append("success", success).append("data", data).append("lastRefreshed", lastRefreshed).append("lastOriginUpdate", lastOriginUpdate).append("additionalProperties", additionalProperties).toString();
}

}
