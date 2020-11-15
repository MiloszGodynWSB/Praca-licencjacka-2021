import DB as database
import JsonFile as file
import os, glob

# x = database.DB("localhost","root","Figo5252")
# x.connect()

fileToLoad = file.JsonFile()


# moviesLocation = "E:\\Licencjat\\archive\\movies\\"
# for filename in glob.glob(os.path.join(moviesLocation, "*.json")):
#     print(filename)
#     fileToLoad.open(filename)
#     imdbID = fileToLoad.getImdbID()
#     tmdbId = fileToLoad.getTmdbID()
#     print("IMDB ID: ", imdbID)
#     print("TMDB ID: ", tmdbId)
#     print()

moviesLocation = "E:\\Licencjat\\archive\\movies\\movie_100.json"

fileToLoad.open(moviesLocation)
genres = fileToLoad.getGenres()
print(genres)
genreCount = fileToLoad.getGenresCount()
print(genreCount)