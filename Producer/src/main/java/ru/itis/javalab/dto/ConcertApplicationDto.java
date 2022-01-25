package ru.itis.javalab.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConcertApplicationDto {
    private String institute;
    private String concertDate;
    private String cultorgName;
    private String cultorgSurname;
    private String cultorgPatronymic;
    private String cultorgClass;
    private String cultorgPhone;
    private String producerName;
    private String producerSurname;
    private String producerPatronymic;
    private String producerClass;
    private String producerPhone;
    private String deputyName;
    private String deputySurname;
    private String deputyPatronymic;
    private String deputyPhone;
    private String dateInTheSmallHall;
    private String timeInTheSmallHall;
    private String dateInTheBigHall;
    private String timeInTheBigHall;
}
