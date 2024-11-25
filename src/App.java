import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class App {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> planets = new ArrayList<>(Arrays.asList("Mercurio", "Venus", "Marte", "Jupiter", "Saturno", "Urano", "Neptuno"));
    static ArrayList<Double> distance = new ArrayList<>(Arrays.asList(91691000.0, 41400000.0, 78340000.0, 628730000.0, 1275000000.0, 2723950000.0, 4351400000.0));

    static ArrayList<String> spaceships = new ArrayList<>(Arrays.asList("Transportadora","Exploradora", "Doble velocidad", "Nodriza" ));
    static ArrayList<Integer> speed = new ArrayList<>(Arrays.asList(26000, 40300, 80600, 32000 ));
    public static void main(String[] args) throws Exception {

        int option;
        do {
            showMenu(args);
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    selecPlanet(args);
                    break;
                case 2:
                selectShip(args);
                    break;
                default:
                    break;
            }

        } while (option != 4);

    }

    public static void showMenu(String[] args) {
        System.out.println("""
                /n
                        ===================
                --------| Menú de la nave |---------
                        ===================
                1. Seleccionar un planeta como destino.
                2. Seleccionar la nave espacial.
                3. Iniciar simulación del viaje.
                4. Salir.
                """);
                System.out.println("Porfavor escoge una opción!");
    }

    public static void selectShip(String[] args) {
        
    }

    public static void selecPlanet(String[] args) {
        
    }
}
