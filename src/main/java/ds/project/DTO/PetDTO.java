package ds.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {
    private int petID;
    private String name;
    private String species;
    private String breed;
    private int age;
    private char sex;
    private float weight;
    private int hostID;
}
