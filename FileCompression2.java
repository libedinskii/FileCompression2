import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
public class FileCompression2 {
    public static void compressFile(String sourceFilePath, String destinationFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath))) {
            Set<String> uniqueLines = new HashSet<>();
            String line;
            int duplicateCount = 0;

            while ((line = reader.readLine()) != null) {
                if (uniqueLines.contains(line)) {
                    duplicateCount++;
                } else {
                    uniqueLines.add(line);
                    writer.write(line);
                    writer.newLine();
                }
            }

            writer.write("Количество удаленных дублирующихся строк: " + duplicateCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decompressFile(String compressedFilePath, String destinationFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(compressedFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sourceFilePath = "input.txt";
        String compressedFilePath = "compressed.txt";
        String decompressedFilePath = "decompressed.txt";

        // Сжатие файла
        compressFile(sourceFilePath, compressedFilePath);

        // Восстановление файла
        decompressFile(compressedFilePath, decompressedFilePath);

        // Запуск потока проверки орфографии и автозамены
        Thread spellCheckThread = new Thread(() -> {
            // Реализуйте здесь логику проверки орфографии и автозамены
            // Этот код будет выполняться параллельно с вводом пользователя
        });
        spellCheckThread.start();

        // Ожидание завершения потока проверки орфографии
        try {
            spellCheckThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
