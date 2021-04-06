import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class PortalFAPTest {
    private WebDriver navegador;
    private String[] materias = new String[5];
    private String[] qtdFaltas = new String[5];

    public void sleep (){
        try {Thread.sleep(5000);}
        catch(InterruptedException e) {}
    }

    public void arquivo() throws IOException {
        int i;
        FileWriter arq = new FileWriter("Faltas.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("+-----------Quantidade de Faltas----------+%n");
        for (i=0; i<=4; i++) {
            gravarArq.printf("| %s -> %s |%n", materias[i],qtdFaltas[i]);
        }
        gravarArq.printf("+-----------------------------------------+%n");

        try {
            arq.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setup(){
        var loginUrl= "https://portaldoaluno.fapce.edu.br/web/app/edu/PortalEducacional/login/";
        //Acessando o chromedriver em minha máquina
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        navegador = new ChromeDriver();
        navegador.get(loginUrl);
        //Realizar uma espera implícita
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @Test
    public void testLogin() throws IOException {
        String user = "Insira seu n° da matrícula";
        String password = "Insira sua senha";
        String dadosRA = "Nome Completo (RA: N° da matrícula)";



        //Encontrar e digitar no campo de user
        WebElement fieldUser = navegador.findElement(By.id("User"));
        fieldUser.sendKeys(user);

        //Encontrar e digitar no campo de senha
        WebElement fieldPass = navegador.findElement(By.id("Pass"));
        fieldPass.sendKeys(password);

        //Aguardar um momento para as informações serem carregadas
        sleep();

        //Clicar no botão para realizar o acesso na plataforma
        WebElement btnAcess = navegador.findElement(By.xpath("/html/body/div[3]/div[3]/form/div[4]/input"));
        btnAcess.click();

        //Aguardar um momento para as informações serem carregadas
        sleep();

        //Verificar se o user foi logado corretamente
        WebElement infoLogin = navegador.findElement(By.xpath("//*[@id=\"desktopHeaderMenu\"]/span[1]"));
        String dadosLogin = infoLogin.getText();
        assertEquals(dadosRA, dadosLogin);

        //Clicando no menu lateral
        WebElement btnMenu = navegador.findElement(By.id("show-menu"));
        btnMenu.click();
        sleep();

        //Acessando a central de faltas do aluno
        WebElement btnFaltas = navegador.findElement(By.id("EDU_PORTAL_ACADEMICO_CENTRALALUNO_FALTAS"));
        btnFaltas.click();
        sleep();

        //Registrando os dados de faltas 1
        WebElement materia01 = navegador.findElement(By.xpath("//*[@id=\"faltasGrid\"]/div/div[3]/table/tbody/tr[1]/td[2]/a"));
        materias[0] = materia01.getText();

        WebElement falta01 = navegador.findElement(By.xpath("//*[@id=\"faltasGrid\"]/div/div[3]/table/tbody/tr[1]/td[9]/span"));
        qtdFaltas[0] = falta01.getText();

        //Registrando os dados de faltas 2
        WebElement materia02 = navegador.findElement(By.xpath("//*[@id=\"faltasGrid\"]/div/div[3]/table/tbody/tr[2]/td[2]/a"));
        materias[1] = materia02.getText();

        WebElement falta02 = navegador.findElement(By.xpath("//*[@id=\"faltasGrid\"]/div/div[3]/table/tbody/tr[2]/td[9]/span"));
        qtdFaltas[1] = falta02.getText();

        //Registrando os dados de faltas 3
        WebElement materia03 = navegador.findElement(By.xpath("//*[@id=\"faltasGrid\"]/div/div[3]/table/tbody/tr[3]/td[2]/a"));
        materias[2] = materia03.getText();

        WebElement falta03 = navegador.findElement(By.xpath("//*[@id=\"faltasGrid\"]/div/div[3]/table/tbody/tr[3]/td[9]/span"));
        qtdFaltas[2] = falta03.getText();

        //Registrando os dados de faltas 4
        WebElement materia04 = navegador.findElement(By.xpath("//*[@id=\"faltasGrid\"]/div/div[3]/table/tbody/tr[4]/td[2]/a"));
        materias[3] = materia04.getText();

        WebElement falta04 = navegador.findElement(By.xpath("//*[@id=\"faltasGrid\"]/div/div[3]/table/tbody/tr[4]/td[9]/span"));
        qtdFaltas[3] = falta04.getText();

        //Registrando os dados de faltas 5
        WebElement materia05 = navegador.findElement(By.xpath("//*[@id=\"faltasGrid\"]/div/div[3]/table/tbody/tr[5]/td[2]/a"));
        materias[4] = materia05.getText();

        WebElement falta05 = navegador.findElement(By.xpath("//*[@id=\"faltasGrid\"]/div/div[3]/table/tbody/tr[5]/td[9]/span"));
        qtdFaltas[4] = falta05.getText();

        //Registrando em um arquivo
        arquivo();



    }

    @After
    public void tearDown(){
        //Fechando o navegador
        navegador.quit();
    }


}
