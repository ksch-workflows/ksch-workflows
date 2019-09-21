package ksch.rm.ehr;

import lombok.Getter;

import java.util.List;

@Getter
public class CareEntry extends ENTRY {

    private Object subject;

    private List<Object> participations;
}
