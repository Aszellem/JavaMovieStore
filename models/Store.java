package models;

import java.util.ArrayList;

public class Store {
    ArrayList<Movie> movies;

    public Store() {
        this.movies = new ArrayList<Movie>();
    }

    public Movie getMovie(int index) {
        return new Movie(movies.get(index));
    }

    public void setMovie(int index, Movie movie) {
        this.movies.set(index, new Movie(movie));
    }

    public void addMovie(Movie movie) {
        movies.add(new Movie(movie));
    }

    public void action(String movieName, String action) {
        if(movies.isEmpty()){
            throw new IllegalStateException("The store is not in a valid state to call the action method.");
        }
        if(!(action.equals("sell") || action.equals("rent") || action.equals("return"))){
            throw new IllegalArgumentException("");
        }
        if(movieName == null || movieName.isBlank()){
            throw new IllegalArgumentException("The name cannot be blank");
        }
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getName().equals(movieName)) {
                switch (action) {
                    case "sell":
                        movies.remove(i);
                        if (!(movies.get(i).isAvailable())) {
                            throw new IllegalStateException("Cannot sell movie that was rented out");
                        }
                        break;
                    case "rent":
                        movies.get(i).setAvailable(false);
                        break;
                    case "return":
                        movies.get(i).setAvailable(true);
                        break;
                }
            }
        }
    }

    public Movie getMovie(String name){
        for (int i = 0; i < movies.size(); i++) {
            if(movies.get(i).getName().equals(name)){
                return new Movie(movies.get(i));
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < this.movies.size(); i++) {
            temp += this.movies.get(i).toString();
            temp += "\n\n";
        }
        return temp;
    }

    

}
