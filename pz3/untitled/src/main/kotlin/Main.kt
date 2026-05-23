import javax.swing.*

fun main() {

    SwingUtilities.invokeLater {

        val frame = JFrame("Множення")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(300, 220)
        frame.layout = null
        frame.isResizable = false

        val panel = JPanel()
        panel.layout = null
        panel.setBounds(0, 0, 300, 220)

        val firstField = JTextField()
        val secondField = JTextField()
        val button = JButton("Помножити")
        val resultLabel = JLabel("Результат:")

        val firstLabel = JLabel("Перше число:")
        val secondLabel = JLabel("Друге число:")

        firstLabel.setBounds(20, 20, 120, 20)
        firstField.setBounds(140, 20, 120, 20)

        secondLabel.setBounds(20, 60, 120, 20)
        secondField.setBounds(140, 60, 120, 20)

        button.setBounds(20, 100, 120, 25)
        resultLabel.setBounds(20, 140, 200, 25)

        button.addActionListener {
            try {
                val a = firstField.text.toDouble()
                val b = secondField.text.toDouble()
                resultLabel.text = "Результат: ${a * b}"
            } catch (e: Exception) {
                resultLabel.text = "Помилка!"
            }
        }
        panel.add(firstLabel)
        panel.add(firstField)
        panel.add(secondLabel)
        panel.add(secondField)
        panel.add(button)
        panel.add(resultLabel)

        frame.add(panel)
        frame.isVisible = true
    }
}