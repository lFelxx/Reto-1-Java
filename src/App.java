import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    // Integrantes del equipo
    //-Felix Castro
    //-Balmer Arteaga
    // listas estaticas de los planetas
    static String[] planets = { "Mercurio", "Venus", "Marte", "Jupiter", "Saturno", "Urano", "Neptuno" };
    static Double[] distance = { 91691000.0, 41400000.0, 78340000.0, 628730000.0, 1275000000.0, 2723950000.0,
            4351400000.0 };
    // Diccionario para almacenar la información detallada de cada planeta
    static Map<Integer, String> information = new HashMap<>();
    // variables que almacenan el indice de la lista a la cual se quiere acceder
    static int selectedShip;
    static int selectedPlanet;
    static int amountPass;
    // variables de verificacion booleanas
    static Boolean haveShip = false;
    static Boolean havePlanet = false;
    static Boolean havepass = false;
    static Boolean manageOxy = false;
    static Boolean manageFuel = false;
    // listas estaticas de la nave
    static String[] spaceShips = { "Transportadora", "Exploradora", "Doble velocidad", "Nodriza" };
    static double[] speed = { 26000, 40300, 80600, 32000 };
    static double[] fuelConsum = { 180, 120, 360, 400 };
    static double[] oxygen = { 6351.0, 3250.0, 4125.0, 10025.0 };
    static Integer[] pass = { 6, 4, 8, 10 };

    public static void main(String[] args) throws Exception {
        int option;
        do {
            showMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    if (havePlanet == false) {
                        selecPlanet();
                    } else {
                        System.out.println("Ya has seleccionado un destino: " + planets[selectedPlanet]);
                        // si tiene el planeta y una nave seleccionada le muestra los tiempos de llegada
                        if (haveShip == true) {
                            calculateTime();
                        }
                    }
                    if (haveShip == false && havePlanet == true) {
                        selectShip();
                    }
                    break;
                case 2:
                    selectShip();
                    // despues de seleccionar la nave verifica que ya tenga seleccionado un planeta y le muestra
                    // los tiempos del viaje
                    if (havePlanet == true) {
                        calculateTime();
                    }
                    break;
                case 3:
                    simulateTrip();
                    break;
                default:
                System.out.println("Opción invalida, intentalo nuevamente!!!");
                    break;
            }
        } while (option != 4);

    }

    public static void showMenu() {
        System.out.println("""

                |        ===================             |
                |--------| Menú principal  |-------------|
                |        ===================             |
                |1. Seleccionar un planeta como destino. |
                |2. Gestionar la nave espacial.          |
                |3. Iniciar simulación del viaje.        |
                |4. Salir.                               |

                """);
        System.out.println("Porfavor escoge una opción!");
    }

    public static void selectShip() {
        System.out.println("Selecciona la operación a realizar\n");
        int option;
        do {
            System.out.println("""
                    1. Seleccionar nave
                    2. Gestionar oxígeno
                    3. Gestionar combustible
                    4. Volver al menú principal
                    """);
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    if (haveShip == false) {
                        System.out.println("""
                                                    =================
                                                    NAVES DISPONIBLES
                                                    =================
                                """);
                        for (int i = 0; i < spaceShips.length; i++) {
                            System.out.println("Nave " + (i + 1) + ". " + spaceShips[i] + " -Velocidad: " + speed[i] + "km/h");
                        }
                        System.out.println("Selecciona una nave porfavor.");
                        var option2 = scanner.nextInt();
                        if (option2 > 0 && option2 <= spaceShips.length) {
                            System.out.println("Has seleccionado la nave: " + spaceShips[option2 - 1]);
                            selectedShip = option2 - 1;
                            haveShip = true;
                            // se llama al metodo de contar los pasajeros de la nave
                            amountPass();
                            // se llama al metodo de calcular el tiempo de viaje si ya tiene un planeta seleccionado
                            if (havePlanet == true) {
                                calculateTime();
                            }
                        } else {
                            System.err.println("Selección incorrecta, Intenta nuevamente! ");
                        }
                    // si ya tiene una nave seleccionada, verifica que este dentro del numero de opciones a escoger
                    // y envia un mensaje de confirmación
                    } else {
                        if (amountPass <= pass[selectedShip]) {
                            System.out.println("Ya has escogido la nave: " + spaceShips[selectedShip]);
                            // si no ha seleccionado la cantidad de pasajeros llama al metodo de para seleccionarlos
                            if (havepass = false) {
                                amountPass();
                            // sino envia un mensaje de la cantidad de pasajeros seleccionada
                            } else {
                                System.out.println("Ya has seleccionado la cantidad de pasajeros: " + amountPass
                                        + " para la nave: "
                                        + spaceShips[selectedShip]);
                            }
                        } else {
                            amountPass();
                        }
                    }
                    break;
                case 2:
                // si ya escogio un planeta y una nave, puede gestionar el oxígeno.
                    if (havePlanet == true) {
                        if (haveShip == true) {
                            showOxy();
                        // si no ha seleccionado una nave.
                        } else {
                            System.out.println("Primero debes seleccionar una nave para gestionar el oxígeno!");
                        }
                    // si no ha seleccionado un planeta
                    } else {
                        System.out.println("Aún no has seleccionado un planeta!");
                    }
                    break;
                case 3:
                // verifica que ya se tenga un planeta seleccionado para poder gestionar el combustible
                if (havePlanet == true) {
                    if (haveShip == true) {
                        showfuel();
                    // si no ha seleccionado una nave.
                    } else {
                        System.out.println("Primero debes seleccionar una nave para gestionar el combustible!");
                    }
                // si no ha seleccionado un planeta
                } else {
                    System.out.println("Aún no has seleccionado un planeta!");
                }
                break;
            }
        } while (option != 4);
    }

    public static void selecPlanet() {
        showPlanets();
        System.out.println("8. Volver al menú principal");
        System.out.println("Porfavor escoge el planeta al que deseas ir");
        selectedPlanet = scanner.nextInt() - 1;
        if (selectedPlanet >= 0 && selectedPlanet < planets.length) {
            System.out.println("Has seleccionado el planeta: " + planets[selectedPlanet]);
            // luego de seleccionar el planeta llama a la funcion para mostrar su información
            showInfo();
            havePlanet = true;
        } else {
            System.err.println("Opcion invalida, intentalo nuevamente!");
        }
    }

    public static void simulateTrip() {
        if (havePlanet == true && haveShip == true) {
            calculateTime();
            if (manageOxy == true && manageFuel == true) {
                System.out.println("""
                                ====================
                                Simulación Del Viaje
                                ====================
                        """);
                System.out.println("¿Desea simular el viaje? s/n");
                char option = scanner.next().charAt(0);
                // transforma el character option en mayuscula
                if (Character.toUpperCase(option) == 'S') {
                    System.out.println("----- Iniciando Viaje ----");
                    Random random = new Random();
                    for (int progreso = 0; progreso <= 100; progreso += 10) {
                        if (progreso == 30) {
                            // muestra la barra de progeso
                            progressBarSimu(10000);
                        }

                        if (random.nextInt(10) < 3) {
                            System.out.println("Terminando ajustes...");
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println("Error en la simulación");
                        }
                    }
                    // resetear variables booleanas para poder realizar otro viaje
                    havePlanet = false;
                    haveShip = false;
                    havepass = false;
                    manageFuel = false;
                    manageOxy = false;

                } else {
                    System.out.println("CANCELANDO PROCEDIMIENTO...");
                    progressBar(500);
                }

            } else {
                if (manageOxy == false || manageFuel == false) {
                    if (manageOxy == false) {
                        System.out.println(
                        "Los Niveles de oxígeno estan por debajo de los indicadores, por favor reviselos");
                    }
                    if (manageFuel == false) {
                        System.out.println(
                            "Los Niveles de combustible estan por debajo de los indicadores, por favor reviselos");
                    }
                }
            }

    }else {
        if (manageOxy == false || manageFuel == false) {
            if (manageOxy == false) {
                System.out.println(
                "Los Niveles de oxígeno estan por debajo de los indicadores, por favor reviselos");
            }
            if (manageFuel == false) {
                System.out.println(
                    "Los Niveles de combustible estan por debajo de los indicadores, por favor reviselos");
            }
        }
}
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

    public static void showInfo() {
        // Ingreso de la información de cada planeta a un diccionario
        information.put(0, "Se trata de un planeta cuya atmósfera es prácticamente inexistente,\r\n" + //
                "solo cuenta con algún rastro de gas. Las fluctuaciones de su temperatura son muy grandes,\r\n" + //
                "yendo desde los -180 °C hasta los +430 °C.\n");
        information.put(1,
                "La temperatura media del planeta es de 453 °C, su traslación dura aproximadamente 225 días terrestres,\r\n"
                        + //
                        "y su rotación 243 días. A tener en cuenta que la rotación de Venus es retrógrada, en el sentido de las agujas del reloj.\r\n"
                        + //
                        "No tiene satélites en su órbita.\n");
        information.put(2,
                "Igualmente, Marte cuenta con hielo en ambos polos, y su atmósfera está compuesta por Dióxido de carbono y Oxígeno.\r\n"
                        + //
                        "Su temperatura oscila entre los -123 °C y los 37°C, a lo que se suma la presencia de un fuerte viento.\n");
        information.put(3,
                "Su temperatura en el exterior de las nubes ronda los -153 °C, su traslación dura 11,87 años terrestres,\r\n"
                        + //
                        "mientras que su rotación 9,93 horas. Cuenta con más de 67 satélites, entre ellos Ganimedes.\n");
        information.put(4, "Su temperatura oscila alrededor de -185 °C. Su traslación dura 29,46 años terrestres,\r\n" + //
                " mientras que su rotación 10,66h. Cuenta con 62 satélites, entre ellos Titán.\n");
        information.put(5,
                "Su atmósfera está compuesta por Hidrógeno, Helio y Metano. No tiene litosfera, ni tampoco rastro de agua.\r\n"
                        + //
                        "Su temperatura ronda los -214 °C, su traslación dura 84,3 años terrestres y su rotación 17,2h.\n");
        information.put(6,
                "Ni tiene litosfera, ni hay rastro de agua. Su temperatura está alrededor de los -225 °C,\r\n" + //
                        "y se trata de un planeta muy azotado por fuertes vientos de hasta 2.000 km/h. Su traslación dura 164,8\r\n"
                        + //
                        "años terrestres y su rotación 16,11h. Cuenta con 14 satélites\n");
        // se le asigna una variable a la funcion get que obtiene el valor de cada KEY
        // dependiendo el indice.
        String info = information.get(selectedPlanet);
        System.out.println("""
                                                            =======================
                                                            INFORMACION DEL PLANETA
                                                            =======================
                """);
        System.out.println("El planeta " + planets[selectedPlanet] + " está a una distancia de: "
                + distance[selectedPlanet] + " km\n");
        System.out.println(info);
    }

    public static void amountPass() {
        System.out.println("Ingrese la cantidad de pasajeros que ingresarán a la nave.");
        amountPass = scanner.nextInt();
        if (amountPass > 0 && amountPass <= pass[selectedShip]) {
            System.out.println("Registro exitoso, a esta nave se le asignaron: " + amountPass + " pasajeros\n");
            havepass = true;
        } else {
            System.err.println("Capacidad maxima excedida! Intentalo nuevamente.");
        }
    }

    public static void calculateTime() {
        // linea para formatear los numeros tipo double
        DecimalFormat format = new DecimalFormat("#.#");
        double timehours = distance[selectedPlanet] / speed[selectedShip];
        // formatear las horas
        var timeFormat = format.format(timehours);
        System.out.println("""
                            ================
                            DATOS  DEL VIAJE
                            ================
                """);
        System.out.println("Nave seleccionada para el viaje: '" +spaceShips[selectedShip]+ "' con una velocidad de: " + speed[selectedShip] + "km/h");
        System.out.println("Planesta destino: " + planets[selectedPlanet] + " a una distancia de: " + distance[selectedPlanet]+"km\n");
        System.out.println("El tiempo estimado de viaje es de " + timeFormat + " horas");
        double timedays = timehours / 24.0;
        // formatear los dias
        var daysFormat = format.format(timedays);
        System.out.println("Duración del viaje en dias: " + daysFormat + " dias.\n");
    }

    public static void showOxy() {
        var personOxygen = 8000;
        DecimalFormat format = new DecimalFormat("#.#");
        double timehours = distance[selectedPlanet] / speed[selectedShip];
        // Oxígeno
        double oxygenHour = timehours * personOxygen / 24;
        double oxyPerson = oxygenHour * amountPass;
        var oxygenFormat = format.format(oxyPerson);
        // comparo el oxígeno de la nave con el oxígeno necesitado por pasajero.
        if (oxygen[selectedShip] < oxyPerson && manageOxy == false) {
            System.out.println("Se necesitan " + oxygenFormat + " litros de oxígeno para poder mantener el viaje de "
                + amountPass + " personas que irán en la nave\n");
            System.out.println("La nave seleccionada tiene disponible: " + oxygen[selectedShip] + " litros de oxígeno");
            System.out.println("""
                    Está por debajo de los indicadores, desea ajustar los niveles de oxígeno para el viaje?
                    s/n
                    """);
            // confirmacion para mostrar la barra de carga
            char respond = scanner.next().charAt(0);
            if (Character.toUpperCase(respond) == 'S') {
                System.out.println("PROCESANDO...");
                 // funcion para mostrar la barra de carga
                progressBar(10000);
                manageOxy = true;
            } else {
                System.out.println("CANCELANDO PROCEDIMIENTO...");
                progressBar(500);
            }
        } else {
            System.out.println("Ya se ajustaron los niveles de oxígeno.");
        }
    }

    public static void showfuel() {
        DecimalFormat format = new DecimalFormat("#.#");
        // Combustible
        double fuel = distance[selectedPlanet] * 290000 / 575;
        var fuelFormat = format.format(fuel);
        // comparo el combustible de la nave con el combustible necesitado para el viaje dependiendo la distancia.
        if (fuelConsum[selectedShip] < fuel && manageFuel == false) {
            System.out.println("Se necesitan " + fuelFormat + " litros de combustible para llegar al planeta: "
            + planets[selectedPlanet] + "\n");
            System.out.println("La nave seleccionada tiene disponible: " + fuelConsum[selectedShip] + " litros de combustible");
            System.out.println("""
                    Está por debajo de los indicadores, desea ajustar los niveles de combustible para el viaje?
                    s/n
                    """);
            // confirmacion para mostrar la barra de carga
            char respond = scanner.next().charAt(0);
            if (Character.toUpperCase(respond) == 'S') {
                System.out.println("PROCESANDO...");
                // funcion para mostrar la barra de carga
                progressBar(10000);
                calculateTime();
                manageFuel = true;
            } else {
                System.out.println("CANCELANDO PROCEDIMIENTO...");
                progressBar(500);
            }
        } else {
            System.out.println("Ya se ajustaron los niveles de combustible");
        }
    }

    public static void progressBar(int steps) {
        int totalSteps = steps;

        for (int i = 0; i <= totalSteps; i++) {
            double progress = (double) i / totalSteps;
            printProgressBar(progress);
            // Cuando llegue al 100%, sigue con el código
            if (i == totalSteps) {
                System.out.println("\n¡Proceso completado! Ajustando compuertas...");
            }
        }
    }

    public static void progressBarSimu(int steps) {
        int totalSteps = steps;

        for (int i = 0; i <= totalSteps; i++) {
            double progress = (double) i / totalSteps;
            printProgressBar(progress);
            // Cuando llegue al 100%, sigue con el código
            if (i == 7000) {
                System.out.println("\nCasi Llegamos");
            }
            if (i == totalSteps) {
                System.out.println("\n¡Llegada exitosa al destino...");
            }
        }
    }

    private static void printProgressBar(double progress) {
        int width = 50; // Ancho de la barra de progreso
        int completed = (int) (width * progress);
        System.out.print("\r[");
        for (int i = 0; i < width; i++) {
            if (i <= completed) {
                System.out.print("=");
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("] " + (int) (progress * 100) + "%");
    }


}
