import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Estudiante> lista = new ArrayList<>();
    static final String ARCHIVO = "estudiantes.dat";

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        File archivo = new File(ARCHIVO);

        if (archivo.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO));
                lista = (ArrayList<Estudiante>) ois.readObject();
                ois.close();
                System.out.println("Datos cargados correctamente.");
            } catch (Exception e) {
                System.out.println("Error al cargar el archivo. Puede estar corrupto.");
            }
        } else {
            try {
                archivo.createNewFile();
                System.out.println("Archivo creado porque no existía.");
            } catch (IOException e) {
                System.out.println("Error al crear el archivo.");
            }
        }

        int opcion = 0;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Buscar estudiante");
            System.out.println("3. Listar estudiantes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Debe introducir un numero.");
                sc.nextLine();
                opcion = 0;
            }

            switch (opcion) {

                case 1:
                    agregar(sc);
                    break;

                case 2:
                    buscar(sc);
                    break;

                case 3:
                    listar();
                    break;

                case 4:
                    guardarDatos();
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 4);

    }

    public static void agregar(Scanner sc) {

        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Matricula: ");
            String matricula = sc.nextLine();

            System.out.print("Promedio: ");
            double promedio = sc.nextDouble();
            sc.nextLine();

            Estudiante e = new Estudiante(nombre, matricula, promedio);
            lista.add(e);

            System.out.println("Estudiante agregado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al agregar estudiante.");
            sc.nextLine();
        }
    }

    public static void listar() {

        if (lista.size() == 0) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            for (Estudiante e : lista) {
                System.out.println(e);
            }
        }
    }

    public static void guardarDatos() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO));
            oos.writeObject(lista);
            oos.close();
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos.");
        }
    }

    public static void buscar(Scanner sc) {

        System.out.print("Ingrese la matricula a buscar: ");
        String mat = sc.nextLine();

        boolean encontrado = false;

        try {
            RandomAccessFile raf = new RandomAccessFile(ARCHIVO, "r");

            for (Estudiante e : lista) {
                if (e.getMatricula().equals(mat)) {
                    System.out.println("Estudiante encontrado:");
                    System.out.println(e);
                    encontrado = true;
                    break;
                }
            }

            raf.close();

            if (!encontrado) {
                System.out.println("No se encontro el estudiante.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }
}