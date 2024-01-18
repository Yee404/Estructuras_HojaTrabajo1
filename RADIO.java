import java.util.*;

public class RADIO {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean go = true;
        String opcion = "";

        while (go) {
            printMenu();

            System.out.println("Ingrese la opción seleccionada: ");
            opcion = scan.nextLine();
            System.out.println("");
        }
    }

    // Menú de la radio (para la terminal, no interfaz gráfica)
    public static void printMenu() {
        System.out.println("");

    }
}