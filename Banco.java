public class Banco {

    private double saldo;

    public Banco(double saldoInicial) {
        saldo = saldoInicial;
    }

    public synchronized boolean retirarDinero(double monto) {

        if (saldo >= monto) {
            System.out.println(Thread.currentThread().getName() +
                    " retiró $" + monto);
            saldo = saldo - monto;
            return true;
        } else {
            System.out.println("No hay suficiente dinero en el Banco.");
            return false;
        }
    }

    public double getSaldo() {
        return saldo;
    }
}