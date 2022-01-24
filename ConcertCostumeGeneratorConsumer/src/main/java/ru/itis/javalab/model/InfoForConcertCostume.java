package ru.itis.javalab.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InfoForConcertCostume {

    private String institute;
    private String concertDate;
    private String cultorgName;
    private String cultorgSurname;
    private String cultorgPatronymic;
    private String deputyName;
    private String deputySurname;
    private String deputyPatronymic;
}
