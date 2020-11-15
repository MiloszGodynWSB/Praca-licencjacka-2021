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
        for genre in self.data["genres"]:
            returnGenres = returnGenres + genre["name"] + ", "
        return returnGenres.rstrip(', ')
    def getGenresCount(self):
        return len(self.data["genres"])