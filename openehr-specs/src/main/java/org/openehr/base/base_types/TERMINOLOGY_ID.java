package org.openehr.base.base_types;

/**
 * @see <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_terminology_id_class">specifications.openehr.org</a>
 */
public class TERMINOLOGY_ID extends OBJECT_ID {

    public TERMINOLOGY_ID(String value) {
        super(value);
    }

    public String toString() {
        return this.getValue();
    }
}
