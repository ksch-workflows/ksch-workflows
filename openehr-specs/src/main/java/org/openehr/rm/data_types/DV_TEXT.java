package org.openehr.rm.data_types;

import lombok.RequiredArgsConstructor;

/**
 * A text item, which may contain any amount of legal characters arranged as e.g. words, sentences etc (i.e. one
 * DV_TEXT may be more than one word). Visual formatting and hyperlinks may be included via markdown.
 *
 * @see <a href="https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_text_class">specifications.openehr.org</a>
 */
@RequiredArgsConstructor
public class DV_TEXT extends DATA_VALUE {

    private final String value;

    /**
     * @return Displayable rendition of the item, regardless of its underlying structure. For DV_CODED_TEXT, this is
     * the rubric of the complete term as provided by the terminology service.
     */
    public String getValue() {
        return value;
    }
}
