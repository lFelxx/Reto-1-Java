import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static String[] planets = { "Mercurio", "Venus", "Marte", "Jupiter", "Saturno", "Urano", "Neptuno" };
    static Double[] distance = { 91691000.0, 41400000.0, 78340000.0, 628730000.0, 1275000000.0, 2723950000.0,
            4351400000.0 };
    static Map<Integer, String> information = new HashMap<>();
    static int selectedship;
    static Boolean haveship = false;

    static String[] spaceships = { "Transportadora", "Exploradora", "Doble velocidad", "Nodriza" };
    static Integer[] speed = { 26000, 40300, 80600, 32000 };
    static Integer[] pass = {6, 4, 8, 10};

    public static void main(String[] args) throws Exception {
        int option;
        do {
            showMenu(args);
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    Integer index = selecPlanet();
                    showInfo(index);
                    if (haveship == false) {
                        selectShip();
                    }
                    break;
                case 2:
                    selectShip();
                    break;
                default:
                    break;
            }

        } while (option != 4);

    }

    public static void showMenu(String[] args) {
        System.out.println("""

                |        ===================             |
                |--------| Menú de la nave |-------------|
                |        ===================             |
                |1. Seleccionar un planeta como destino. |
                |2. Seleccionar la nave espacial.        |
                |3. Iniciar simulación del viaje.        |
                |4. Salir.                               |

                """);
        System.out.println("Porfavor escoge una opción!");
    }

    public static void selectShip() {
        if (haveship == false) {
            System.out.println("""
                                        =================
                                        NAVES DISPONIBLES
                                        =================
                    """);
            for (int i = 0; i < spaceships.length; i++) {
                System.out.println("Nave " + (i + 1) + ". " + spaceships[i] + " -Velocidad: " + speed[i]);
            }
            System.out.println("Selecciona una nave porfavor.");
            var option = scanner.nextInt();
            if (option > 0 && option <= spaceships.length) {
                System.out.println("Has seleccionado la nave: " + spaceships[option - 1]);
                selectedship = option;
                haveship = true;
            } else {
                System.err.println("Selección incorrecta, Intenta nuevamente! ");
            }
        }else{
            System.out.println("Ya has escogido la nave: " + spaceships[selectedship - 1]);
            System.out.println("Ingrese la cantidad de pasajeros que ingresarán a la nave.");
            var amount = scanner.nextInt();
            if (amount > 0 && amount <= pass[selectedship - 1]) {
                System.out.println("Registro exitoso");
            }else{
                System.err.println("Capacidad maxima excedida!");
            }
        }
    }

    public static Integer selecPlanet() {
        Integer index;
        showPlanets();
        System.out.println("8. Volver al menú principal");
        System.out.println("Porfavor escoge el planeta al que deseas ir");
        index = scanner.nextInt();
        return index - 1;
    }

    // Metodos auxiliares

    public static void showPlanets() {
        System.out.println("Planetas Disponibles: ");
        var count = 0;
        for (String planet : planets) {
            count++;
            System.out.println(count + ". " + planet);

        }
    }

    public static void showInfo(Integer index) {
        // Ingreso de la información de cada planeta a un diccionario
        information.put(0, "Se trata de un planeta cuya atmósfera es prácticamente inexistente,\r\n" + //
                "solo cuenta con algún rastro de gas. Las fluctuaciones de su temperatura son muy grandes,\r\n" + //
                "yendo desde los -180 °C hasta los +430 °C.");
        information.put(1,
                "La temperatura media del planeta es de 453 °C, su traslación dura aproximadamente 225 días terrestres,\r\n"
                        + //
                        "y su rotación 243 días. A tener en cuenta que la rotación de Venus es retrógrada, en el sentido de las agujas del reloj.\r\n"
                        + //
                        "No tiene satélites en su órbita.");
        information.put(2,
                "Igualmente, Marte cuenta con hielo en ambos polos, y su atmósfera está compuesta por Dióxido de carbono y Oxígeno.\r\n"
                        + //
                        "Su temperatura oscila entre los -123 °C y los 37°C, a lo que se suma la presencia de un fuerte viento.");
        information.put(3,
                "Su temperatura en el exterior de las nubes ronda los -153 °C, su traslación dura 11,87 años terrestres,\r\n"
                        + //
                        "mientras que su rotación 9,93 horas. Cuenta con más de 67 satélites, entre ellos Ganimedes.");
        information.put(4, "Su temperatura oscila alrededor de -185 °C. Su traslación dura 29,46 años terrestres,\r\n" + //
                " mientras que su rotación 10,66h. Cuenta con 62 satélites, entre ellos Titán.");
        information.put(5,
                "Su atmósfera está compuesta por Hidrógeno, Helio y Metano. No tiene litosfera, ni tampoco rastro de agua.\r\n"
                        + //
                        "Su temperatura ronda los -214 °C, su traslación dura 84,3 años terrestres y su rotación 17,2h.");
        information.put(6,
                "Ni tiene litosfera, ni hay rastro de agua. Su temperatura está alrededor de los -225 °C,\r\n" + //
                        "y se trata de un planeta muy azotado por fuertes vientos de hasta 2.000 km/h. Su traslación dura 164,8\r\n"
                        + //
                        "años terrestres y su rotación 16,11h. Cuenta con 14 satélites");
        // se le asigna una variable a la funcion get que obtiene el valor de cada KEY
        // dependiendo el indice.
        String info = information.get(index);
        System.out.println("""
                                                            =======================
                                                            INFORMACION DEL PLANETA
                                                            =======================
                """);
        System.out.println("El planeta " + planets[index] + " está a una distancia de: " + distance[index] + " km\n");
        System.out.println(info);
    }

}
