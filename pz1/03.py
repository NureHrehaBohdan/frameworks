from datetime import datetime

year = int(input("Введіть рік народження: "))
age = datetime.now().year - year

print("Ваш вік:", age)