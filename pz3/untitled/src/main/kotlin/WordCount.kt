import javax.swing.*
import java.io.File

fun main() {
    SwingUtilities.invokeLater {
        WordCounter()
    }
}

class WordCounter : JFrame() {

    private val resultLabel = JLabel("Оберіть файл")

    init {
        title = "Підрахунок слів у файлі"
        setSize(400, 200)
        defaultCloseOperation = EXIT_ON_CLOSE
        layout = null
        isResizable = false
        setLocationRelativeTo(null)

        val button = JButton("Вибрати файл")
        button.setBounds(120, 40, 160, 30)

        resultLabel.setBounds(50, 100, 300, 30)

        button.addActionListener {
            chooseFile()
        }

        add(button)
        add(resultLabel)

        isVisible = true
    }

    private fun chooseFile() {
        val chooser = JFileChooser()

        val result = chooser.showOpenDialog(this)

        if (result == JFileChooser.APPROVE_OPTION) {
            val file = chooser.selectedFile
            val count = countWords(file)

            resultLabel.text = "Слів у файлі: $count"
        }
    }

    private fun countWords(file: File): Int {
        return file.readText()
            .split(Regex("\\s+"))
            .filter { it.isNotBlank() }
            .size
    }
}