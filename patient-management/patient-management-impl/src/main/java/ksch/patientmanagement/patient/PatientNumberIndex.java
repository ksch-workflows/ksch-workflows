package ksch.patientmanagement.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@TableGenerator(name = "patient_index_number", initialValue = 999)
class PatientNumberIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "patient_index_number")
    @Column(unique = true)
    private int index;

    @Override
    public String toString() {
        return String.valueOf(index);
    }
}
