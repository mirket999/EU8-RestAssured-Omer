package com.cydeo.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RegionPOJO_Task {
    @JsonProperty("region_id")
    private int regionId;
    @JsonProperty("region_name")
    private String regionName;
    private List<LinkPojo_Task> links;

}
