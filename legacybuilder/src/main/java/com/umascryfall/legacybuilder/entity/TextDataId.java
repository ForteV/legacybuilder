package com.umascryfall.legacybuilder.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TextDataId implements Serializable {

    private int category;
    
    // index is a reserved keyword in some SQL dialects, avoiding @Column(name="index") issues if possible, but map it anyway.
    private int indexId;

    public TextDataId() {}

    public TextDataId(int category, int indexId) {
        this.category = category;
        this.indexId = indexId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getIndexId() {
        return indexId;
    }

    public void setIndexId(int indexId) {
        this.indexId = indexId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextDataId that = (TextDataId) o;
        return category == that.category && indexId == that.indexId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, indexId);
    }
}
