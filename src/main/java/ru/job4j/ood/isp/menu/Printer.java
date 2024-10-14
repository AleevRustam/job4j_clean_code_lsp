package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {

    private static final String INDENT = "    ";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo itemInfo : menu) {
            int level = itemInfo.getNumber().split("\\.").length - 1;
            String indent = INDENT.repeat(level);
            System.out.print(indent + itemInfo.getNumber() + itemInfo.getName() + "\n");
        }
    }
}
