package pl.comp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.comp.model.annotations.ExcludeFromJacocoGeneratedReport;
import pl.comp.model.exceptions.CloneException;

public abstract class SudokuFields implements Serializable, Cloneable {
    public static final int SIZE = 9;

    protected List<SudokuField> fields;

    public SudokuFields(List<SudokuField> fields) {
        this.fields = fields;
    }

    public Boolean verify() {

        // Check if rules apply
        for (int i = 0; i < SIZE; i++) {
            final int value = fields.get(i).getFieldValue();

            // We check with next indexes.
            for (int j = i + 1; j < SIZE; j++) {
                final int otherValueInList = fields.get(j).getFieldValue();
                if (value == otherValueInList) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof SudokuFields that)) {
            return false;
        }

        return new EqualsBuilder().append(fields, that.fields).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(fields).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("fields", fields)
            .toString();
    }

    @ExcludeFromJacocoGeneratedReport
    protected List<SudokuField> cloneFields() throws CloneException {
        try {
            List<SudokuField> newList = new ArrayList<SudokuField>();
            for (SudokuField field : fields) {
                newList.add(field.clone());
            }
            return newList;
        } catch (CloneNotSupportedException exception) {
            throw new CloneException("exception.clone", exception);
        }
    }

}
