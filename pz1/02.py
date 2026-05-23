print("Програма для обчислення середнього значення .")

count = int(input("Скільки чисел ви хочете ввести? "))
sum = 0

for i in range(count):
    current = float(input(f"Введіть число {i + 1}: "))
    sum += current

avg = sum / count

print("Середнє значення:", avg)
