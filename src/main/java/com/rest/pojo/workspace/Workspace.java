package com.rest.pojo.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;

//@JsonInclude(JsonInclude.Include.NON_NULL)   //<-- it include only non-Null properties in the Serialization and
                                             // De-Serialization process, but if we have any primitive  like
                                              // int x;   as default value if x=0; this will be included

@JsonInclude(JsonInclude.Include.NON_DEFAULT)  //<-- as above explain here the properties with Default values are
               // also not included.

//@JsonIgnoreProperties(value ="x")         //<- with this we can ignore the property from class field also
@JsonIgnoreProperties(value ="id_new", allowSetters = true)  //<- "allowSetters" or "allowGetters" we can allow this
public class Workspace {                                // property only for Serialization or Deserialization process

    private String id;
    private String name;
    private String type;
    private String description;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)  //   we can use this annotation on properties level also
    private int i;                                  //<-- this field i use only for showing @Annotation purpose

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private HashMap<String, String> maHashMap;       //<-- if this HashMap is empty then it this property will not
                                                   // in the Serialization and De-Serialization process

    @JsonIgnore             // <-- this @Annotation will ignore this field in Serialization and De-Serialization process
    private int id_new;

    public Workspace() {
    }

    public Workspace(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
