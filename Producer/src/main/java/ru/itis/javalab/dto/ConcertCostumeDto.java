package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConcertCostumeDto {

    private String institute;
    private String concertDate;
    private String cultorgName;
    private String cultorgSurname;
    private String cultorgPatronymic;
    private String deputyName;
    private String deputySurname;
    private String deputyPatronymic;
}
