from datetime import datetime

date_str = input("Введіть дату народження (дд.мм.рррр): ")

birth_date = datetime.strptime(date_str, "%d.%m.%Y")
today = datetime.now()

age = today.year - birth_date.year

if (today.month, today.day) < (birth_date.month, birth_date.day):
    age -= 1

print("Ваш вік:", age)