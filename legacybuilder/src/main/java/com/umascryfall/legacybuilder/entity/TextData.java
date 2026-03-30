package com.umascryfall.legacybuilder.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "text_data")
public class TextData {

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "category", column = @Column(name = "category")),
        @AttributeOverride(name = "indexId", column = @Column(name = "\"index\""))
    })
    private TextDataId id;

    @Column(columnDefinition = "TEXT")
    private String text;

    public TextData() {}

    public TextDataId getId() {
        return id;
    }

    public void setId(TextDataId id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
