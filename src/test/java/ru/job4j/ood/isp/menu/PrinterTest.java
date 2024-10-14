package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class PrinterTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenPrintMenuThenOutputCorrectStructure() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        MenuPrinter printer = new Printer();
        printer.print(menu);

        String expectedOutput = """
                1.Сходить в магазин
                    1.1.Купить продукты
                        1.1.1.Купить хлеб
                        1.1.2.Купить молоко
                2.Покормить собаку
                """;
        assertThat(outputStream.toString()).isEqualTo(expectedOutput);
    }
}