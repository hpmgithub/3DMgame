package com.jash.dmgame.entities;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "TYPE_ENTITY".
 */
public class TypeEntity {

    private Long id;
    private String typeName;

    public TypeEntity() {
    }

    public TypeEntity(Long id) {
        this.id = id;
    }

    public TypeEntity(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
