package org.openehr.base.base_types;

import lombok.RequiredArgsConstructor;

/**
 * Ancestor class of identifiers of informational objects. Ids may be completely meaningless,
 * in which case their only job is to refer to something, or may carry some information to do
 * with the identified object.
 * <p>
 * Object ids are used inside an object to identify that object. To identify another object in
 * another service, use an OBJECT_REF, or else use a UID for local objects identified by UID.
 * If none of the subtypes is suitable, direct instances of this class may be used.
 */
@RequiredArgsConstructor
public class OBJECT_ID {

    private final String value;

    /**
     * @return The value of the id in the form defined below.
     */
    public String getValue() {
        return value;
    }
}
