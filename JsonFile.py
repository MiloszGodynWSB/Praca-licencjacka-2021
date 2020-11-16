import json

class JsonFile:
    def open(self, location):
        with open(location, encoding='utf-8-sig') as file:
            self.data = json.load(file)
    
    def getImdbID(self):
        return self.data["imdb_id"]
    
    def getTmdbID(self):
        return self.data["id"]
    
    def getGenres(self):
        returnGenres = ""
        prevName = []
        self.repeatNames = 0
        for genre in self.data["genres"]:
            #This can be replaced with a dictionary
            if genre["name"] not in prevName:
                if genre["name"] == "Science Fiction":
                    returnGenres = returnGenres + "ScienceFiction" + ", "
                elif genre["name"] == "TV Movie":
                    returnGenres = returnGenres + "TVMovie" + ", "
                elif genre["name"] == "Action & Adventure":
                    returnGenres = returnGenres + "ActionAdventure" + ", "
                elif genre["name"] == "SciFiFantasy":
                    returnGenres = returnGenres + "ActionAdventure" + ", "
                elif genre["name"] == "War & Politics":
                    returnGenres = returnGenres + "War" + ", "
                else:
                    returnGenres = returnGenres + genre["name"] + ", "
            else:
                self.repeatNames = self.repeatNames + 1
            prevName.append(genre["name"])
        return returnGenres.rstrip(', ')
    
    def getGenresCount(self):
        return len(self.data["genres"])
    
    def getGenresCountString(self):
        genreCountStr = ""
        for x in range(self.getGenresCount() - self.repeatNames):
            genreCountStr = genreCountStr + "1, "
        return genreCountStr.rstrip(', ')