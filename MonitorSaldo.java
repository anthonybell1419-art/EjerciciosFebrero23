public class MonitorSaldo extends Thread {

    private Banco banco;

    public MonitorSaldo(Banco b) {
        this.banco = b;
    }

    @Override
    public void run() {

        while (true) {

            System.out.println("Saldo actual: $" + banco.getSaldo());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Error en el monitor.");
            }
        }
    }
}