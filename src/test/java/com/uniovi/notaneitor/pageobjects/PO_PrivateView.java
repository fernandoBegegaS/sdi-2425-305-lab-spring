package com.uniovi.notaneitor.pageobjects;

import com.uniovi.notaneitor.util.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PO_PrivateView extends PO_NavView {
    static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
        // Esperamos 5 segundos a que cargue el DOM porque en algunos equipos falla
        SeleniumUtils.waitSeconds(driver, 5);

        // Seleccionamos el alumno userOrder
        new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);

        // Rellenamos el campo de descripción
        WebElement description = driver.findElement(By.name("description"));
        description.clear();
        description.sendKeys(descriptionp);

        WebElement score = driver.findElement(By.name("score"));
        score.click();
        score.clear();
        score.sendKeys(scorep);

        By boton = By.className("btn");
        driver.findElement(boton).click();
    }


    static public void logut(WebDriver driver) {

        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);

    }


    static public void click(WebDriver driver, String str, String path) {
        List<WebElement> elements  = PO_View.checkElementBy(driver, str, path);
        elements.get(0).click();
    }

    static public void click(WebDriver driver, String str, String path, int index) {
        List<WebElement> elements  = PO_View.checkElementBy(driver, str, path);
        elements.get(index).click();
    }
}
