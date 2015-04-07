/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paypal.developer.jeffprestes.brasilino.java.client.raspberry;

import com.pi4j.wiringpi.SoftPwm;


/**
 * Class that represents car's motor
 * @author jprestes
 */
public class Motor {
    
    MotorController mControl = null;

    public Motor()  {
        super();
        mControl = new OseppMotorContoller();
        this.frenteAtiva();
        this.frenteDesativa();
        this.trasAtiva();
        this.trasDesativa();
        this.esquerdaAtiva();
        this.esquerdaDesativa();
        this.direitaAtiva();
        this.direitaDesativa();
    }
    
    /**
     * Sends commands to internal motors
     * @param cmd 
     * FA, FD = forward activate/deactivate 
     * EA, ED = left activate/deactivate 
     * DA, DD = right activate/deactivate 
     * TA, TD = backward activate/deactivate 
     * PP stops
     */
    public void opere(String cmd)   {
        switch (cmd)    {
                    
            case "EA":
                this.esquerdaAtiva();
                break;

            case "ED":
                this.esquerdaDesativa();
                break;

            case "DA":
                this.direitaAtiva();
                break;

            case "DD":
                this.direitaDesativa();
                break;

            case "FA":
                this.frenteAtiva();
                break;

            case "FD":
                this.frenteDesativa();
                break;

            case "TA":
                this.trasAtiva();
                break;

            case "TD":
                this.trasDesativa();
                break;

            case "PP":
                this.freia();
                break;
        }
    }
    
    /**
     * Activate engine to move forward
     */
    public void frenteAtiva()    {
        this.trasDesativa();
        SoftPwm.softPwmWrite(mControl.getPinPWMPropulsao(), 99);
        mControl.getPinFrente().high();
        System.out.println("frenteAtiva");
    }
    
    /**
     * Activate engine to move backward
     */
    public void trasAtiva()     {
        this.frenteDesativa();
        SoftPwm.softPwmWrite(mControl.getPinPWMPropulsao(), 99);
        mControl.getPinTras().high();
        System.out.println("trasAtiva");
    }
    
    /**
     * Activate engine to move to right
     */
    public void direitaAtiva()  {
        this.esquerdaDesativa();
        SoftPwm.softPwmWrite(mControl.getPinPWMDirecao(), 99);
        mControl.getPinDireita().high();
        System.out.println("direitaAtiva");
    }
    
    
    /**
     * Activate engine to move to left
     */
    public void esquerdaAtiva()     {
        this.direitaDesativa();
        SoftPwm.softPwmWrite(mControl.getPinPWMDirecao(), 99);
        mControl.getPinEsquerda().high();
        System.out.println("esquerdaAtiva");
    }
    
    /** 
     * Deactivate engine that move forwards
     */
    public void frenteDesativa()    {
        SoftPwm.softPwmWrite(mControl.getPinPWMPropulsao(), 0);
        mControl.getPinFrente().low();
        System.out.println("frenteDesativa");
    }
    
    
    /**
     * Deactivate engine that move backwards
     */
    public void trasDesativa()      {
        SoftPwm.softPwmWrite(mControl.getPinPWMPropulsao(), 0);
        mControl.getPinTras().low();
        System.out.println("trasDesativa");
    }
    
    
    /**
     * Deactivate engine that moves toward to right
     */
    public void direitaDesativa()   {
        SoftPwm.softPwmWrite(mControl.getPinPWMDirecao(), 0);
        mControl.getPinDireita().low();
        System.out.println("direitaDesativa");
    }
    
    /**
     * Deactivate engine that moves toward to left
     */
    public void esquerdaDesativa()  {
        SoftPwm.softPwmWrite(mControl.getPinPWMDirecao(), 0);
        mControl.getPinEsquerda().low();
        System.out.println("esquerdaDesativa");
    }
    
    /**
     * Breaks the car
     */
    public void freia()     {
        this.frenteDesativa();
        this.trasDesativa();
        System.out.println("freia");
    }
}
