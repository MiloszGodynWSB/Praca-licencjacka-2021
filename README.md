# Licencjat - backend

## Uruchomienie projektu

### home

[localhost:8080](http://localhost:8080)

### Dodawanie użytkownika

Generowanie hasła z użyciem [BCrypt](https://bcrypt-generator.com).

Przykład:  
login: _user_  
hasło: _pass_


```sql
INSERT INTO movieselector.users (username, password, enabled) VALUES ('user', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', DEFAULT);
INSERT INTO movieselector.authorities (username, authority) VALUES ('user', 'ROLE_USER');
```

### baza danych

Baza musi spełniać następujące warunki:
- port: 3306
- login: root
- hasło: admin

[localhost:8080/movies/2](localhost:8080/movies/2) - przykład z szukaniem filmu z _movieID_ = 2.  Wówczas w konsoli zwróci coś takiego:
```
Movie{movieID=2, imdbID='tt0094675', action=0, adventure=0, comedy=1, crime=1, documentary=0, drama=1, family=0, fantasy=0, history=0, horror=0, music=0, mystery=0, romance=0, scienceFiction=0, tvMovie=0, thriller=0, war=0, western=0}
```

### miejsce na algorytmy

Obecnie miejscem na algorytmy jest:
```
src/main/java/pl/wsb/licencjat/recommendation
```

Generalnie na tym samym poziomie co folder _recommendation_ można tworzyć bez przeszkód inne foldery, jeśli będzie to wygodne.