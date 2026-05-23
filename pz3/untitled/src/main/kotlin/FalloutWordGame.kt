import javax.swing.*
import java.awt.*
import java.io.File

class FalloutWordGame : JFrame() {

    private var words = listOf<String>()
    private lateinit var secret: String
    private var attempts = 5
    private var gameOver = false

    private val logArea = JTextArea()
    private val attemptsLabel = JLabel("Спроби: $attempts")
    private val listPanel = JPanel()

    init {
        title = "Fallout Word Hack"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(520, 380)
        setLocationRelativeTo(null)
        isResizable = false

        layout = BorderLayout(10, 10)

        (contentPane as JPanel).border = BorderFactory.createEmptyBorder(10, 10, 10, 10)

        loadWords()

        listPanel.layout = BoxLayout(listPanel, BoxLayout.Y_AXIS)

        createButtons()

        logArea.isEditable = false
        logArea.font = Font("Monospaced", Font.PLAIN, 12)

        val scroll = JScrollPane(logArea)

        val right = JPanel(BorderLayout())

        val top = JPanel(FlowLayout(FlowLayout.LEFT))
        val restart = JButton("Restart")

        restart.addActionListener { restartGame() }

        top.add(attemptsLabel)
        top.add(restart)

        right.add(top, BorderLayout.NORTH)
        right.add(scroll, BorderLayout.CENTER)

        add(JScrollPane(listPanel), BorderLayout.WEST)
        add(right, BorderLayout.CENTER)

        isVisible = true
    }

    private fun loadWords() {
        val file = File("words.txt")

        words = file.readLines()
                .map { it.trim().uppercase() }
                .filter { it.isNotEmpty() }
                .shuffled()
                .take(10)

        secret = words.random()
    }

    private fun createButtons() {
        listPanel.removeAll()

        for (word in words) {
            val btn = JButton(word)

            btn.maximumSize = Dimension(180, 32)
            btn.alignmentX = CENTER_ALIGNMENT

            btn.addActionListener {
                if (gameOver || attempts == 0) return@addActionListener

                attempts--
                val match = count(word)

                logArea.append("$word → $match/${secret.length}\n")
                attemptsLabel.text = "Спроби: $attempts"

                btn.isEnabled = false

                if (word == secret) end("ВИ ВИГРАЛИ")
                else if (attempts == 0) end("ВИ ПРОГРАЛИ $secret")
            }

            listPanel.add(btn)
            listPanel.add(Box.createRigidArea(Dimension(0, 5)))
        }

        listPanel.revalidate()
        listPanel.repaint()
    }

    private fun restartGame() {
        attempts = 5
        gameOver = false
        logArea.text = ""
        attemptsLabel.text = "Спроби: $attempts"

        loadWords()
        createButtons()
    }

    private fun count(word: String): Int {
        var count = 0

        val len = minOf(word.length, secret.length)

        for (i in 0 until len) {
            if (word[i] == secret[i]) {
                count++
            }
        }
        return count
    }

    private fun end(text: String) {
        gameOver = true
        logArea.append("\n$text\n")
    }
}

fun main() {
    SwingUtilities.invokeLater {
        FalloutWordGame()
    }
}