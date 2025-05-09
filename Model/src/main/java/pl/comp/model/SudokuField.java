package pl.comp.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.comp.model.annotations.ExcludeFromJacocoGeneratedReport;
import pl.comp.model.exceptions.CloneException;
import pl.comp.model.exceptions.IncorrectValueException;

public class SudokuField implements Serializable, Comparable<SudokuField>, Cloneable {
    private int value;

    public int getFieldValue() {
        return this.value;
    }

    public void setFieldValue(int value) {
        if (value < 0 || value > 9) {
            throw new IncorrectValueException("exception.incorrect_value");
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SudokuField that)) {
            return false;
        }
        return new EqualsBuilder().append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("value", value)
            .toString();
    }

    @Override
    public int compareTo(SudokuField other) throws NullPointerException {
        return value - other.value;
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public SudokuField clone() throws CloneException {
        try {
            return (SudokuField) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new CloneException("exception.clone", exception);
        }
    }
}
