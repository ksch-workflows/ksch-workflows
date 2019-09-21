package ksch.rm.ehr;

import ksch.rm.common.generic.Participation;

import java.util.List;

/**
 * The abstract parent of all ENTRY subtypes. An ENTRY is the root of a logical item of hard clinical information
 * created in the clinical statement context, within a clinical session. There can be numerous such contexts in a
 * clinical session. Observations and other Entry types only ever document information captured/created in the event
 * documented by the enclosing Composition.
 *
 * An ENTRY is also the minimal unit of information any query should return, since a whole ENTRY (including subparts)
 * records spatial structure, timing information, and contextual information, as well as the subject and generator of
 * the information.
 */
public class ENTRY {

    /**
     * Other participations at ENTRY level.
     */
    private List<Participation> otherParticipations;




}
