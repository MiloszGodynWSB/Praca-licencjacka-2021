import DB as database
import JsonFile as file
import os, glob, threading

x = database.DB("localhost","root","passToChange")
x.connect()

moviesExcept = 0
seriesExcept = 0

def loadMovies():
    global moviesExcept
    fileToLoad = file.JsonFile()
    moviesLocation = "E:\\Licencjat\\archive\\movies\\"
    for moviesFilename in glob.glob(os.path.join(moviesLocation, "*.json")):
        try:
            fileToLoad.open(moviesFilename)
            if fileToLoad.getGenresCount() >= 1:
                imdbID = fileToLoad.getImdbID()
                tmdbId = fileToLoad.getTmdbID()
                genres = fileToLoad.getGenres()
                tables = "movieID, imdbID, " + genres 
                tables = tables.rstrip(', ')
                genreCountStr = str(tmdbId) + ", '" + str(imdbID) + "', " + fileToLoad.getGenresCountString()
                genreCountStr = genreCountStr.rstrip(', ')
                x.insert("movies", tables, genreCountStr)
        except:
            print("Excepted Movie")
            moviesExcept = moviesExcept + 1
            print()
            continue

def loadTV():
    global seriesExcept
    fileToLoad = file.JsonFile()
    seriesLocation = "E:\\Licencjat\\archive\\series\\"
    for seriesFilename in glob.glob(os.path.join(seriesLocation, "*.json")):
        try:
            fileToLoad.open(seriesFilename)
            if fileToLoad.getGenresCount() >= 1:
                tmdbId = fileToLoad.getTmdbID()
                genres = fileToLoad.getGenres()
                tables = "movieID, " + genres 
                tables = tables.rstrip(', ')
                genreCountStr = str(tmdbId) + ", " + fileToLoad.getGenresCountString()
                genreCountStr = genreCountStr.rstrip(', ')
                print(genreCountStr)
                x.insert("series", tables, genreCountStr)
        except:
            print("Excepted Series")
            seriesExcept = seriesExcept + 1
            print()
            continue



loadMovies()
loadTV()

print("Movies Except: ", moviesExcept)
print("Series Except: ", seriesExcept)