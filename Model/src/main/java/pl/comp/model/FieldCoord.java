package pl.comp.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FieldCoord {

    private final int colNumber;
    private final int rowNumber;

    public FieldCoord(final int colNumber, int rowNumber) {
        this.colNumber = colNumber;
        this.rowNumber = rowNumber;
    }

    public int getX() {
        return colNumber;
    }

    public int getY() {
        return rowNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("X", colNumber)
            .append("Y", rowNumber)
            .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        FieldCoord that = (FieldCoord) other;
        return new EqualsBuilder()
            .append(colNumber, that.colNumber)
            .append(rowNumber, that.rowNumber)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(colNumber)
            .append(rowNumber)
            .toHashCode();
    }
}


