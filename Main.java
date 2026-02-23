public class Main {

    public static void main(String[] args) {

        Banco banco = new Banco(50000);

        Cajero c1 = new Cajero("Cajero 1", banco);
        Cajero c2 = new Cajero("Cajero 2", banco);
        Cajero c3 = new Cajero("Cajero 3", banco);

        MonitorSaldo monitor = new MonitorSaldo(banco);
        monitor.setDaemon(true); 

        monitor.start();
        c1.start();
        c2.start();
        c3.start();

        try {
            c1.join();
            c2.join();
            c3.join();
        } catch (InterruptedException e) {
            System.out.println("Error esperando cajeros.");
        }

        System.out.println("\n===== RESUMEN FINAL =====");
        System.out.println("Cajero 1 hizo: " + c1.getTransacciones() + " transacciones.");
        System.out.println("Cajero 2 hizo: " + c2.getTransacciones() + " transacciones.");
        System.out.println("Cajero 3 hizo: " + c3.getTransacciones() + " transacciones.");
        System.out.println("Saldo final: $" + banco.getSaldo());
    }
}