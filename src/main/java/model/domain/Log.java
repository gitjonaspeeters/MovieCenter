package model.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Log {
    private Film film;
    private LocalDateTime dateTime;

    public Log(Film film, LocalDateTime dateTime) {
        this.film = film;
        this.dateTime = dateTime;
    }

    public Film getFilm() {
        return film;
    }

    public String getDate() {
        String result= "";
        result += dateTime.getYear() + " " + dateTime.getMonth() + " " +dateTime.getDayOfMonth();
        return result;
    }


    public String getTime() {
        return dateTime.getHour() + ":" + dateTime.getMinute();
    }
}
