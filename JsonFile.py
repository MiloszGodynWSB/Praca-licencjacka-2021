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
        self.doubleGenre = 0
        for genre in self.data["genres"]:
            #This can be replaced with a dictionary
            if genre["name"] not in prevName:
                if genre["name"] == "Science Fiction":
                    returnGenres = returnGenres + "ScienceFiction" + ", "
                elif genre["name"] == "TV Movie":
                    returnGenres = returnGenres + "TVMovie" + ", "
                elif genre["name"] == "Action & Adventure":
                    if "Adventure" not in prevName:
                        returnGenres = returnGenres + "Adventure" + ", "
                    else:
                        self.repeatNames = self.repeatNames + 1
                    if "Action" not in prevName:
                        returnGenres = returnGenres + "Action" + ", "
                    else:
                        self.repeatNames = self.repeatNames + 1
                    prevName.append("Action")
                    prevName.append("Adventure")
                    self.doubleGenre = self.doubleGenre + 1
                elif genre["name"] == "Sci-Fi & Fantasy":
                    if "Science Fiction" not in prevName:
                        returnGenres = returnGenres + "ScienceFiction" + ", "
                    else:
                        self.repeatNames = self.repeatNames + 1
                    if "Fantasy" not in prevName:
                        returnGenres = returnGenres + "Fantasy" + ", "
                    else:
                        self.repeatNames = self.repeatNames + 1
                    prevName.append("Science Fiction")
                    prevName.append("Fantasy")
                    self.doubleGenre = self.doubleGenre + 1
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
        for x in range(self.getGenresCount() - self.repeatNames + self.doubleGenre):
            genreCountStr = genreCountStr + "1, "
        return genreCountStr.rstrip(', ')