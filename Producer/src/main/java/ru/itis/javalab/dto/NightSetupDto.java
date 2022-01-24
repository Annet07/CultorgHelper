package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NightSetupDto {

    private String institute;
    private String concertDate;
    private String startDate;
    private String startTime;
    private String finishDate;
    private String finishTime;
    private String responsibleName;
    private String responsibleSurname;
    private String responsiblePatronymic;
    private String responsiblePosition;
    private String responsiblePhone;
    private String cultorgName;
    private String cultorgSurname;
    private String cultorgPatronymic;
    private String deputyName;
    private String deputySurname;
    private String deputyPatronymic;
    private String instituteInTheGenitive;

}
