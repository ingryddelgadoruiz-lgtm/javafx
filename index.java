import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class index {

    // AWT: Frame es la ventana principal, parecido a un JFrame pero de la librería AWT.
    // En este caso se usa Frame porque el código original está hecho con AWT/Swing mixto.
    private Frame fr_Ventana;

    // Paneles no se usan en la lógica final, pero se mantienen para respetar la estructura original.
    private Panel Panel1, Panel2, Panel3;

    // Etiquetas para mostrar texto informativo en la ventana.
    private JLabel lb_Instrucciones, lb_Num1, lb_Num2, lb_Resultado;

    // Campos de texto donde el usuario escribe los números y se muestra el resultado.
    private JTextField tf_Num1, tf_Num2, tf_Resultado;

    // Botones para cada operación matemática.
    private JButton bt_Sumar, bt_Restar, bt_Multiplicar, bt_Dividir;

    // Constructor principal: crea todos los componentes visuales y configura los eventos.
    public index() {
        // Se crea la ventana principal con un título que describe la aplicación.
        fr_Ventana = new Frame("Operaciones Matemáticas");

        // Configuración básica de la ventana.
        fr_Ventana.setSize(500, 300);
        fr_Ventana.setLocationRelativeTo(null); // Centra la ventana en la pantalla.
        fr_Ventana.setResizable(false); // Evita que el usuario cambie el tamaño.

        // Se define el texto de cada etiqueta.
        lb_Instrucciones = new JLabel("Ingrese dos números y seleccione la operación:");
        lb_Num1 = new JLabel("Número 1:");
        lb_Num2 = new JLabel("Número 2:");
        lb_Resultado = new JLabel("Resultado:");

        // Campos de texto para capturar los números.
        tf_Num1 = new JTextField();
        tf_Num2 = new JTextField();
        tf_Resultado = new JTextField();
        tf_Resultado.setEditable(false); // El resultado no se puede editar manualmente.

        // Botones con sus nombres visibles para el usuario.
        bt_Sumar = new JButton("Sumar");
        bt_Restar = new JButton("Restar");
        bt_Multiplicar = new JButton("Multiplicar");
        bt_Dividir = new JButton("Dividir");

        // Se llaman a los métodos que organizan el contenido y asignan funcionamiento.
        acomodarComponentes();
        agregarEventos();

        // Cuando el usuario cierre la ventana, finalizará la aplicación.
        fr_Ventana.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

        fr_Ventana.setVisible(true);
    }

    // Este método organiza visualmente todos los componentes en la ventana.
    public void acomodarComponentes() {
        // El GridLayout divide la ventana en filas y columnas.
        // En este caso: 6 filas (encabezado + 2 números + resultado + botones) y 2 columnas.
        fr_Ventana.setLayout(new GridLayout(6, 2, 10, 10));

        // Se agregan los elementos en el orden: etiqueta de instrucción, espacio, número 1,
        // campo 1, número 2, campo 2, resultado, campo resultado, botones de operación.
        fr_Ventana.add(lb_Instrucciones);
        fr_Ventana.add(new JLabel(""));
        fr_Ventana.add(lb_Num1);
        fr_Ventana.add(tf_Num1);
        fr_Ventana.add(lb_Num2);
        fr_Ventana.add(tf_Num2);
        fr_Ventana.add(lb_Resultado);
        fr_Ventana.add(tf_Resultado);
        fr_Ventana.add(bt_Sumar);
        fr_Ventana.add(bt_Restar);
        fr_Ventana.add(bt_Multiplicar);
        fr_Ventana.add(bt_Dividir);
    }

    // Este método asigna la acción que hará cada botón cuando el usuario lo presiona.
    private void agregarEventos() {
        // Se usa una expresión lambda para crear un ActionListener de manera corta.
        // Cada botón llama a un método calcular que recibe el símbolo de la operación.
        bt_Sumar.addActionListener(e -> calcular('+'));
        bt_Restar.addActionListener(e -> calcular('-'));
        bt_Multiplicar.addActionListener(e -> calcular('*'));
        bt_Dividir.addActionListener(e -> calcular('/'));
    }

    // Método que valida los datos y realiza la operación matemática correspondiente.
    private void calcular(char operacion) {
        try {
            // Lee los textos de los campos y los convierte a número decimal.
            double num1 = Double.parseDouble(tf_Num1.getText().trim());
            double num2 = Double.parseDouble(tf_Num2.getText().trim());
            double resultado = 0;

            // El switch decide qué operación ejecutar según el botón presionado.
            switch (operacion) {
                case '+':
                    resultado = num1 + num2;
                    break;
                case '-':
                    resultado = num1 - num2;
                    break;
                case '*':
                    resultado = num1 * num2;
                    break;
                case '/':
                    // Si el divisor es cero, se lanza una excepción para evitar una división inválida.
                    if (num2 == 0) {
                        throw new ArithmeticException("No se puede dividir entre cero.");
                    }
                    resultado = num1 / num2;
                    break;
                default:
                    throw new IllegalArgumentException("Operación no válida.");
            }

            // Muestra el resultado en el campo de texto destinado para ello.
            tf_Resultado.setText(String.valueOf(resultado));

        } catch (NumberFormatException e) {
            // Se ejecuta si el usuario ingresó texto no numérico, por ejemplo "abc".
            JOptionPane.showMessageDialog(fr_Ventana,
                    "Ingrese solo números válidos en los dos campos.",
                    "Error de entrada",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException e) {
            // Se ejecuta si ocurre un error matemático, como dividir entre cero.
            JOptionPane.showMessageDialog(fr_Ventana,
                    e.getMessage(),
                    "Error matemático",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            // Se ejecuta si la operación recibida no es reconocida.
            JOptionPane.showMessageDialog(fr_Ventana,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método principal: es el punto de entrada del programa.
    public static void main(String[] args) {
        // SwingUtilities.invokeLater asegura que la interfaz se cree en el hilo adecuado.
        // Esto es importante para evitar problemas visuales en aplicaciones con Swing.
        SwingUtilities.invokeLater(() -> {
            new index();
        });
    }
}