import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FetchData extends JFrame {
    private JButton jButton1;

    public FetchData() {
        this.initComponents();
    }

    private void initComponents() {
        this.jButton1 = new JButton();
        this.setDefaultCloseOperation(3);
        this.jButton1.setText("Запустить программу");
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FetchData.this.jButton1ActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jButton1, -1, 264, 32767).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jButton1, -2, 129, -2).addContainerGap(-1, 32767)));
        this.pack();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://corner-stats.com/index.php?route=account/login&red_route=");
        WebElement formElement = driver.findElement(By.id("login"));
        WebElement usernameElement = formElement.findElement(By.name("login"));
        WebElement passwordElement = formElement.findElement(By.name("password"));
        usernameElement.sendKeys(new CharSequence[]{"******"});
        passwordElement.sendKeys(new CharSequence[]{"******"});
        formElement.submit();

        while (driver.getCurrentUrl().toCharArray().length < 26) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException var17) {
                Logger.getLogger(FetchData.class.getName()).log(Level.SEVERE, (String) null, var17);
            }
        }

        String[] elements = driver.getCurrentUrl().split("/");
        WebElement table = driver.findElement(By.id("table_corners"));
        List<WebElement> combined = driver.findElements(By.cssSelector(".team_1_corners_quantity, .team_2_corners_quantity, a[href^='/" + elements[3] + "/']"));
        ArrayList<Integer> outgoingCorners = new ArrayList();

        for (int i = 1; i < combined.size(); i += 3) {
            if (!((WebElement) combined.get(i)).getText().equals("?")) {
                outgoingCorners.add(Integer.valueOf(((WebElement) combined.get(i)).getText()));
            }
        }

        for (int i = 0; i < outgoingCorners.size(); ++i) {
            System.out.println(outgoingCorners.get(i));
        }

        driver.get("https://corner-stats.com/");

        while (driver.getCurrentUrl().toCharArray().length < 26) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException var16) {
                Logger.getLogger(FetchData.class.getName()).log(Level.SEVERE, (String) null, var16);
            }
        }

        String[] elements2 = driver.getCurrentUrl().split("/");
        WebElement table2 = driver.findElement(By.id("table_corners"));
        List<WebElement> combined2 = driver.findElements(By.cssSelector(".team_1_corners_quantity, .team_2_corners_quantity, a[href^='/" + elements2[3] + "/']"));
        ArrayList<Integer> incomingCorners = new ArrayList();

        for (int i = 0; i < combined2.size(); i += 3) {
            if (((WebElement) combined2.get(i)).getText().toCharArray().length > 2) {
                if (!((WebElement) combined2.get(i + 2)).getText().equals("?")) {
                    incomingCorners.add(Integer.valueOf(((WebElement) combined2.get(i + 2)).getText()));
                }
            } else if (!((WebElement) combined2.get(i)).getText().equals("?")) {
                incomingCorners.add(Integer.valueOf(((WebElement) combined2.get(i)).getText()));
            }
        }

        for (int i = 0; i < incomingCorners.size(); ++i) {
            System.out.println(incomingCorners.get(i));
        }

        driver.close();
        ArrayList<ArrayList<Integer>> corners = new ArrayList();
        corners.add(outgoingCorners);
        corners.add(incomingCorners);
        String[] args = new String[]{""};
        BuildChart.setAllCorners(corners);
        BuildChart.main(args);
    }

    public static void main(String[] args) {
        try {
            LookAndFeelInfo[] var1 = UIManager.getInstalledLookAndFeels();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                LookAndFeelInfo info = var1[var3];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException var5) {
            Logger.getLogger(FetchData.class.getName()).log(Level.SEVERE, (String) null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(FetchData.class.getName()).log(Level.SEVERE, (String) null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(FetchData.class.getName()).log(Level.SEVERE, (String) null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(FetchData.class.getName()).log(Level.SEVERE, (String) null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new FetchData()).setVisible(true);
            }
        });
    }
}
