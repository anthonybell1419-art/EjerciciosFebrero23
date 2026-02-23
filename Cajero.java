import java.util.Random;

public class Cajero extends Thread {

    private Banco banco;
    private int transacciones = 0;
    private Random random = new Random();

    public Cajero(String nombre, Banco b) {
        super(nombre);
        this.banco = b;
    }

    public int getTransacciones() {
        return transacciones;
    }

    @Override
    public void run() {

        int clientes = random.nextInt(3) + 3;

        for (int i = 0; i < clientes; i++) {

            double monto = random.nextInt(1501) + 500; 

            boolean resultado = banco.retirarDinero(monto);

            if (resultado) {
                transacciones++;
            }

            try {
                int tiempo = random.nextInt(3) + 1; 
                Thread.sleep(tiempo * 1000);
            } catch (InterruptedException e) {
                System.out.println("Error en el cajero " + getName());
            }
        }

        System.out.println(getName() + " terminó.");
    }
}