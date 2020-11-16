import mysql.connector


class DB:
    def __init__(self, host, user, password):
            self.host= host
            self.user= user
            self.password = password

    def connect(self):
        self.database = mysql.connector.connect(
            host = self.host,
            user = self.user,
            password = self.password
        )
        self.cursor = self.database.cursor()

    def insert(self, table, genres, values):
        
        sql = "insert into " + table + "(" + genres + ")" + " values (" + values + ")"
        print(sql)
        self.cursor.execute("use movieselector")
        self.cursor.execute(sql)
        self.database.commit()

        print(self.cursor.rowcount, "record inserted.")