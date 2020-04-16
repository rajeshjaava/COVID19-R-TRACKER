package com.covid19.rtracker.models;

import java.util.HashMap;
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
"total",
"confirmedCasesIndian",
"confirmedCasesForeign",
"discharged",
"deaths",
"confirmedButLocationUnidentified"
})
public class Summary {

@JsonProperty("total")
private Integer total;
@JsonProperty("confirmedCasesIndian")
private Integer confirmedCasesIndian;
@JsonProperty("confirmedCasesForeign")
private Integer confirmedCasesForeign;
@JsonProperty("discharged")
private Integer discharged;
@JsonProperty("deaths")
private Integer deaths;
@JsonProperty("confirmedButLocationUnidentified")
private Integer confirmedButLocationUnidentified;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("total")
public Integer getTotal() {
return total;
}

@JsonProperty("total")
public void setTotal(Integer total) {
this.total = total;
}

@JsonProperty("confirmedCasesIndian")
public Integer getConfirmedCasesIndian() {
return confirmedCasesIndian;
}

@JsonProperty("confirmedCasesIndian")
public void setConfirmedCasesIndian(Integer confirmedCasesIndian) {
this.confirmedCasesIndian = confirmedCasesIndian;
}

@JsonProperty("confirmedCasesForeign")
public Integer getConfirmedCasesForeign() {
return confirmedCasesForeign;
}

@JsonProperty("confirmedCasesForeign")
public void setConfirmedCasesForeign(Integer confirmedCasesForeign) {
this.confirmedCasesForeign = confirmedCasesForeign;
}

@JsonProperty("discharged")
public Integer getDischarged() {
return discharged;
}

@JsonProperty("discharged")
public void setDischarged(Integer discharged) {
this.discharged = discharged;
}

@JsonProperty("deaths")
public Integer getDeaths() {
return deaths;
}

@JsonProperty("deaths")
public void setDeaths(Integer deaths) {
this.deaths = deaths;
}

@JsonProperty("confirmedButLocationUnidentified")
public Integer getConfirmedButLocationUnidentified() {
return confirmedButLocationUnidentified;
}

@JsonProperty("confirmedButLocationUnidentified")
public void setConfirmedButLocationUnidentified(Integer confirmedButLocationUnidentified) {
this.confirmedButLocationUnidentified = confirmedButLocationUnidentified;
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
return new ToStringBuilder(this).append("total", total).append("confirmedCasesIndian", confirmedCasesIndian).append("confirmedCasesForeign", confirmedCasesForeign).append("discharged", discharged).append("deaths", deaths).append("confirmedButLocationUnidentified", confirmedButLocationUnidentified).append("additionalProperties", additionalProperties).toString();
}

}
