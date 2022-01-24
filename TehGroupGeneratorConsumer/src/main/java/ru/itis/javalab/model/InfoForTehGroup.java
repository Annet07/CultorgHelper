package ru.itis.javalab.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class InfoForTehGroup {

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
