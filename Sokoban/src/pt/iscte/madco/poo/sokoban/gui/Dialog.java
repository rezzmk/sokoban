package pt.iscte.madco.poo.sokoban.gui;

import javax.swing.*;
import java.util.concurrent.Callable;

/**
 * Dialog helpers
 */
public class Dialog {
    /**
     * Triggers an Input dialog and asks for a username
     */
    public static String getUserNameInput(Object[] names) {
        ImageIcon icon = new ImageIcon("images/logo.png");

        boolean runWithNames = false;
        if (names != null) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Run with existing user?", "Sokoban", dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                runWithNames = true;
            }
        }

        return (String) JOptionPane.showInputDialog(
                null,
                "Please, provide a username",
                "Sokoban",
                JOptionPane.INFORMATION_MESSAGE,
                icon,
                runWithNames ? names : null,
                null);
    }

    public static void showMessage(String message) {
      JTextArea textArea = new JTextArea(6, 25);
      textArea.setText(message);
      textArea.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(textArea);
      JOptionPane.showMessageDialog(null, scrollPane);
    }

    public static <T> void showMessageAndWait(String message, Callable<T> funcCancel, Callable<T> funcOk) {
        SwingUtilities.invokeLater(() -> {
            int selection = JOptionPane.showConfirmDialog(
                    null,
                    message,
                    null,
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (selection == JOptionPane.CANCEL_OPTION) {
            	if (funcCancel != null) {
            		try {
                        funcCancel.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }	
            	}                
            }
            else if (selection == JOptionPane.OK_OPTION) {
            	if (funcOk != null) {
            		try {
                        funcOk.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }	
            	}                
            }
        });
    }
}
