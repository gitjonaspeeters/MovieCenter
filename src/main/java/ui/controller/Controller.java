package ui.controller;


import model.db.FilmDB;
import model.domain.Film;
import model.domain.Log;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    FilmDB database= new FilmDB();



    public Controller(){super();}


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcessRequest(request, response);
    }




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcessRequest(request,response);
    }
    private void doProcessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination;
        String command = request.getParameter("command");
        if(command == null){
            command = "home";
        }
        if(getCookieWithKey(request,"mode") != null){
            request.setAttribute("mode",getCookieWithKey(request,"mode").getValue());
        }else{
            request.setAttribute("mode", "family");
        }
        switch(command){
            case "overzicht":
                destination = showOverzicht(request);
                break;
            case "voegtoeForm":
                destination= getvoegToeForm(request);
                break;
            case "voegtoe":
                destination= getVoegtoe(request,response);
                break;
            case "voegtoepagina":
                destination= "voeg_toe.jsp";
                break;
            case "zoekenpagina":
                destination= "zoeken.jsp";
                break;
            case "verwijdercon":
                destination= "verwijdercon.jsp";
                break;
            case "naaraanpassen":
                destination= "Aanpassen.jsp";
                break;

            case "verwijder":
                destination= verwijder(request,response);
                break;
            case "zoekenerror":
                destination=getZoeken(request,response);
                break;
            case "journal":
                destination= logFilms(request,response);
                break;
            case "show":
                destination= SwitchCoockies(request, response);
                break;
            case "aanpassen":
                destination= aanpassen(request,response);
                break;
            default:
                destination = goHome(request);
        }
         request.getRequestDispatcher(destination).forward(request, response);
    }

    private String getvoegToeForm(HttpServletRequest request) {
        return "voeg_toe.jsp";
    }


    private String goHome(HttpServletRequest request) {

        request.setAttribute("Langstefilm",database.getLangsteFilm());
        Cookie cookie = getCookieWithKey(request, "theme");
        if (cookie == null || cookie.getValue().equals("normaal")) {
            return "index.jsp";
        } else {
            return "indexhorror.jsp";
        }
    }





    private String showOverzicht(HttpServletRequest request) {
        String destination;
        request.setAttribute("Films", database.getDatabank());
        request.setAttribute("Langstefilm", database.getLangsteFilm());
        destination= "overzicht.jsp";
        return destination;
    }

    private String logFilms(HttpServletRequest request, HttpServletResponse response){
        return "journalmetcoocki-es.jsp";
    }

    private void voegFilmInLog(Film film,HttpServletRequest request, HttpServletResponse response){
        HttpSession sessie = request.getSession();
        ArrayList<Log> opgezochteFilms;

        if(sessie.getAttribute("opgezochteFilms")== null){
            opgezochteFilms = new ArrayList<>();
        }else{
            opgezochteFilms =  (ArrayList<Log>) sessie.getAttribute("opgezochteFilms");
        }
        opgezochteFilms.add(new Log(film,LocalDateTime.now()));
        sessie.setAttribute("opgezochteFilms", opgezochteFilms);
    }

    private String zoeken(HttpServletRequest request, HttpServletResponse response) {
       return "zoeken.jsp";
    }


    private String SwitchCoockies(HttpServletRequest request, HttpServletResponse  response){
            String destination = null;
            Cookie cookie= getCookieWithKey(request, "mode");
            Cookie c  = null;

            if(cookie == null || cookie.getValue().equals("horror")){
                c= new Cookie("mode", "family");
                request.setAttribute("mode", "family");
                destination= goHome(request);
            }
            else if(cookie.getValue().equals("family")){
                c= new Cookie("mode", "love");
                request.setAttribute("mode" , "love");
                destination= goHome(request);
            }else if(cookie.getValue().equals("love")){
                c= new Cookie("mode", "scifi");
                request.setAttribute("mode" , "scifi");
                destination= goHome(request);
            }else{
                c= new Cookie("mode", "horror");
                request.setAttribute("mode" , "horror");
                destination= goHome(request);
            }
            response.addCookie(c);
            return  destination;
    }
    private Cookie getCookieWithKey(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies
        ) {
            if (cookie.getName().equals(key))
                return cookie;
        }
        return null;
    }


    private String showZoeken(HttpServletRequest request, HttpServletResponse response) {
        String destination;
        request.setAttribute("naam",database.zoekFilm("naam"));
        destination="overzicht.jsp";
        return destination;


    }

    private String verwijder( HttpServletRequest request, HttpServletResponse response) {
        String naam = request.getParameter("naam");
        database.verwijderFilm(naam);
        return showOverzicht(request);
    }

    private String aanpassen( HttpServletRequest request, HttpServletResponse response){
        ArrayList<String> errorsaanpassen = new ArrayList<String>();
        Film oudefilm= database.vind(request.getParameter("naam"));
        Film niewefilm = new Film();
        setGenre(niewefilm,request, errorsaanpassen);
        setRating(niewefilm,request, errorsaanpassen);
        setDuur(niewefilm,request, errorsaanpassen);
        if(errorsaanpassen.size() == 0){
            try{
                database.aanpassenFilm(oudefilm, niewefilm);
                return showOverzicht(request);
            }catch (IllegalArgumentException exc){
                request.setAttribute("errorsaanpassen", exc.getMessage());
                return "Aanpassen.jsp";
            }
        }else{
            request.setAttribute("errorsaanpassen",errorsaanpassen);
            return "Aanpassen.jsp";
        }
    }




    private String getVoegtoe(HttpServletRequest request,  HttpServletResponse response){
        ArrayList<String> errors = new ArrayList<String>();
        Film film = new Film("null","null",2,2);
        setNaam(film,request,errors);
        setGenre(film,request,errors);
        setRating(film,request,errors);
        setDuur(film,request,errors);
        if(errors.size() == 0){
            try{
                database.VoegToe(film);
                return showOverzicht(request);
            }catch (IllegalArgumentException exc){
                request.setAttribute("errors", exc.getMessage());
                return "voeg_toe.jsp";
            }
        }else{
            request.setAttribute("errors",errors);
            return "voeg_toe.jsp";
        }

    }
    private String getZoeken(HttpServletRequest request,  HttpServletResponse response){
        String titel= request.getParameter("titel");
        try {
            request.setAttribute("Films",database.zoekFilm(titel));
            voegFilmInLog(database.vind(titel), request,response);
            return "overzicht.jsp";
        }
        catch (IllegalArgumentException exc) {
            request.setAttribute("errors", exc.getMessage());
            return "zoeken.jsp";
        }
    }

    private void setNaam(Film film,HttpServletRequest request,  ArrayList<String> errors){
        String naam = request.getParameter("naam");
        try {
            film.setNaam(naam);
            request.setAttribute("naamClass","has-succes");
            request.setAttribute("naamPreviousValue", naam);
        }catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
            request.setAttribute("naamClass","has-error");
        }
    }

    private void setGenre(Film film,HttpServletRequest request,  ArrayList<String> errors){
        String genre = request.getParameter("genre");
        try {
            film.setGenre(genre);
            request.setAttribute("genreClass","has-succes");
            request.setAttribute("genrePreviousValue", genre);
        }catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
            request.setAttribute("genreClass","has-error");
        }
    }

    private void setRating(Film film,HttpServletRequest request,  ArrayList<String> errors){
        int rating;
        if(request.getParameter("Rating").isBlank()){
            rating=-1;
        }else{
            rating = Integer.parseInt(request.getParameter("Rating"));
        }try{
            film.setRating(rating);
            request.setAttribute("ratingClass","has-succes");
            request.setAttribute("ratingPreviousValue", rating);
        }catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
            request.setAttribute("ratingClass","has-error");
        }

        }

    private void setDuur(Film film,HttpServletRequest request,  ArrayList<String> errors){
        int duur;
        if(request.getParameter("Duur").isBlank()){
            duur=-1;
        }else{
            duur = Integer.parseInt(request.getParameter("Duur"));
        }try{
            film.setDuur(duur);
            request.setAttribute("duurClass","has-succes");
            request.setAttribute("duurPreviousValue", duur);
        }catch (IllegalArgumentException exc){
            errors.add(exc.getMessage());
            request.setAttribute("duurClass","has-error");

        }

    }
    }




