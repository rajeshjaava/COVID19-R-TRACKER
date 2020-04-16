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
"day",
"summary",
"regional"
})
public class Datum {

@JsonProperty("day")
private String day;
@JsonProperty("summary")
private Summary summary;
@JsonProperty("regional")
private List<Regional> regional = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("day")
public String getDay() {
return day;
}

@JsonProperty("day")
public void setDay(String day) {
this.day = day;
}

@JsonProperty("summary")
public Summary getSummary() {
return summary;
}

@JsonProperty("summary")
public void setSummary(Summary summary) {
this.summary = summary;
}

@JsonProperty("regional")
public List<Regional> getRegional() {
return regional;
}

@JsonProperty("regional")
public void setRegional(List<Regional> regional) {
this.regional = regional;
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
return new ToStringBuilder(this).append("day", day).append("summary", summary).append("regional", regional).append("additionalProperties", additionalProperties).toString();
}

}
