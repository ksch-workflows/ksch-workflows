package org.openehr.rm.data_types;

import org.openehr.base.base_types.TERMINOLOGY_ID;

/**
 * A fully coordinated (i.e. all coordination has been performed) term from a terminology service (as distinct from
 * a particular terminology).
 *
 * @see <a href="https://specifications.openehr.org/releases/RM/latest/data_types.html#_code_phrase_class">specifications.openehr.org</a>
 */
public class CODE_PHRASE {

    private final TERMINOLOGY_ID terminology_id;

    private final String code_string;

    /**
     * @param terminology_id see {@link CODE_PHRASE#getTerminologyId()}
     * @param code_string see {@link CODE_PHRASE#getCodeString()}
     */
    public CODE_PHRASE(TERMINOLOGY_ID terminology_id, String code_string) {
        this.terminology_id = terminology_id;
        this.code_string = code_string;
    }

    /**
     * @return Identifier of the distinct terminology from which the code_string (or its elements) was extracted.
     */
    public TERMINOLOGY_ID getTerminologyId() {
        return terminology_id;
    }

    /**
     * @return The key used by the terminology service to identify a concept or coordination of concepts. This string
     * is most likely parsable inside the terminology service, but nothing can be assumed about its syntax outside
     * that context.
     */
    public String getCodeString() {
        return code_string;
    }
}
