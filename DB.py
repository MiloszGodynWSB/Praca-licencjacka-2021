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

    def insert(self, values):
        
        sql = "insert into movies values ()"

        self.cursor.execute("use movieselector")
        self.cursor.execute(sql, val)
        self.database.commit()

        print(self.cursor.rowcount, "record inserted.")