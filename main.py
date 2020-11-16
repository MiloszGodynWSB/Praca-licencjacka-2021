import DB as database
import JsonFile as file
import os, glob

x = database.DB("localhost","root","Figo5252")
x.connect()

fileToLoad = file.JsonFile()

#populate movies table
moviesLocation = "E:\\Licencjat\\archive\\movies\\"
for moviesFilename in glob.glob(os.path.join(moviesLocation, "*.json")):
    fileToLoad.open(moviesFilename)
    imdbID = fileToLoad.getImdbID()
    tmdbId = fileToLoad.getTmdbID()
    genres = fileToLoad.getGenres()
    print(genres)
    tables = "movieID, imdbID, " + genres 
    tables = tables.rstrip(', ')
    print(tables)
    genreCountStr = str(tmdbId) + ", '" + str(imdbID) + "', " + fileToLoad.getGenresCountString()
    genreCountStr = genreCountStr.rstrip(', ')
    print(genreCountStr)
    x.insert("movies", tables, genreCountStr)

#populate tv series table
seriesLocation = "E:\\Licencjat\\archive\\series\\"
for seriesFilename in glob.glob(os.path.join(seriesLocation, "*.json")):
    fileToLoad.open(seriesFilename)
    imdbID = fileToLoad.getImdbID()
    tmdbId = fileToLoad.getTmdbID()
    genres = fileToLoad.getGenres()
    print(genres)
    tables = "movieID, imdbID, " + genres 
    tables = tables.rstrip(', ')
    print(tables)
    genreCountStr = str(tmdbId) + ", '" + str(imdbID) + "', " + fileToLoad.getGenresCountString()
    genreCountStr = genreCountStr.rstrip(', ')
    print(genreCountStr)
    x.insert("series", tables, genreCountStr)