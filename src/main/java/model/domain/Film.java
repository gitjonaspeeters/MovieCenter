package model.domain;

public class Film {
    private String naam;
    private String genre;
    private int rating;
    private int duur;

    public Film(String naam, String genre, int rating, int duur) {
        setNaam(naam);
        setGenre(genre);
        setRating(rating);
        setDuur(duur);
    }

    public Film() {

    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if( naam == null || naam.isBlank()){
            throw new IllegalArgumentException("Vul correcte naam in.");
        }
        this.naam = naam;
    }

    public void setGenre(String genre) {
        if(genre == null || genre.isBlank()){
            throw new IllegalArgumentException("genre kan niet leeg zijn");
        }
        this.genre = genre;
    }

    public void setRating(int rating) {
        if(rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Geef juiste rating.");
        }
        this.rating = rating;
    }

    public void setDuur(int duur) {
        if(duur < 0 ) {
            throw new IllegalArgumentException("Geef juiste duur(in minuten).");
        }
        this.duur = duur;
    }

    public String getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    public int getDuur() {
        return duur;
    }
}
