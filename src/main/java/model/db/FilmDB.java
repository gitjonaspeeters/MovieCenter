package model.db;

import model.domain.Film;
import java.util.ArrayList;

public class FilmDB {
    ArrayList<Film> databank= new ArrayList<>();



    public ArrayList<Film> getDatabank() {
        return databank;
    }

    //public ArrayList<Film> getDataBankMetFilter(vat tot){

        //New arraylist
        // if(databank.rating == ...) add in arraylist
        //return arraylist





    public void VoegToe(Film film){
        if(film ==null) throw  new IllegalArgumentException("Geef bestaande film");
        if(vind(film.getNaam()) != null) throw new IllegalArgumentException("Film bestaat al");
        databank.add(film);
    }

    public Film vind(String naam){
        if(naam == null || naam.isEmpty()) throw new IllegalArgumentException("");
        for(Film film : databank){
            if(film.getNaam().equals(naam)){
                return film;
            }

        }
        return null;
    }

    public Film getLangsteFilm(){
        Film result= new Film("(er zijn nog geen films)","dd",9, 0);
        for (Film j : databank){
            if(j.getDuur()> result.getDuur()){
                result = j;
            }
        }
        return result;
    }

    public void verwijderFilm(String naam){
        ArrayList<Film> p= new ArrayList<Film>();
        for (Film a:databank){
            if (a.getNaam().equals(naam)){
            }else {
                p.add(a);
            }
        }
        databank=p;
    }

    public void aanpassenFilm(Film oudefilm, Film nieuwefilm){
        int index = databank.indexOf(oudefilm);
        databank.get(index).setRating(nieuwefilm.getRating());
        databank.get(index).setDuur(nieuwefilm.getDuur());
        databank.get(index).setGenre(nieuwefilm.getGenre());

    }

    public ArrayList<Film> zoekFilm(String film) {
        if (film == null || film.isBlank()) throw new IllegalArgumentException("Vul een geldige titel in");
        ArrayList<Film> result = new ArrayList<>();
        for (int i = 0; i < databank.size(); i++) {
            if (film.equals(databank.get(i).getNaam())) {
                result.add(databank.get(i));
            }
        }if(result.size() == 0){
            throw new IllegalArgumentException("Film bestaat niet");
        }else {
            return result;
        }
    }

}





