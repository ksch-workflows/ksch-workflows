package org.openehr.rm.data_types;

/**
 * A text item whose value must be the rubric from a controlled terminology, the key (i.e. the 'code') of which is
 * the defining_code attribute. In other words: a DV_CODED_TEXT is a combination of a CODE_PHRASE (effectively a code)
 * and the rubric of that term, from a terminology service, in the language in which the data were authored.
 * <p>
 * Since DV_CODED_TEXT is a subtype of DV_TEXT, it can be used in place of it, effectively allowing the type DV_TEXT
 * to mean a text item, which may optionally be coded.
 * <p>
 * Misuse: If the intention is to represent a term code attached in some way to a fragment of plain text,
 * DV_CODED_TEXT should not be used; instead use a DV_TEXT and a TERM_MAPPING to a CODE_PHRASE.
 *
 * @see <a href="https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_coded_text_class">specifications.openehr.org</a>
 */
public class DV_CODED_TEXT extends DV_TEXT {

    private final CODE_PHRASE defining_code;

    /**
     * @param value see {@link DV_TEXT#getValue()}
     */
    public DV_CODED_TEXT(CODE_PHRASE defining_code, String value) {
        super(value);

        this.defining_code = defining_code;
    }

    /**
     * @return The term of which the value attribute is the textual rendition (i.e. rubric).
     */
    public CODE_PHRASE getDefiningCode() {
        return defining_code;
    }
}
