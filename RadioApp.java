import java.util.Scanner;

public class RadioApp {

    public static void main(String[] args) {
        Radio radio = new Radio();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\nMenú:\n");
            System.out.println("1. Encender/Apagar");
            System.out.println("2. Cambiar a AM/FM");
            System.out.println("3. Ver estado actual");
            System.out.println("0. Salir");
            System.out.print("Ingrese la opción: \n");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    radio.togglePowerOffOn();
                    break;
                case 2:
                    radio.toggleAMMode();
                    break;
                case 3:
                    radio.showCurrentState();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
