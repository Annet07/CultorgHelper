package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TehGroupDto {

    private String institute;
    private String instituteInTheGenitive;
    private String startDate;
    private String startTime;
    private String finishDate;
    private String finishTime;
    private String responsibleName;
    private String responsibleSurname;
    private String responsiblePatronymic;
    private String responsiblePosition;
    private String directorName;
    private String directorSurname;
    private String directorPatronymic;
    private String today;
}
