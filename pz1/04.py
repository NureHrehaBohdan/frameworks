class Book:
    def __init__(self, title, author, year):
        self.title = title
        self.author = author
        self.year = year

book = Book("Ми", "Євген Замятін", 1924)

print("Назва:", book.title)
print("Автор:", book.author)
print("Рік:", book.year)