package clases;
import com.sun.awt.AWTUtilities;
import java.awt.BorderLayout; 
import java.awt.Color;  
import java.awt.Toolkit; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.JButton; 
import javax.swing.JDialog; 
import javax.swing.JScrollPane;  
import javax.swing.JTextPane; 
import javax.swing.text.BadLocationException; 
import javax.swing.text.SimpleAttributeSet; 
import javax.swing.text.StyleConstants; 
import javax.swing.text.StyledDocument; 

public class Hilo extends Thread { 

    private Ventana ventana; 
    private float opacidad = 0.3f;              // opacidad inicial 
    private final int TIEMPO = 5000;         // tiempo en milisegundos que estara activa la ventana

    public Hilo() { 
        ventana = new Ventana(); 
        AWTUtilities.setWindowOpacity(ventana, 0.0f); 
        ventana.setVisible(true); 
    } 

    public void agregarTexto(String msj, int tipoColor) { 
        ventana.agregarTexto(msj, tipoColor); 
    } 

    @Override 
    public void run() { 
        try { 
            hacerVisible(); 
            Thread.sleep(TIEMPO); 
            desvanecer(); 
            ventana.dispose(); 
        } catch (InterruptedException e) { 
            System.err.println(e); 
        } 
    } 

    private void hacerVisible() throws InterruptedException { 
        opacidad = 0.3f; 
        while (opacidad < 1) { 
            AWTUtilities.setWindowOpacity(ventana, opacidad); 
            opacidad += 0.03f; 
            Thread.sleep(20); 
        } 
    } 

    private void desvanecer() throws InterruptedException { 
        opacidad = 1.0f; 
        while (opacidad > 0) { 
            AWTUtilities.setWindowOpacity(ventana, opacidad); 
            opacidad -= 0.03f; 
            Thread.sleep(20); 
        } 
    } 

    public static void main(String[] args) { 
        Hilo hilo = new Hilo(); 
        hilo.agregarTexto("Hola Taringa!", 0); 
        hilo.start(); 
        hilo.agregarTexto("Hola Taringa!", 1); 
        hilo.agregarTexto("Hola Taringa!", 2); 
    } 

    class Ventana extends JDialog { 

        private final int BARRA_DE_ESTADO = 30; // Tamaño de la barra de estado en windows 
        private SimpleAttributeSet attrib; 
        private JScrollPane scrollPane; 
        private JTextPane textoPane; 
        private JButton btnCerrar; 

        public Ventana() { 
            iniciarComponentes(); 
            ubicacionVentana(); 
            attrib = new SimpleAttributeSet(); 
        } 

        private void ubicacionVentana() { 
            int tamanioX = getWidth(); 
            int tamanioY = getHeight(); 
            int maxX = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(); 
            int maxY = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(); 
             
            // ubicacion de la ventana 
            setLocation(maxX - tamanioX, maxY - tamanioY - BARRA_DE_ESTADO); 
        } 

        private void iniciarComponentes() { 
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);             
            scrollPane = new JScrollPane(); 
            textoPane = new JTextPane(); 
            btnCerrar = new JButton(); 
             
            setAlwaysOnTop(true);                          // siempre arriba 
            setPreferredSize(new java.awt.Dimension(280, 120));           // tamaño de la ventana
            setResizable(false);                             // no se puede modificar el tamaño
            setUndecorated(true);                           // no tiene los controles de estado

            scrollPane.setAutoscrolls(true); 
            textoPane.setEditable(false); 
            scrollPane.setViewportView(textoPane); 

            getContentPane().add(scrollPane, BorderLayout.CENTER); 

            btnCerrar.setText("Cerrar Ventana"); 
            btnCerrar.addActionListener(new ActionListener() { 
                @Override 
                public void actionPerformed(ActionEvent evt) { 
                    dispose(); 
                } 
            }); 
            getContentPane().add(btnCerrar, BorderLayout.PAGE_END); 
            pack(); 
        } 

        public void agregarTexto(String msj, int tipoColor) { 
            try { 
                StyleConstants.setForeground(attrib, getColorTexto(tipoColor)); 
                StyledDocument sd = textoPane.getStyledDocument(); 
                if (!textoPane.getText().isEmpty()) { 
                    sd.insertString(sd.getLength(), "n", attrib); 
                } 
                sd.insertString(sd.getLength(), msj, attrib); 
            } catch (BadLocationException e) { 
                System.err.println(e); 
            } 
        } 

        private Color getColorTexto(int tipo) { 
            switch (tipo) { 
                case 0:     // Verde 
                    return new Color(0, 130, 0); 
                case 1:     // ROJO 
                    return new Color(255, 0, 0); 
                default:    // Negro 
                    return new Color(0, 0, 0); 
            } 
        } 
    } 
} 